//
//  utilities.c
//  HCPolytonicGreekKBapp
//
//  Created by Jeremy March on 3/4/17.
//  Copyright © 2017 Jeremy March. All rights reserved.
//

#include "utilities.h"

#define UNICODE_SURROGATE_PAIR    -1
#define UNICODE_BAD_INPUT         -2

#define DEBUG_SPLICE(X) debug_msg(X)

void debug_msg(char *x)
{
    fprintf(stderr, "%s\n", x);
    //exit(1);
}

bool rightShiftFromOffsetSteps(UCS2 *ucs2, int offset, int steps, int *len, int buffer_capacity)
{
    if (*len + steps > buffer_capacity)
    {
        DEBUG_SPLICE("right shift: out of bounds!");
        return false;
    }
    if (offset >= *len)
    {
        DEBUG_SPLICE("right shift: offset out of bounds!");
        return false;
    }
    
    for (int j = *len ; j >= offset; j--)
    {
        //printf("j: %d\n", j);
        ucs2[j + steps] = ucs2[j];
        ucs2[j] = 0;
    }
    *len += steps;
    return true;
}

//Moves everything over to the left, eating the char at the offset index
bool leftShiftFromOffsetSteps(UCS2 *ucs2, int offset, int steps, int *len)
{
    if (offset < 0)
    {
        DEBUG_SPLICE("offset out of bounds!");
        return false;
    }
    if (offset + steps > *len)
    {
        DEBUG_SPLICE("out of bounds!");
        return false;
    }
    int j = offset;
    for ( ; j < *len - 1; j++)
    {
        ucs2[j] = ucs2[j + steps];
    }
    *len -= steps;
    return true;
}

/*
 * Almost just like the Javascript function splice
 * Except there cannot be any empty gaps, it will fail
 * string: the string
 * len: the actual len of the string
 * bufferCapacity: the length of the array buffer
 * offset: the offset to begin inserting/removing elements
 * replacing: how many characters to replace or remove if insert_len is 0
 * insert: the string to insert
 * insert_len: the length of the elements to be inserted.  Can be 0 if just want to remove.
 *
 * returns true, or false if error
 */
bool ucsplice(UCS2 *string, int *len, int buffer_capacity, int offset, int replacing, UCS2 *insert, int insert_len)
{
    if (*len + insert_len - replacing > buffer_capacity)
    {
        DEBUG_SPLICE("out of bounds!");
        return false;
    }
    if (offset >= *len + 1)
    {
        DEBUG_SPLICE("offset beyond end of string + 1!"); //would create gap
        return false;
    }
    if (replacing > (*len - offset))
    {
        DEBUG_SPLICE("replacing cannot go past end of string!");
        return false;
    }
    if (insert_len < 0)
    {
        DEBUG_SPLICE("insert len must be >= 0!");
        return false;
    }
    if (offset > *len || offset < 0)
    {
        DEBUG_SPLICE("offset out of bounds!");
        return false;
    }
    //shift right, this function increases len
    if (replacing < insert_len)
    {
        if (offset + replacing < *len) //only call right shift if offset is before end, else rightShift will fail since nothing to move
        {
            if (!rightShiftFromOffsetSteps(string, offset + replacing, insert_len - replacing, len, buffer_capacity))
            {
                return false;
            }
        }
        else
        {
            *len += insert_len - replacing;
        }
    }
    else if (replacing > insert_len)
    {
        if (!leftShiftFromOffsetSteps(string, offset + insert_len, replacing - insert_len, len))
        {
            return false;
        }
    }
    for (int i = 0; i < insert_len; i++)
    {
        string[offset + i] = insert[i];
    }
    return true;
}

/* Input: a Unicode code point, "ucs2".
 
 Output: UTF-8 characters in buffer "utf8".
 
 Return value: the number of bytes written into "utf8", or -1 if
 there was an error.
 
 This adds a zero byte to the end of the string. It assumes that the
 buffer "utf8" has at least four bytes of space to write to. */
// from http://www.lemoda.net/c/ucs2-to-utf8/
int ucs2_to_utf8 (UCS2 ucs2, unsigned char * utf8)
{
    if (ucs2 < 0x80) {
        utf8[0] = ucs2;
        utf8[1] = '\0';
        return 1;
    }
    if (ucs2 >= 0x80  && ucs2 < 0x800) {
        utf8[0] = (ucs2 >> 6)   | 0xC0;
        utf8[1] = (ucs2 & 0x3F) | 0x80;
        utf8[2] = '\0';
        return 2;
    }
    if (ucs2 >= 0x800 && ucs2 < 0xFFFF) {
        if (ucs2 >= 0xD800 && ucs2 <= 0xDFFF) {
            // Ill-formed.
            return UNICODE_SURROGATE_PAIR;
        }
        utf8[0] = ((ucs2 >> 12)       ) | 0xE0;
        utf8[1] = ((ucs2 >> 6 ) & 0x3F) | 0x80;
        utf8[2] = ((ucs2      ) & 0x3F) | 0x80;
        utf8[3] = '\0';
        return 3;
    }
    /*
    //ucs2 >= UINT16_MAX &&
    if (ucs2 >= 0x10000 && ucs2 < 0x10FFFF) {
        // http://tidy.sourceforge.net/cgi-bin/lxr/source/src/utf8.c#L380
        utf8[0] = 0xF0 | (ucs2 >> 18);
        utf8[1] = 0x80 | ((ucs2 >> 12) & 0x3F);
        utf8[2] = 0x80 | ((ucs2 >> 6) & 0x3F);
        utf8[3] = 0x80 | ((ucs2 & 0x3F));
        utf8[4] = '\0';
        return 4;
    }
*/
    return UNICODE_BAD_INPUT;
}

/* Convert a UTF-8 encoded character in "input" into a number. This
 function returns the unicode value of the UTF-8 character if
 successful, and -1 if not successful. "end_ptr" is set to the next
 character after the read character on success. "end_ptr" is set to
 the start of input on failure. "end_ptr" may not be null. */

UCS2 utf8_to_ucs2 (const unsigned char * input, const unsigned char ** end_ptr)
{
    *end_ptr = input;
    if (input[0] == 0)
        return -1;
    if (input[0] < 0x80) {
        * end_ptr = input + 1;
        return input[0];
    }
    if ((input[0] & 0xE0) == 0xE0) {
        if (input[1] == 0 || input[2] == 0)
            return -1;
        * end_ptr = input + 3;
        return
        (input[0] & 0x0F)<<12 |
        (input[1] & 0x3F)<<6  |
        (input[2] & 0x3F);
    }
    if ((input[0] & 0xC0) == 0xC0) {
        if (input[1] == 0)
            return -1;
        * end_ptr = input + 2;
        return
        (input[0] & 0x1F)<<6  |
        (input[1] & 0x3F);
    }
    return -1;
}

//FIX ME, this could probably be written more efficiently.
//only null terminate at the end and verify that it is null terminated properly
int ucs2_to_utf8_string(UCS2 *ucs2, int len, unsigned char *utf8)
{
    unsigned char *utf8Temp = utf8;
    if (len < 1)
    {
        utf8[0] = '\0';
        return 1;
    }
    
    for (int i = 0; i < len; i++)
    {
        int utf8Len;
        utf8Len = ucs2_to_utf8(ucs2[i], utf8Temp);
        if (utf8Len > -1)
            utf8Temp += utf8Len;
        else
            return 0;
        *utf8Temp = '\0';
    }
    
    return 1;
}

void utf8_to_ucs2_string(const unsigned char *utf8, UCS2 *ucs2, int *len)
{
    int temp; //because UCS2 is unsigned.
    *len = 0;
    
    for( int i = 0; *utf8 ; i++)
    {
        temp = utf8_to_ucs2 (utf8, &utf8);
        if (temp == -1)
        {
            break;
        }
        else
        {
            ucs2[i] = temp;
        }
        (*len)++;
    }
}

/*
bool utf8HasSuffix2(char *s, char *suffix, ...)
{
    va_list argp;
    unsigned long len = strlen(s);
    
    //if (suffixLen > len)
    //    return false;
 
    va_start( argp, suffix );
    for( i = 0; argp != '\0'; ++i )
    {
 
        suffix = va_arg( vl, char * );
        unsigned long suffixLen = strlen(suffix);
    
        long j = len - 1;
        for (long i = suffixLen - 1; i >= 0; i--, j--)
        {
            if (suffix[i] != s[j])
                return false;
        }
    }
    va_end( argp );
 
    return true;
}

*/
