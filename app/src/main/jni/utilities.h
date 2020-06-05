//
//  utilities.h
//  HCPolytonicGreekKBapp
//
//  Created by Jeremy March on 3/4/17.
//  Copyright © 2017 Jeremy March. All rights reserved.
//

#ifndef utilities_h
#define utilities_h

#include <stdbool.h>
#include <stdio.h>

#define BUFFER_LEN 1024

typedef unsigned short UCS2;

bool ucsplice(UCS2 *string, int *len, int bufferCapacity, int offset, int replacing, UCS2 *insert, int insert_len);

int ucs2_to_utf8 (UCS2 ucs2, unsigned char * utf8);
UCS2 utf8_to_ucs2 (const unsigned char * input, const unsigned char ** end_ptr);

void utf8_to_ucs2_string(const unsigned char *utf8, UCS2 *ucs2, int *len);
int ucs2_to_utf8_string(UCS2 *ucs2, int len, unsigned char *utf8);

#endif /* utilities_h */
