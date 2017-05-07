//
//  GreekUnicode.h
//  morph
//
//  Created by Jeremy on 4/15/15.
//  Copyright (c) 2015 Jeremy March. All rights reserved.
//

#ifndef morph_GreekUnicode_h
#define morph_GreekUnicode_h

#define COMBINING_GRAVE                 0x0300
#define COMBINING_ACUTE                 0x0301
#define COMBINING_CIRCUMFLEX            0x0342//0x0302
#define COMBINING_MACRON                0x0304
#define COMBINING_DIAERESIS             0x0308
#define COMBINING_SMOOTH_BREATHING      0x0313
#define COMBINING_ROUGH_BREATHING       0x0314
#define COMBINING_IOTA_SUBSCRIPT        0x0345
#define EM_DASH                         0x2014
#define LEFT_PARENTHESIS                0x0028
#define RIGHT_PARENTHESIS               0x0029
#define SPACE                           0x0020
#define EN_DASH                         0x2013
#define HYPHEN                          0x2010
#define COMMA                           0x002C
/* BASIC GREEK 0370-03FF */

#define GREEK_CAPITAL_LETTER_HETA  					0x0370 // Ͱ → 2C75 Ⱶ latin capital letter half h
#define GREEK_SMALL_LETTER_HETA 					0x0371 // ͱ → 2C76 ⱶ latin small letter half h
#define GREEK_CAPITAL_LETTER_ARCHAIC_SAMPI			0x0372 // Ͳ
#define GREEK_SMALL_LETTER_ARCHAIC_SAMPI			0x0373 // ͳ
#define GREEK_NUMERAL_SIGN 			 				0x0374 // ʹ = dexia keraia //• indicates numeric use of letters → 02CA ˊ modifier letter acute accent ≡ 02B9 ʹ modifier letter prime
#define GREEK_LOWER NUMERAL_SIGN  					0x0375 // ͵ = aristeri keraia //• indicates numeric use of letters→ 02CF ˏ modifier letter low acute accent
#define GREEK_CAPITAL_LETTER_PAMPHYLIAN_DIGAMMA		0x0376 // Ͷ
#define GREEK_SMALL_LETTER_PAMPHYLIAN_DIGAMMA		0x0377 // ͷ

#define GREEK_YPOGEGRAMMENI  								0x037A // ͺ = iota subscript → 0345 $ͅ combining greek ypogegrammeni≈ 0020  0345 $ͅ
#define GREEK_SMALL_REVERSED_LUNATE_SIGMA_SYMBOL			0x037B // ͻ
#define GREEK_SMALL_DOTTED_LUNATE_SIGMA_SYMBOL	 			0x037C // ͼ
#define GREEK_SMALL_REVERSED_DOTTED_LUNATE_SIGMA_SYMBOL		0x037D // ͽ
#define GREEK_QUESTION_MARK 		 						0x037E // ; = erotimatiko• sentence-final punctuation • 003B ; is the preferred character → 003F ? question mark ≡ 003B ; semicolon
#define GREEK_CAPITAL_LETTER_YOT 							0x037F // Ϳ • lowercase is 03F3 ϳ

#define GREEK_TONOS 		 								0x0384 // ΄ → 00B4 ´ acute accent //→ 030D $̍ combining vertical line above ≈ 0020  0301 $́
#define GREEK_DIALYTIKA_TONOS 	 							0x0385 // ΅ ≡ 00A8 ¨ 0301 $́
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_TONOS 	 			0x0386 // Ά ≡ 0391 Α 0301 $́
#define GREEK_ANO_TELEIA  									0x0387 // · • functions in Greek like a semicolon • 00B7 · is the preferred character ≡ 00B7 · middle raised dot
#define GREEK_CAPITAL_LETTER_EPSILON_WITH_TONOS  			0x0388 // Έ ≡ 0395 Ε 0301 $́
#define GREEK_CAPITAL_LETTER_ETA_WITH_TONOS  				0x0389 // Ή ≡ 0397 Η 0301 $́
#define GREEK_CAPITAL_LETTER_IOTA_WITH_TONOS  				0x038A // Ί ≡ 0399 Ι 0301 $́
//0x038B " <reserved>
#define GREEK_CAPITAL_LETTER_OMICRON_WITH_TONOS  			0x038C // Ό ≡ 039F Ο 0301 $́
//0x038D " <reserved>
#define GREEK_CAPITAL_LETTER_UPSILON_WITH_TONOS  			0x038E // Ύ ≡ 03A5 Υ 0301 $́
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_TONOS  				0x038F // Ώ ≡ 03A9 Ω 0301 $́
#define GREEK_SMALL_LETTER_IOTA_WITH_DIALYTIKA_AND_TONOS 	0x0390 // ΐ ≡ 03CA ϊ 0301 $́
#define GREEK_CAPITAL_LETTER_ALPHA	 						0x0391 // Α
#define GREEK_CAPITAL_LETTER_BETA	 						0x0392 // Β
#define GREEK_CAPITAL_LETTER_GAMMA  						0x0393 // Γ = gamma function → 213E ℾ double-struck capital gamma
#define GREEK_CAPITAL_LETTER_DELTA  						0x0394 // Δ → 2206 Δ increment
#define GREEK_CAPITAL_LETTER_EPSILON		0x0395 // Ε
#define GREEK_CAPITAL_LETTER_ZETA 			0x0396 // Ζ
#define GREEK_CAPITAL_LETTER_ETA	 		0x0397 // Η
#define GREEK_CAPITAL_LETTER_THETA	 		0x0398 // Θ
#define GREEK_CAPITAL_LETTER_IOTA 	 		0x0399 // Ι = iota adscript
#define GREEK_CAPITAL_LETTER_KAPPA	 		0x039A // Κ
#define GREEK_CAPITAL_LETTER_LAMDA	 		0x039B // Λ
#define GREEK_CAPITAL_LETTER_MU		 		0x039C // Μ
#define GREEK_CAPITAL_LETTER_NU	 			0x039D // Ν
#define GREEK_CAPITAL_LETTER_XI	 			0x039E // Ξ
#define GREEK_CAPITAL_LETTER_OMICRON	 	0x039F // Ο
#define GREEK_CAPITAL_LETTER_PI 	 		0x03A0 // Π → 213F ℿ double-struck capital pi //→ 220F Π n-ary product
#define GREEK_CAPITAL_LETTER_RHO	 		0x03A1 // Ρ
//0x03A2 " <reserved>
#define GREEK_CAPITAL_LETTER_SIGMA 	 		0x03A3 // Σ → 01A9 Ʃ latin capital letter esh //→ 2211 Σ n-ary summation
#define GREEK_CAPITAL_LETTER_TAU	 		0x03A4 // Τ
#define GREEK_CAPITAL_LETTER_UPSILON		0x03A5 // Υ
#define GREEK_CAPITAL_LETTER_PHI 			0x03A6 // Φ
#define GREEK_CAPITAL_LETTER_CHI			0x03A7 // Χ
#define GREEK_CAPITAL_LETTER_PSI	 		0x03A8 // Ψ
#define GREEK_CAPITAL_LETTER_OMEGA 	 		0x03A9 // Ω → 2126 Ω ohm sign → 2127 ℧ inverted ohm sign
#define GREEK_CAPITAL_LETTER_IOTA_WITH_DIALYTIKA				0x03AA // Ϊ ≡ 0399 Ι 0308 $̈
#define GREEK_CAPITAL_LETTER_UPSILON_WITH_DIALYTIKA				0x03AB // Ϋ ≡ 03A5 Υ 0308 $̈
#define GREEK_SMALL_LETTER_ALPHA_WITH_TONOS 	 				0x03AC // ά ≡ 03B1 α 0301 $́
#define GREEK_SMALL_LETTER_EPSILON_WITH_TONOS 	 				0x03AD // έ ≡ 03B5 ε 0301 $́
#define GREEK_SMALL_LETTER_ETA_WITH_TONOS 	 					0x03AE // ή ≡ 03B7 η 0301 $́
#define GREEK_SMALL_LETTER_IOTA_WITH_TONOS 	 					0x03AF // ί ≡ 03B9 ι 0301 $́
#define GREEK_SMALL_LETTER_UPSILON_WITH_DIALYTIKA_AND_TONOS 	0x03B0 // ΰ ≡ 03CB ϋ 0301 $́
#define GREEK_SMALL_LETTER_ALPHA 		0x03B1 // α → 0251 ɑ latin small letter alpha → 221D ∝ proportional to
#define GREEK_SMALL_LETTER_BETA 		0x03B2 // β → 00DF ß latin small letter sharp s → 0180 ƀ latin small letter b with stroke
#define GREEK_SMALL_LETTER_GAMMA 		0x03B3 // γ → 0263 ɣ latin small letter gamma → 213D ℽ double-struck small gamma
#define GREEK_SMALL_LETTER_DELTA		0x03B4 // δ
#define GREEK_SMALL_LETTER_EPSILON 		0x03B5 // ε → 025B ɛ latin small letter open e
#define GREEK_SMALL_LETTER_ZETA		 	0x03B6 // ζ
#define GREEK_SMALL_LETTER_ETA		 	0x03B7 // η
#define GREEK_SMALL_LETTER_THETA 		0x03B8 // θ → 0275 ɵ latin small letter barred o → 0473 ѳ cyrillic small letter fita
#define GREEK_SMALL_LETTER_IOTA 		0x03B9 // ι → 0269 ɩ latin small letter iota → 2129 ℩ turned GREEK_SMALL_LETTER_iota
#define GREEK_SMALL_LETTER_KAPPA		0x03BA // κ
#define GREEK_SMALL_LETTER_LAMDA 		0x03BB // λ = lambda
#define GREEK_SMALL_LETTER_MU 		 	0x03BC // μ → 00B5 μ micro sign
#define GREEK_SMALL_LETTER_NU	 		0x03BD // ν
#define GREEK_SMALL_LETTER_XI	 		0x03BE // ξ
#define GREEK_SMALL_LETTER_OMICRON 	 	0x03BF // ο
#define GREEK_SMALL_LETTER_PI 	 		0x03C0 // π • math constant 3.141592...
#define GREEK_SMALL_LETTER_RHO	 		0x03C1 // ρ
#define GREEK_SMALL_LETTER_FINAL_SIGMA 	0x03C2 // ς = stigma (the Modern Greek name for this letterform) • not to be confused with the actual stigma letter → 03DB ϛ GREEK_SMALL_LETTER_stigma
#define GREEK_SMALL_LETTER_SIGMA 	 	0x03C3 // σ • used symbolically with a numeric value 200
#define GREEK_SMALL_LETTER_TAU	 		0x03C4 // τ
#define GREEK_SMALL_LETTER_UPSILON 	 	0x03C5 // υ → 028A ʊ latin small letter upsilon → 028B ʋ latin small letter v with hook
#define GREEK_SMALL_LETTER_PHI 	 		0x03C6 // φ → 0278 ɸ latin small letter phi → 03D5 ϕ greek phi symbol • the ordinary Greek letter, showing considerable glyph variation • in mathematical contexts, the loopy glyph is preferred, to contrast with 03D5 ϕ
#define GREEK_SMALL_LETTER_CHI 	 		0x03C7 // χ → AB53 ꭓ latin small letter chi
#define GREEK_SMALL_LETTER_PSI	 		0x03C8 // ψ
#define GREEK_SMALL_LETTER_OMEGA	 	0x03C9 // ω

#define GREEK_SMALL_LETTER_IOTA_WITH_DIALYTIKA 	 		0x03CA // ϊ ≡ 03B9 ι 0308 $̈
#define GREEK_SMALL_LETTER_UPSILON_WITH_DIALYTIKA 		0x03CB // ϋ ≡ 03C5 υ 0308 $̈
#define GREEK_SMALL_LETTER_OMICRON_WITH_TONOS 	 		0x03CC // ό ≡ 03BF ο 0301 $́
#define GREEK_SMALL_LETTER_UPSILON_WITH_TONOS 	 		0x03CD // ύ ≡ 03C5 υ 0301 $́
#define GREEK_SMALL_LETTER_OMEGA_WITH_TONOS 	 		0x03CE // ώ ≡ 03C9 ω 0301 $́
#define GREEK_CAPITAL_KAI_SYMBOL		 				0x03CF // Ϗ → 03D7 ϗ greek kai symbol
#define GREEK_BETA_SYMBOL		 						0x03D0 // ϐ = curled beta ≈ 03B2 β GREEK_SMALL_LETTER_beta
#define GREEK_THETA_SYMBOL		 						0x03D1 // ϑ = script theta • used as a technical symbol ≈ 03B8 θ GREEK_SMALL_LETTER_theta
#define GREEK_UPSILON_WITH_HOOK_SYMBOL	 				0x03D2 // ϒ ≈ 03A5 Υ GREEK_CAPITAL_LETTER_upsilon
#define GREEK_UPSILON_WITH_ACUTE_AND_HOOK_SYMBOL 		0x03D3 // ϓ ≡ 03D2 ϒ 0301 $́
#define GREEK_UPSILON_WITH_DIAERESIS_AND_HOOK_SYMBOL 	0x03D4 // ϔ ≡ 03D2 ϒ 0308 $̈
#define GREEK_PHI_SYMBOL								0x03D5 // ϕ • used as a technical symbol, with a stroked glyph • maps to “phi1” symbol entities ≈ 03C6 φ GREEK_SMALL_LETTER_phi
#define GREEK_PI_SYMBOL		 							0x03D6 // ϖ = omega pi • used as a technical symbol • a variant of pi, looking like omega ≈ 03C0 π GREEK_SMALL_LETTER_pi
#define GREEK_KAI_SYMBOL		 						0x03D7 // ϗ • used as an ampersand → 2CE4 ⳤ coptic symbol kai
#define GREEK_LETTER_ARCHAIC_KOPPA	 					0x03D8 // Ϙ
#define GREEK_SMALL_LETTER_ARCHAIC_KOPPA 	 			0x03D9 // ϙ • the Q-shaped archaic koppas are the ordinary alphabetic letters and can also be used as symbols with a numeric value of 90 in classical and pre-classical texts
#define GREEK_LETTER_STIGMA 	 						0x03DA // Ϛ • apparently in origin a cursive form of digamma • the name “stigma” originally applied to a medieval sigma-tau ligature, whose shape was confusably similar to the cursive digamma • used as a symbol with a numeric value of 6 → 2185 ↅ roman numeral six late form
#define GREEK_SMALL_LETTER_STIGMA 	 					0x03DB // ϛ → 03C2 ς GREEK_SMALL_LETTER_final sigma
#define GREEK_LETTER_DIGAMMA	 		0x03DC // Ϝ
#define GREEK_SMALL_LETTER_DIGAMMA 	 	0x03DD // ϝ • used as a symbol with a numeric value of 6
#define GREEK_LETTER_KOPPA	 			0x03DE // Ϟ
#define GREEK_SMALL_LETTER_KOPPA 	 	0x03DF // ϟ • used in modern Greek as a symbol with a numeric value of 90, as in the dating of legal documentation
#define GREEK_LETTER_SAMPI	 			0x03E0 // Ϡ
#define GREEK_SMALL_LETTER_SAMPI 	 	0x03E1 // ϡ • used as a symbol with a numeric value of 900

#define COPTIC_CAPITAL_LETTER_SHEI	 	0x03E2 // Ϣ
#define COPTIC_SMALL_LETTER_SHEI	 	0x03E3 // ϣ
#define COPTIC_CAPITAL_LETTER_FEI	 	0x03E4 // Ϥ
#define COPTIC_SMALL_LETTER_FEI	 		0x03E5 // ϥ
#define COPTIC_CAPITAL_LETTER_KHEI	 	0x03E6 // Ϧ
#define COPTIC_SMALL_LETTER_KHEI	 	0x03E7 // ϧ
#define COPTIC_CAPITAL_LETTER_HORI	 	0x03E8 // Ϩ
#define COPTIC_SMALL_LETTER_HORI	 	0x03E9 // ϩ
#define COPTIC_CAPITAL_LETTER_GANGIA	0x03EA // Ϫ
#define COPTIC_SMALL_LETTER_GANGIA	 	0x03EB // ϫ
#define COPTIC_CAPITAL_LETTER_SHIMA	 	0x03EC // Ϭ
#define COPTIC_SMALL_LETTER_SHIMA	 	0x03ED // ϭ
#define COPTIC_CAPITAL_LETTER_DEI	 	0x03EE // Ϯ
#define COPTIC_SMALL_LETTER_DEI	 		0x03EF // ϯ

#define GREEK_KAPPA_SYMBOL	 					0x03F0 // ϰ = script kappa • used as technical symbol ≈ 03BA κ GREEK_SMALL_LETTER_kappa
#define GREEK_RHO_SYMBOL	 					0x03F1 // ϱ = tailed rho • used as technical symbol ≈ 03C1 ρ GREEK_SMALL_LETTER_rho
#define GREEK_LUNATE_SIGMA_SYMBOL				0x03F2 // ϲ = GREEK_SMALL_LETTER_lunate sigma (1.0) ≈ 03C2 ς GREEK_SMALL_LETTER_final sigma
#define GREEK_LETTER_YOT 	 					0x03F3 // ϳ • uppercase is 037F Ϳ
#define GREEK_CAPITAL_THETA_SYMBOL	 			0x03F4 // ϴ → 0472 Ѳ cyrillic capital letter fita ≈ 0398 Θ GREEK_CAPITAL_LETTER_theta
#define GREEK_LUNATE_EPSILON_SYMBOL	 			0x03F5 // ϵ = straight epsilon → 220A ε small element of ≈ 03B5 ε GREEK_SMALL_LETTER_epsilon
#define GREEK_REVERSED_LUNATE_EPSILON_SYMBOL	0x03F6 // ϶ = reversed straight epsilon → 220D ∍ small contains as member
#define GREEK_CAPITAL_LETTER_SHO	 			0x03F7 // Ϸ
#define GREEK_SMALL_LETTER_SHO		 			0x03F8 // ϸ
#define GREEK_CAPITAL_LUNATE_SIGMA_SYMBOL		0x03F9 // Ϲ ≈ 03A3 Σ GREEK_CAPITAL_LETTER_sigma
#define GREEK_CAPITAL_LETTER_SAN		 		0x03FA // Ϻ
#define GREEK_SMALL_LETTER_SAN		 			0x03FB // ϻ

#define GREEK_RHO_WITH_STROKE_SYMBOL		 				0x03FC // ϼ • used with abbreviations containing 03C1 ρ
#define GREEK_CAPITAL_REVERSED_LUNATE_SIGMA_SYMBOL	 		0x03FD // Ͻ = antisigma
#define GREEK_CAPITAL_DOTTED_LUNATE SIGMA_SYMBOL  			0x03FE // Ͼ = sigma periestigmenon
#define GREEK_CAPITAL_REVERSED_DOTTED_LUNATE_SIGMA_SYMBOL	0x03FF // Ͽ = antisigma periestigmenon

/* EXTENDED GREEK 1F00-1FFF */

#define GREEK_SMALL_LETTER_ALPHA_WITH_PSILI                     0x1F00  // ἀ ≡ 03B1 α  0313 $̓
#define GREEK_SMALL_LETTER_ALPHA_WITH_DASIA                     0x1F01  // ἁ ≡ 03B1 α  0314 $̔
#define GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_VARIA           0x1F02  // ἂ ≡ 1F00 ἀ  0300 $̀
#define GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_VARIA           0x1F03  // ἃ ≡ 1F01 ἁ  0300 $̀
#define GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_OXIA            0x1F04  // ἄ ≡ 1F00 ἀ  0301 $́
#define GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_OXIA			0x1F05  // ἅ ≡ 1F01 ἁ  0301 $́
#define GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_PERISPOMENI		0x1F06  // ἆ ≡ 1F00 ἀ  0342 $͂
#define GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_PERISPOMENI		0x1F07  // ἇ ≡ 1F01 ἁ  0342 $͂
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_PSILI                   0x1F08  // Ἀ ≡ 0391 Α  0313 $̓
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_DASIA                   0x1F09  // Ἁ ≡ 0391 Α  0314 $̔
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_PSILI_AND_VARIA			0x1F0A  // Ἂ ≡ 1F08 Ἀ  0300 $̀
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_DASIA_AND_VARIA			0x1F0B  // Ἃ ≡ 1F09 Ἁ  0300 $̀
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_PSILI_AND_OXIA          0x1F0C  // Ἄ ≡ 1F08 Ἀ  0301 $́
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_DASIA_AND_OXIA			0x1F0D  // Ἅ ≡ 1F09 Ἁ  0301 $́
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_PSILI_AND_PERISPOMENI	0x1F0E  // Ἆ ≡ 1F08 Ἀ  0342 $͂
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_DASIA_AND_PERISPOMENI	0x1F0F  // Ἇ ≡ 1F09 Ἁ  0342 $͂
#define GREEK_SMALL_LETTER_EPSILON_WITH_PSILI					0x1F10  // ἐ ≡ 03B5 ε  0313 $̓
#define GREEK_SMALL_LETTER_EPSILON_WITH_DASIA					0x1F11  // ἑ ≡ 03B5 ε  0314 $̔
#define GREEK_SMALL_LETTER_EPSILON_WITH_PSILI_AND_VARIA			0x1F12  // ἒ ≡ 1F10 ἐ  0300 $̀
#define GREEK_SMALL_LETTER_EPSILON_WITH_DASIA_AND_VARIA			0x1F13  // ἓ ≡ 1F11 ἑ  0300 $̀
#define GREEK_SMALL_LETTER_EPSILON_WITH_PSILI_AND_OXIA			0x1F14  // ἔ ≡ 1F10 ἐ  0301 $́
#define GREEK_SMALL_LETTER_EPSILON_WITH_DASIA_AND_OXIA			0x1F15  // ἕ ≡ 1F11 ἑ  0301 $́
//1F16 " <reserved>
//1F17 " <reserved>
#define GREEK_CAPITAL_LETTER_EPSILON_WITH_PSILI                 0x1F18  // Ἐ ≡ 0395 Ε  0313 $̓
#define GREEK_CAPITAL_LETTER_EPSILON_WITH_DASIA                 0x1F19  // Ἑ ≡ 0395 Ε  0314 $̔
#define GREEK_CAPITAL_LETTER_EPSILON_WITH_PSILI_AND_VARIA       0x1F1A 	// Ἒ ≡ 1F18 Ἐ  0300 $̀
#define GREEK_CAPITAL_LETTER_EPSILON_WITH_DASIA_AND_VARIA       0x1F1B  // Ἓ ≡ 1F19 Ἑ  0300 $̀
#define GREEK_CAPITAL_LETTER_EPSILON_WITH_PSILI_AND_OXIA        0x1F1C  // Ἔ ≡ 1F18 Ἐ  0301 $́
#define GREEK_CAPITAL_LETTER_EPSILON_WITH_DASIA_AND_OXIA        0x1F1D  // Ἕ ≡ 1F19 Ἑ  0301 $́
//1F1E " <reserved>
//1F1F " <reserved>
#define GREEK_SMALL_LETTER_ETA_WITH_PSILI                       0x1F20 	// ἠ ≡ 03B7 η  0313 $̓
#define GREEK_SMALL_LETTER_ETA_WITH_DASIA                       0x1F21 	// ἡ ≡ 03B7 η  0314 $̔
#define GREEK_SMALL_LETTER_ETA_WITH_PSILI_AND_VARIA             0x1F22  // ἢ ≡ 1F20 ἠ  0300 $̀
#define GREEK_SMALL_LETTER_ETA_WITH_DASIA_AND_VARIA             0x1F23  // ἣ ≡ 1F21 ἡ  0300 $̀
#define GREEK_SMALL_LETTER_ETA_WITH_PSILI_AND_OXIA              0x1F24  // ἤ ≡ 1F20 ἠ  0301 $́
#define GREEK_SMALL_LETTER_ETA_WITH_DASIA_AND_OXIA              0x1F25  // ἥ ≡ 1F21 ἡ  0301 $́
#define GREEK_SMALL_LETTER_ETA_WITH_PSILI_AND_PERISPOMENI       0x1F26  // ἦ ≡ 1F20 ἠ  0342 $͂
#define GREEK_SMALL_LETTER_ETA_WITH_DASIA_AND_PERISPOMENI       0x1F27 	// ἧ ≡ 1F21 ἡ  0342 $͂
#define GREEK_CAPITAL_LETTER_ETA_WITH_PSILI                     0x1F28 	// Ἠ ≡ 0397 Η  0313 $̓
#define GREEK_CAPITAL_LETTER_ETA_WITH_DASIA                     0x1F29 	// Ἡ ≡ 0397 Η  0314 $̔
#define GREEK_CAPITAL_LETTER_ETA_WITH_PSILI_AND_VARIA           0x1F2A 	// Ἢ ≡ 1F28 Ἠ  0300 $̀
#define GREEK_CAPITAL_LETTER_ETA_WITH_DASIA_AND_VARIA           0x1F2B 	// Ἣ ≡ 1F29 Ἡ  0300 $̀
#define GREEK_CAPITAL_LETTER_ETA_WITH_PSILI_AND_OXIA            0x1F2C 	// Ἤ ≡ 1F28 Ἠ  0301 $́
#define GREEK_CAPITAL_LETTER_ETA_WITH_DASIA_AND_OXIA            0x1F2D	// Ἥ ≡ 1F29 Ἡ  0301 $́
#define GREEK_CAPITAL_LETTER_ETA_WITH_PSILI_AND_PERISPOMENI     0x1F2E 	// Ἦ ≡ 1F28 Ἠ  0342 $͂
#define GREEK_CAPITAL_LETTER_ETA_WITH_DASIA_AND_PERISPOMENI     0x1F2F 	// Ἧ ≡ 1F29 Ἡ  0342 $͂
#define GREEK_SMALL_LETTER_IOTA_WITH_PSILI                      0x1F30 	// ἰ ≡ 03B9 ι  0313 $̓
#define GREEK_SMALL_LETTER_IOTA_WITH_DASIA                      0x1F31  // ἱ ≡ 03B9 ι  0314 $̔
#define GREEK_SMALL_LETTER_IOTA_WITH_PSILI_AND_VARIA            0x1F32 	// ἲ ≡ 1F30 ἰ  0300 $̀
#define GREEK_SMALL_LETTER_IOTA_WITH_DASIA_AND_VARIA            0x1F33 	// ἳ ≡ 1F31 ἱ  0300 $̀
#define GREEK_SMALL_LETTER_IOTA_WITH_PSILI_AND_OXIA             0x1F34  // ἴ ≡ 1F30 ἰ  0301 $́
#define GREEK_SMALL_LETTER_IOTA_WITH_DASIA_AND_OXIA             0x1F35  // ἵ ≡ 1F31 ἱ  0301 $́
#define GREEK_SMALL_LETTER_IOTA_WITH_PSILI_AND_PERISPOMENI      0x1F36 	// ἶ ≡ 1F30 ἰ  0342 $͂
#define GREEK_SMALL_LETTER_IOTA_WITH_DASIA_AND_PERISPOMENI      0x1F37 	// ἷ ≡ 1F31 ἱ  0342 $͂
#define GREEK_CAPITAL_LETTER_IOTA_WITH_PSILI                    0x1F38  // Ἰ ≡ 0399 Ι  0313 $̓
#define GREEK_CAPITAL_LETTER_IOTA_WITH_DASIA                    0x1F39  // Ἱ ≡ 0399 Ι  0314 $̔
#define GREEK_CAPITAL_LETTER_IOTA_WITH_PSILI_AND_VARIA          0x1F3A  // Ἲ ≡ 1F38 Ἰ  0300 $̀
#define GREEK_CAPITAL_LETTER_IOTA_WITH_DASIA_AND_VARIA          0x1F3B  // Ἳ ≡ 1F39 Ἱ  0300 $̀
#define GREEK_CAPITAL_LETTER_IOTA_WITH_PSILI_AND_OXIA           0x1F3C 	// Ἴ ≡ 1F38 Ἰ  0301 $́
#define GREEK_CAPITAL_LETTER_IOTA_WITH_DASIA_AND_OXIA           0x1F3D 	// Ἵ ≡ 1F39 Ἱ  0301 $́
#define GREEK_CAPITAL_LETTER_IOTA_WITH_PSILI_AND_PERISPOMENI    0x1F3E  // Ἶ ≡ 1F38 Ἰ  0342 $͂
#define GREEK_CAPITAL_LETTER_IOTA_WITH_DASIA_AND_PERISPOMENI    0x1F3F 	// Ἷ ≡ 1F39 Ἱ  0342 $͂
#define GREEK_SMALL_LETTER_OMICRON_WITH_PSILI                   0x1F40  // ὀ ≡ 03BF ο  0313 $̓
#define GREEK_SMALL_LETTER_OMICRON_WITH_DASIA                   0x1F41  // ὁ ≡ 03BF ο  0314 $̔
#define GREEK_SMALL_LETTER_OMICRON_WITH_PSILI_AND_VARIA         0x1F42 	// ὂ ≡ 1F40 ὀ  0300 $̀
#define GREEK_SMALL_LETTER_OMICRON_WITH_DASIA_AND_VARIA         0x1F43 	// ὃ ≡ 1F41 ὁ  0300 $̀
#define GREEK_SMALL_LETTER_OMICRON_WITH_PSILI_AND_OXIA          0x1F44 	// ὄ ≡ 1F40 ὀ  0301 $́
#define GREEK_SMALL_LETTER_OMICRON_WITH_DASIA_AND_OXIA          0x1F45 	// ὅ ≡ 1F41 ὁ  0301 $́
//1F46 " <reserved>
//1F47 " <reserved>
#define GREEK_CAPITAL_LETTER_OMICRON_WITH_PSILI                 0x1F48  // Ὀ ≡ 039F Ο  0313 $̓
#define GREEK_CAPITAL_LETTER_OMICRON_WITH_DASIA                 0x1F49  // Ὁ ≡ 039F Ο  0314 $̔
#define GREEK_CAPITAL_LETTER_OMICRON_WITH_PSILI_AND_VARIA       0x1F4A 	// Ὂ ≡ 1F48 Ὀ  0300 $̀
#define GREEK_CAPITAL_LETTER_OMICRON_WITH_DASIA_AND_VARIA       0x1F4B  // Ὃ ≡ 1F49 Ὁ  0300 $̀
#define GREEK_CAPITAL_LETTER_OMICRON_WITH_PSILI_AND_OXIA        0x1F4C 	// Ὄ ≡ 1F48 Ὀ  0301 $́
#define GREEK_CAPITAL_LETTER_OMICRON_WITH_DASIA_AND_OXIA        0x1F4D 	// Ὅ ≡ 1F49 Ὁ  0301 $́
//1F4E " <reserved>
//1F4F " <reserved>
#define GREEK_SMALL_LETTER_UPSILON_WITH_PSILI                   0x1F50 	// ὐ ≡ 03C5 υ  0313 $̓
#define GREEK_SMALL_LETTER_UPSILON_WITH_DASIA                   0x1F51  // ὑ ≡ 03C5 υ  0314 $̔
#define GREEK_SMALL_LETTER_UPSILON_WITH_PSILI_AND_VARIA         0x1F52  // ὒ ≡ 1F50 ὐ  0300 $̀
#define GREEK_SMALL_LETTER_UPSILON_WITH_DASIA_AND_VARIA         0x1F53  // ὓ ≡ 1F51 ὑ  0300 $̀
#define GREEK_SMALL_LETTER_UPSILON_WITH_PSILI_AND_OXIA          0x1F54  // ὔ ≡ 1F50 ὐ  0301 $́
#define GREEK_SMALL_LETTER_UPSILON_WITH_DASIA_AND_OXIA          0x1F55  // ὕ ≡ 1F51 ὑ  0301 $́
#define GREEK_SMALL_LETTER_UPSILON_WITH_PSILI_AND_PERISPOMENI	0x1F56  // ὖ ≡ 1F50 ὐ  0342 $͂
#define GREEK_SMALL_LETTER_UPSILON_WITH_DASIA_AND_PERISPOMENI	0x1F57 	// ὗ ≡ 1F51 ὑ  0342 $͂
//1F58 " <reserved>
#define GREEK_CAPITAL_LETTER_UPSILON_WITH_DASIA                 0x1F59  // Ὑ ≡ 03A5 Υ  0314 $̔
//1F5A " <reserved>
#define GREEK_CAPITAL_LETTER_UPSILON_WITH_DASIA_AND_VARIA		0x1F5B  // Ὓ ≡ 1F59 Ὑ  0300 $̀
//1F5C " <reserved>
#define GREEK_CAPITAL_LETTER_UPSILON_WITH_DASIA_AND_OXIA		0x1F5D 	// Ὕ ≡ 1F59 Ὑ  0301 $́
//1F5E " <reserved>
#define GREEK_CAPITAL_LETTER_UPSILON_WITH_DASIA_AND_PERISPOMENI	0x1F5F  // Ὗ ≡ 1F59 Ὑ  0342 $͂
#define GREEK_SMALL_LETTER_OMEGA_WITH_PSILI                     0x1F60  // ὠ ≡ 03C9 ω  0313 $̓
#define GREEK_SMALL_LETTER_OMEGA_WITH_DASIA                     0x1F61  // ὡ ≡ 03C9 ω  0314 $̔
#define GREEK_SMALL_LETTER_OMEGA_WITH_PSILI_AND_VARIA           0x1F62  // ὢ ≡ 1F60 ὠ  0300 $̀
#define GREEK_SMALL_LETTER_OMEGA_WITH_DASIA_AND_VARIA           0x1F63  // ὣ ≡ 1F61 ὡ  0300 $̀
#define GREEK_SMALL_LETTER_OMEGA_WITH_PSILI_AND_OXIA            0x1F64 	// ὤ ≡ 1F60 ὠ  0301 $́
#define GREEK_SMALL_LETTER_OMEGA_WITH_DASIA_AND_OXIA             0x1F65  // ὥ ≡ 1F61 ὡ  0301 $́
#define GREEK_SMALL_LETTER_OMEGA_WITH_PSILI_AND_PERISPOMENI     0x1F66 	// ὦ ≡ 1F60 ὠ  0342 $͂
#define GREEK_SMALL_LETTER_OMEGA_WITH_DASIA_AND_PERISPOMENI     0x1F67 	// ὧ ≡ 1F61 ὡ  0342 $͂
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_PSILI                   0x1F68  // Ὠ ≡ 03A9 Ω  0313 $̓
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_DASIA                   0x1F69  // Ὡ ≡ 03A9 Ω  0314 $̔
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_PSILI_AND_VARIA         0x1F6A  // Ὢ ≡ 1F68 Ὠ  0300 $̀
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_DASIA_AND_VARIA         0x1F6B  // Ὣ ≡ 1F69 Ὡ  0300 $̀
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_PSILI_AND_OXIA          0x1F6C  // Ὤ ≡ 1F68 Ὠ  0301 $́
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_DASIA_AND_OXIA          0x1F6D  // Ὥ ≡ 1F69 Ὡ  0301 $́
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_PSILI_AND_PERISPOMENI	0x1F6E 	// Ὦ ≡ 1F68 Ὠ  0342 $͂
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_DASIA_AND_PERISPOMENI	0x1F6F 	// Ὧ ≡ 1F69 Ὡ  0342 $͂
#define GREEK_SMALL_LETTER_ALPHA_WITH_VARIA         0x1F70  // ὰ ≡ 03B1 α  0300 $̀
#define GREEK_SMALL_LETTER_ALPHA_WITH_OXIA          0x1F71  // ά ≡ 03AC ά  GREEK_SMALL_LETTER alpha_WITH tonos
#define GREEK_SMALL_LETTER_EPSILON_WITH_VARIA       0x1F72 	// ὲ ≡ 03B5 ε  0300 $̀
#define GREEK_SMALL_LETTER_EPSILON_WITH_OXIA        0x1F73 	// έ ≡ 03AD έ  GREEK_SMALL_LETTER_EPSILON_WITH tonos
#define GREEK_SMALL_LETTER_ETA_WITH_VARIA           0x1F74  // ὴ ≡ 03B7 η  0300 $̀
#define GREEK_SMALL_LETTER_ETA_WITH_OXIA            0x1F75  // ή ≡ 03AE ή  GREEK_SMALL_LETTER_ETA_WITH tonos
#define GREEK_SMALL_LETTER_IOTA_WITH_VARIA          0x1F76  // ὶ ≡ 03B9 ι  0300 $̀
#define GREEK_SMALL_LETTER_IOTA_WITH_OXIA           0x1F77  // ί ≡ 03AF ί  GREEK_SMALL_LETTER_IOTA_WITH tonos
#define GREEK_SMALL_LETTER_OMICRON_WITH_VARIA		0x1F78  // ὸ ≡ 03BF ο  0300 $̀
#define GREEK_SMALL_LETTER_OMICRON_WITH_OXIA		0x1F79  // ό ≡ 03CC ό  GREEK_SMALL_LETTER_OMICRON_WITH tonos
#define GREEK_SMALL_LETTER_UPSILON_WITH_VARIA		0x1F7A  // ὺ ≡ 03C5 υ  0300 $̀
#define GREEK_SMALL_LETTER_UPSILON_WITH_OXIA		0x1F7B  // ύ ≡ 03CD ύ  GREEK_SMALL_LETTER_UPSILON_WITH tonos
#define GREEK_SMALL_LETTER_OMEGA_WITH_VARIA         0x1F7C  // ὼ ≡ 03C9 ω  0300 $̀
#define GREEK_SMALL_LETTER_OMEGA_WITH_OXIA          0x1F7D  // ώ ≡ 03CE ώ  GREEK_SMALL_LETTER_OMEGA_WITH tonos
//1F7E " <reserved>
//1F7F " <reserved>
#define GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_YPOGEGRAMMENI                       0x1F80  // ᾀ ≡ 1F00 ἀ  0345 $ͅ
#define GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_YPOGEGRAMMENI                       0x1F81  // ᾁ ≡ 1F01 ἁ  0345 $ͅ
#define GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_VARIA_AND_YPOGEGRAMMENI             0x1F82  // ᾂ ≡ 1F02 ἂ  0345 $ͅ
#define GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_VARIA_AND_YPOGEGRAMMENI             0x1F83  // ᾃ ≡ 1F03 ἃ  0345 $ͅ
#define GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_OXIA_AND_YPOGEGRAMMENI              0x1F84  // ᾄ ≡ 1F04 ἄ  0345 $ͅ
#define GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_OXIA_AND_YPOGEGRAMMENI              0x1F85  // ᾅ ≡ 1F05 ἅ  0345 $ͅ
#define GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_PERISPOMENI_AND_YPOGEGRAMMENI       0x1F86  // ᾆ ≡ 1F06 ἆ  0345 $ͅ
#define GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_PERISPOMENI_AND_YPOGEGRAMMENI       0x1F87  // ᾇ ≡ 1F07 ἇ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_PSILI_AND_PROSGEGRAMMENI                    0x1F88  // ᾈ ≡ 1F08 Ἀ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_DASIA_AND_PROSGEGRAMMENI                    0x1F89  // ᾉ ≡ 1F09 Ἁ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_PSILI_AND_VARIA_AND_PROSGEGRAMMENI          0x1F8A  // ᾊ ≡ 1F0A Ἂ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_DASIA_AND_VARIA_AND_PROSGEGRAMMENI          0x1F8B  // ᾋ ≡ 1F0B Ἃ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_PSILI_AND_OXIA_AND_PROSGEGRAMMENI           0x1F8C  // ᾌ ≡ 1F0C Ἄ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_DASIA_AND_OXIA_AND_PROSGEGRAMMENI           0x1F8D  // ᾍ ≡ 1F0D Ἅ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_PSILI_AND_PERISPOMENI_AND_PROSGEGRAMMENI	0x1F8E 	// ᾎ ≡ 1F0E Ἆ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_DASIA_AND_PERISPOMENI_AND_PROSGEGRAMMENI	0x1F8F 	// ᾏ ≡ 1F0F Ἇ  0345 $ͅ
#define GREEK_SMALL_LETTER_ETA_WITH_PSILI_AND_YPOGEGRAMMENI                         0x1F90  // ᾐ ≡ 1F20 ἠ  0345 $ͅ
#define GREEK_SMALL_LETTER_ETA_WITH_DASIA_AND_YPOGEGRAMMENI                         0x1F91  // ᾑ ≡ 1F21 ἡ  0345 $ͅ
#define GREEK_SMALL_LETTER_ETA_WITH_PSILI_AND_VARIA_AND_YPOGEGRAMMENI               0x1F92 	// ᾒ ≡ 1F22 ἢ  0345 $ͅ
#define GREEK_SMALL_LETTER_ETA_WITH_DASIA_AND_VARIA_AND_YPOGEGRAMMENI               0x1F93 	// ᾓ ≡ 1F23 ἣ  0345 $ͅ
#define GREEK_SMALL_LETTER_ETA_WITH_PSILI_AND_OXIA_AND_YPOGEGRAMMENI                0x1F94 	// ᾔ ≡ 1F24 ἤ  0345 $ͅ
#define GREEK_SMALL_LETTER_ETA_WITH_DASIA_AND_OXIA_AND_YPOGEGRAMMENI                0x1F95 	// ᾕ ≡ 1F25 ἥ  0345 $ͅ
#define GREEK_SMALL_LETTER_ETA_WITH_PSILI_AND_PERISPOMENI_AND_YPOGEGRAMMENI         0x1F96  // ᾖ ≡ 1F26 ἦ  0345 $ͅ
#define GREEK_SMALL_LETTER_ETA_WITH_DASIA_AND_PERISPOMENI_AND_YPOGEGRAMMENI         0x1F97  // ᾗ ≡ 1F27 ἧ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_ETA_WITH_PSILI_AND_PROSGEGRAMMENI                      0x1F98  // ᾘ ≡ 1F28 Ἠ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_ETA_WITH_DASIA_AND_PROSGEGRAMMENI                      0x1F99  // ᾙ ≡ 1F29 Ἡ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_ETA_WITH_PSILI_AND_VARIA_AND_PROSGEGRAMMENI            0x1F9A 	// ᾚ ≡ 1F2A Ἢ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_ETA_WITH_DASIA_AND_VARIA_AND_PROSGEGRAMMENI            0x1F9B 	// ᾛ ≡ 1F2B Ἣ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_ETA_WITH_PSILI_AND_OXIA_AND_PROSGEGRAMMENI             0x1F9C  // ᾜ ≡ 1F2C Ἤ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_ETA_WITH_DASIA_AND_OXIA_AND_PROSGEGRAMMENI             0x1F9D  // ᾝ ≡ 1F2D Ἥ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_ETA_WITH_PSILI_AND_PERISPOMENI_AND_PROSGEGRAMMENI      0x1F9E 	// ᾞ ≡ 1F2E Ἦ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_ETA_WITH_DASIA_AND_PERISPOMENI_AND_PROSGEGRAMMENI      0x1F9F 	// ᾟ ≡ 1F2F Ἧ  0345 $ͅ
#define GREEK_SMALL_LETTER_OMEGA_WITH_PSILI_AND_YPOGEGRAMMENI                       0x1FA0  // ᾠ ≡ 1F60 ὠ  0345 $ͅ
#define GREEK_SMALL_LETTER_OMEGA_WITH_DASIA_AND_YPOGEGRAMMENI                       0x1FA1  // ᾡ ≡ 1F61 ὡ  0345 $ͅ
#define GREEK_SMALL_LETTER_OMEGA_WITH_PSILI_AND_VARIA_AND_YPOGEGRAMMENI             0x1FA2  // ᾢ ≡ 1F62 ὢ  0345 $ͅ
#define GREEK_SMALL_LETTER_OMEGA_WITH_DASIA_AND_VARIA_AND_YPOGEGRAMMENI             0x1FA3  // ᾣ ≡ 1F63 ὣ  0345 $ͅ
#define GREEK_SMALL_LETTER_OMEGA_WITH_PSILI_AND_OXIA_AND_YPOGEGRAMMENI              0x1FA4  // ᾤ ≡ 1F64 ὤ  0345 $ͅ
#define GREEK_SMALL_LETTER_OMEGA_WITH_DASIA_AND_OXIA_AND_YPOGEGRAMMENI              0x1FA5  // ᾥ ≡ 1F65 ὥ  0345 $ͅ
#define GREEK_SMALL_LETTER_OMEGA_WITH_PSILI_AND_PERISPOMENI_AND_YPOGEGRAMMENI       0x1FA6 	// ᾦ ≡ 1F66 ὦ  0345 $ͅ
#define GREEK_SMALL_LETTER_OMEGA_WITH_DASIA_AND_PERISPOMENI_AND_YPOGEGRAMMENI       0x1FA7 	// ᾧ ≡ 1F67 ὧ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_PSILI_AND_PROSGEGRAMMENI                    0x1FA8 	// ᾨ ≡ 1F68 Ὠ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_DASIA_AND_PROSGEGRAMMENI                    0x1FA9 	// ᾩ ≡ 1F69 Ὡ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_PSILI_AND_VARIA_AND_PROSGEGRAMMENI          0x1FAA 	// ᾪ ≡ 1F6A Ὢ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_DASIA_AND_VARIA_AND_PROSGEGRAMMENI          0x1FAB  // ᾫ ≡ 1F6B Ὣ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_PSILI_AND_OXIA_AND_PROSGEGRAMMENI           0x1FAC  // ᾬ ≡ 1F6C Ὤ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_DASIA_AND_OXIA_AND_PROSGEGRAMMENI           0x1FAD  // ᾭ ≡ 1F6D Ὥ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_PSILI_AND_PERISPOMENI_AND_PROSGEGRAMMENI	0x1FAE 	// ᾮ ≡ 1F6E Ὦ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_DASIA_AND_PERISPOMENI_AND_PROSGEGRAMMENI	0x1FAF 	// ᾯ ≡ 1F6F Ὧ  0345 $ͅ
#define GREEK_SMALL_LETTER_ALPHA_WITH_VRACHY                                        0x1FB0  // ᾰ ≡ 03B1 α  0306 $̆
#define GREEK_SMALL_LETTER_ALPHA_WITH_MACRON                                        0x1FB1  // ᾱ ≡ 03B1 α  0304 $̄
#define GREEK_SMALL_LETTER_ALPHA_WITH_VARIA_AND_YPOGEGRAMMENI                       0x1FB2  // ᾲ ≡ 1F70 ὰ  0345 $ͅ
#define GREEK_SMALL_LETTER_ALPHA_WITH_YPOGEGRAMMENI                     0x1FB3  // ᾳ ≡ 03B1 α  0345 $ͅ
#define GREEK_SMALL_LETTER_ALPHA_WITH_OXIA_AND_YPOGEGRAMMENI            0x1FB4 	// ᾴ ≡ 03AC ά  0345 $ͅ
//1FB5 " <reserved>
#define GREEK_SMALL_LETTER_ALPHA_WITH_PERISPOMENI                       0x1FB6 	// ᾶ ≡ 03B1 α  0342 $͂
#define GREEK_SMALL_LETTER_ALPHA_WITH_PERISPOMENI_AND_YPOGEGRAMMENI		0x1FB7  // ᾷ ≡ 1FB6 ᾶ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_VRACHY                          0x1FB8  // Ᾰ ≡ 0391 Α  0306 $̆
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_MACRON                          0x1FB9 	// Ᾱ ≡ 0391 Α  0304 $̄
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_VARIA                           0x1FBA 	// Ὰ ≡ 0391 Α  0300 $̀
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_OXIA                            0x1FBB 	// Ά ≡ 0386 Ά  GREEK_CAPITAL_LETTER alpha_WITH tonos
#define GREEK_CAPITAL_LETTER_ALPHA_WITH_PROSGEGRAMMENI                  0x1FBC 	// ᾼ ≡ 0391 Α  0345 $ͅ
#define GREEK KORONIS                                                   0x1FBD 	// ᾽ ≈ 0020   0313 $̓
#define GREEK_PROSGEGRAMMENI                                            0x1FBE  // ι ≡ 03B9 ι  GREEK_SMALL_LETTER_IOTA
#define GREEK_PSILI                                                     0x1FBF  // ᾿ → 02BC ʼ  modifier letter apostrophe ≈ 0020   0313 $̓
#define GREEK_PERISPOMENI                                               0x1FC0  // ῀ ≈ 0020   0342 $͂
#define GREEK_DIALYTIKA_AND_PERISPOMENI                                 0x1FC1  // ῁ ≡ 00A8 ¨  0342 $͂
#define GREEK_SMALL_LETTER_ETA_WITH_VARIA_AND_YPOGEGRAMMENI             0x1FC2  // ῂ ≡ 1F74 ὴ  0345 $ͅ
#define GREEK_SMALL_LETTER_ETA_WITH_YPOGEGRAMMENI                       0x1FC3  // ῃ ≡ 03B7 η  0345 $ͅ
#define GREEK_SMALL_LETTER_ETA_WITH_OXIA_AND_YPOGEGRAMMENI              0x1FC4  // ῄ ≡ 03AE ή  0345 $ͅ
//1FC5 " <reserved>
#define GREEK_SMALL_LETTER_ETA_WITH_PERISPOMENI                         0x1FC6 	// ῆ ≡ 03B7 η  0342 $͂
#define GREEK_SMALL_LETTER_ETA_WITH_PERISPOMENI_AND_YPOGEGRAMMENI       0x1FC7 	// ῇ ≡ 1FC6 ῆ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_EPSILON_WITH_VARIA                         0x1FC8  // Ὲ ≡ 0395 Ε  0300 $̀
#define GREEK_CAPITAL_LETTER_EPSILON_WITH_OXIA                          0x1FC9 	// Έ ≡ 0388 Έ  GREEK_CAPITAL_LETTER epsilon_WITH tonos
#define GREEK_CAPITAL_LETTER_ETA_WITH_VARIA                             0x1FCA  // Ὴ ≡ 0397 Η  0300 $̀
#define GREEK_CAPITAL_LETTER_ETA_WITH_OXIA                              0x1FCB  // Ή ≡ 0389 Ή  GREEK_CAPITAL_LETTER_ETA_WITH tonos
#define GREEK_CAPITAL_LETTER_ETA_WITH_PROSGEGRAMMENI                    0x1FCC  // ῌ ≡ 0397 Η  0345 $ͅ
#define GREEK_PSILI_AND_VARIA                                           0x1FCD  // ῍ ≡ 1FBF ᾿  0300 $̀
#define GREEK_PSILI_AND_OXIA                                            0x1FCE  // ῎ ≡ 1FBF ᾿  0301 $́
#define GREEK_PSILI_AND_PERISPOMENI                                     0x1FCF  // ῏ ≡ 1FBF ᾿  0342 $͂
#define GREEK_SMALL_LETTER_IOTA_WITH_VRACHY                             0x1FD0  // ῐ ≡ 03B9 ι  0306 $̆
#define GREEK_SMALL_LETTER_IOTA_WITH_MACRON                             0x1FD1  // ῑ ≡ 03B9 ι  0304 $̄
#define GREEK_SMALL_LETTER_IOTA_WITH_DIALYTIKA_AND_VARIA                0x1FD2  // ῒ ≡ 03CA ϊ  0300 $̀
#define GREEK_SMALL_LETTER_IOTA_WITH_DIALYTIKA_AND_OXIA                 0x1FD3  // ΐ ≡ 0390 ΐ  GREEK_SMALL_LETTER_IOTA_WITH_DIALYTIKAand tonos
//1FD4 " <reserved>
//1FD5 " <reserved>
#define GREEK_SMALL_LETTER_IOTA_WITH_PERISPOMENI                        0x1FD6  // ῖ ≡ 03B9 ι  0342 $͂
#define GREEK_SMALL_LETTER_IOTA_WITH_DIALYTIKA_AND_PERISPOMENI          0x1FD7 	// ῗ ≡ 03CA ϊ  0342 $͂
#define GREEK_CAPITAL_LETTER_IOTA_WITH_VRACHY                           0x1FD8  // Ῐ ≡ 0399 Ι  0306 $̆
#define GREEK_CAPITAL_LETTER_IOTA_WITH_MACRON                           0x1FD9  // Ῑ ≡ 0399 Ι  0304 $̄
#define GREEK_CAPITAL_LETTER_IOTA_WITH_VARIA                            0x1FDA  // Ὶ ≡ 0399 Ι  0300 $̀
#define GREEK_CAPITAL_LETTER_IOTA_WITH_OXIA                             0x1FDB  // Ί ≡ 038A Ί  GREEK_CAPITAL_LETTER_IOTA_WITH tonos
//1FDC " <reserved>
#define GREEK_DASIA_AND_VARIA                                           0x1FDD  // ῝ ≡ 1FFE ῾  0300 $̀
#define GREEK_DASIA_AND_OXIA                                            0x1FDE  // ῞ ≡ 1FFE ῾  0301 $́
#define GREEK_DASIA_AND_PERISPOMENI                                     0x1FDF  // ῟ ≡ 1FFE ῾  0342 $͂
#define GREEK_SMALL_LETTER_UPSILON_WITH_VRACHY                          0x1FE0  // ῠ ≡ 03C5 υ  0306 $̆
#define GREEK_SMALL_LETTER_UPSILON_WITH_MACRON                          0x1FE1  // ῡ ≡ 03C5 υ  0304 $̄
#define GREEK_SMALL_LETTER_UPSILON_WITH_DIALYTIKA_AND_VARIA             0x1FE2 	// ῢ ≡ 03CB ϋ  0300 $̀
#define GREEK_SMALL_LETTER_UPSILON_WITH_DIALYTIKA_AND_OXIA              0x1FE3  // ΰ ≡ 03B0 ΰ GREEK_SMALL_LETTER_UPSILON_WITH_DIALYTIKA and tonos
#define GREEK_SMALL_LETTER_RHO_WITH_PSILI                               0x1FE4  // ῤ ≡ 03C1 ρ  0313 $̓
#define GREEK_SMALL_LETTER_RHO_WITH_DASIA                               0x1FE5  // ῥ ≡ 03C1 ρ  0314 $̔
#define GREEK_SMALL_LETTER_UPSILON_WITH_PERISPOMENI                     0x1FE6 	// ῦ ≡ 03C5 υ  0342 $͂
#define GREEK_SMALL_LETTER_UPSILON_WITH_DIALYTIKA_AND_PERISPOMENI       0x1FE7 	// ῧ ≡ 03CB ϋ  0342 $͂
#define GREEK_CAPITAL_LETTER_UPSILON_WITH_VRACHY                        0x1FE8  // Ῠ ≡ 03A5 Υ  0306 $̆
#define GREEK_CAPITAL_LETTER_UPSILON_WITH_MACRON                        0x1FE9  // Ῡ ≡ 03A5 Υ  0304 $̄
#define GREEK_CAPITAL_LETTER_UPSILON_WITH_VARIA                         0x1FEA  // Ὺ ≡ 03A5 Υ  0300 $̀
#define GREEK_CAPITAL_LETTER_UPSILON_WITH_OXIA                          0x1FEB  // Ύ ≡ 038E Ύ  GREEK_CAPITAL_LETTER_UPSILON_WITH tonos
#define GREEK_CAPITAL_LETTER_RHO_WITH_DASIA                             0x1FEC  // Ῥ ≡ 03A1 Ρ  0314 $̔
#define GREEK_DIALYTIKA_AND_VARIA                                       0x1FED  // ῭ ≡ 00A8 ¨  0300 $̀
#define GREEK_DIALYTIKA_AND_OXIA                                        0x1FEE  // ΅ ≡ 0385 ΅  greek_DIALYTIKA tonos
#define GREEK_VARIA                                                     0x1FEF  // ` ≡ 0060 `  grave accent
//1FF0 " <reserved>
//1FF1 " <reserved>
#define GREEK_SMALL_LETTER_OMEGA_WITH_VARIA_AND_YPOGEGRAMMENI           0x1FF2 	// ῲ ≡ 1F7C ὼ  0345 $ͅ
#define GREEK_SMALL_LETTER_OMEGA_WITH_YPOGEGRAMMENI                     0x1FF3  // ῳ ≡ 03C9 ω  0345 $ͅ
#define GREEK_SMALL_LETTER_OMEGA_WITH_OXIA_AND_YPOGEGRAMMENI            0x1FF4  // ῴ ≡ 03CE ώ  0345 $ͅ
//1FF5 " <reserved>
#define GREEK_SMALL_LETTER_OMEGA_WITH_PERISPOMENI                       0x1FF6 	// ῶ ≡ 03C9 ω  0342 $͂
#define GREEK_SMALL_LETTER_OMEGA_WITH_PERISPOMENI_AND_YPOGEGRAMMENI     0x1FF7 	// ῷ ≡ 1FF6 ῶ  0345 $ͅ
#define GREEK_CAPITAL_LETTER_OMICRON_WITH_VARIA                         0x1FF8  // Ὸ ≡ 039F Ο  0300 $̀
#define GREEK_CAPITAL_LETTER_OMICRON_WITH_OXIA                          0x1FF9  // Ό ≡ 038C Ό  GREEK_CAPITAL_LETTER_OMICRON_WITH tonos
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_VARIA                           0x1FFA  // Ὼ ≡ 03A9 Ω  0300 $̀
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_OXIA                            0x1FFB  // Ώ ≡ 038F Ώ  GREEK_CAPITAL_LETTER_OMEGA_WITH tonos
#define GREEK_CAPITAL_LETTER_OMEGA_WITH_PROSGEGRAMMENI                  0x1FFC 	// ῼ ≡ 03A9 Ω  0345 $ͅ
#define GREEK_OXIA                                                      0x1FFD  // ´ ≡ 00B4 ´  acute accent
#define GREEK_DASIA                                                     0x1FFE  // ῾ → 02BD ʽ  modifier letter reversed comma ≈ 0020  0314 $̔

//PRIVATE USE AREA PRE-COMPOSED GLYPHS
#define PUA_GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_OXIA_AND_MACRON     0xEB07
#define PUA_GREEK_SMALL_LETTER_ALPHA_WITH_VARIA_AND_MACRON              0xEAF0
#define PUA_GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_VARIA_AND_MACRON    0xEAF3
#define PUA_GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_VARIA_AND_MACRON    0xEAF4
#define PUA_GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_OXIA_AND_BREVE      0xEAFB
#define PUA_GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_VARIA_AND_BREVE     0xEAFC
#define PUA_GREEK_SMALL_LETTER_ALPHA_WITH_OXIA_AND_MACRON               0xEB00
#define PUA_GREEK_SMALL_LETTER_ALPHA_WITH_PSILI_AND_MACRON              0xEB04
#define PUA_GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_MACRON              0xEB05
#define PUA_GREEK_SMALL_LETTER_ALPHA_WITH_DASIA_AND_OXIA_AND_MACRON2    0xEB09

#define PUA_GREEK_SMALL_LETTER_IOTA_WITH_VARIA_AND_MACRON               0xEB38
#define PUA_GREEK_SMALL_LETTER_IOTA_WITH_OXIA_AND_MACRON                0xEB39
#define PUA_GREEK_SMALL_LETTER_IOTA_WITH_PSILI_AND_MACRON               0xEB3C
#define PUA_GREEK_SMALL_LETTER_IOTA_WITH_PSILI_AND_OXIA_AND_MACRON      0xEB3D
#define PUA_GREEK_SMALL_LETTER_IOTA_WITH_DASIA_AND_MACRON               0xEB3E
#define PUA_GREEK_SMALL_LETTER_IOTA_WITH_DASIA_AND_OXIA_AND_MACRON      0xEB3F
#define PUA_GREEK_SMALL_LETTER_IOTA_WITH_PSILI_AND_VARIA_AND_MACRON     0xEB54
#define PUA_GREEK_SMALL_LETTER_IOTA_WITH_DASIA_AND_VARIA_AND_MACRON     0xEB55

#define PUA_GREEK_SMALL_LETTER_UPSILON_WITH_VARIA_AND_MACRON            0xEB6F
#define PUA_GREEK_SMALL_LETTER_UPSILON_WITH_PSILI_AND_VARIA_AND_MACRON  0xEB71
#define PUA_GREEK_SMALL_LETTER_UPSILON_WITH_DASIA_AND_VARIA_AND_MACRON  0xEB75
#define PUA_GREEK_SMALL_LETTER_UPSILON_WITH_OXIA_AND_MACRON             0xEB7A
#define PUA_GREEK_SMALL_LETTER_UPSILON_WITH_PSILI_AND_MACRON            0xEB7D
#define PUA_GREEK_SMALL_LETTER_UPSILON_WITH_DASIA_AND_MACRON            0xEB7E
#define PUA_GREEK_SMALL_LETTER_UPSILON_WITH_PSILI_AND_OXIA_AND_MACRON   0xEB7F
#define PUA_GREEK_SMALL_LETTER_UPSILON_WITH_DASIA_AND_OXIA_AND_MACRON   0xEB80

#endif

