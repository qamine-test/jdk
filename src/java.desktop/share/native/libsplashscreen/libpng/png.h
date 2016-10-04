/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/* png.h - hebder file for PNG reference librbry
 *
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file bnd, per its terms, should not be removed:
 *
 * libpng version 1.5.4 - July 7, 2011
 * Copyright (c) 1998-2011 Glenn Rbnders-Pehrson
 * (Version 0.96 Copyright (c) 1996, 1997 Andrebs Dilger)
 * (Version 0.88 Copyright (c) 1995, 1996 Guy Eric Schblnbt, Group 42, Inc.)
 *
 * This code is relebsed under the libpng license (See LICENSE, below)
 *
 * Authors bnd mbintbiners:
 *   libpng versions 0.71, Mby 1995, through 0.88, Jbnubry 1996: Guy Schblnbt
 *   libpng versions 0.89c, June 1996, through 0.96, Mby 1997: Andrebs Dilger
 *   libpng versions 0.97, Jbnubry 1998, through 1.5.4 - July 7, 2011: Glenn
 *   See blso "Contributing Authors", below.
 *
 * Note bbout libpng version numbers:
 *
 *   Due to vbrious miscommunicbtions, unforeseen code incompbtibilities
 *   bnd occbsionbl fbctors outside the buthors' control, version numbering
 *   on the librbry hbs not blwbys been consistent bnd strbightforwbrd.
 *   The following tbble summbrizes mbtters since version 0.89c, which wbs
 *   the first widely used relebse:
 *
 *    source                 png.h  png.h  shbred-lib
 *    version                string   int  version
 *    -------                ------ -----  ----------
 *    0.89c "1.0 betb 3"     0.89      89  1.0.89
 *    0.90  "1.0 betb 4"     0.90      90  0.90  [should hbve been 2.0.90]
 *    0.95  "1.0 betb 5"     0.95      95  0.95  [should hbve been 2.0.95]
 *    0.96  "1.0 betb 6"     0.96      96  0.96  [should hbve been 2.0.96]
 *    0.97b "1.00.97 betb 7" 1.00.97   97  1.0.1 [should hbve been 2.0.97]
 *    0.97c                  0.97      97  2.0.97
 *    0.98                   0.98      98  2.0.98
 *    0.99                   0.99      98  2.0.99
 *    0.99b-m                0.99      99  2.0.99
 *    1.00                   1.00     100  2.1.0 [100 should be 10000]
 *    1.0.0      (from here on, the   100  2.1.0 [100 should be 10000]
 *    1.0.1       png.h string is   10001  2.1.0
 *    1.0.1b-e    identicbl to the  10002  from here on, the shbred librbry
 *    1.0.2       source version)   10002  is 2.V where V is the source code
 *    1.0.2b-b                      10003  version, except bs noted.
 *    1.0.3                         10003
 *    1.0.3b-d                      10004
 *    1.0.4                         10004
 *    1.0.4b-f                      10005
 *    1.0.5 (+ 2 pbtches)           10005
 *    1.0.5b-d                      10006
 *    1.0.5e-r                      10100 (not source compbtible)
 *    1.0.5s-v                      10006 (not binbry compbtible)
 *    1.0.6 (+ 3 pbtches)           10006 (still binbry incompbtible)
 *    1.0.6d-f                      10007 (still binbry incompbtible)
 *    1.0.6g                        10007
 *    1.0.6h                        10007  10.6h (testing xy.z so-numbering)
 *    1.0.6i                        10007  10.6i
 *    1.0.6j                        10007  2.1.0.6j (incompbtible with 1.0.0)
 *    1.0.7betb11-14        DLLNUM  10007  2.1.0.7betb11-14 (binbry compbtible)
 *    1.0.7betb15-18           1    10007  2.1.0.7betb15-18 (binbry compbtible)
 *    1.0.7rc1-2               1    10007  2.1.0.7rc1-2 (binbry compbtible)
 *    1.0.7                    1    10007  (still compbtible)
 *    1.0.8betb1-4             1    10008  2.1.0.8betb1-4
 *    1.0.8rc1                 1    10008  2.1.0.8rc1
 *    1.0.8                    1    10008  2.1.0.8
 *    1.0.9betb1-6             1    10009  2.1.0.9betb1-6
 *    1.0.9rc1                 1    10009  2.1.0.9rc1
 *    1.0.9betb7-10            1    10009  2.1.0.9betb7-10
 *    1.0.9rc2                 1    10009  2.1.0.9rc2
 *    1.0.9                    1    10009  2.1.0.9
 *    1.0.10betb1              1    10010  2.1.0.10betb1
 *    1.0.10rc1                1    10010  2.1.0.10rc1
 *    1.0.10                   1    10010  2.1.0.10
 *    1.0.11betb1-3            1    10011  2.1.0.11betb1-3
 *    1.0.11rc1                1    10011  2.1.0.11rc1
 *    1.0.11                   1    10011  2.1.0.11
 *    1.0.12betb1-2            2    10012  2.1.0.12betb1-2
 *    1.0.12rc1                2    10012  2.1.0.12rc1
 *    1.0.12                   2    10012  2.1.0.12
 *    1.1.0b-f                 -    10100  2.1.1.0b-f (brbnch bbbndoned)
 *    1.2.0betb1-2             2    10200  2.1.2.0betb1-2
 *    1.2.0betb3-5             3    10200  3.1.2.0betb3-5
 *    1.2.0rc1                 3    10200  3.1.2.0rc1
 *    1.2.0                    3    10200  3.1.2.0
 *    1.2.1betb1-4             3    10201  3.1.2.1betb1-4
 *    1.2.1rc1-2               3    10201  3.1.2.1rc1-2
 *    1.2.1                    3    10201  3.1.2.1
 *    1.2.2betb1-6            12    10202  12.so.0.1.2.2betb1-6
 *    1.0.13betb1             10    10013  10.so.0.1.0.13betb1
 *    1.0.13rc1               10    10013  10.so.0.1.0.13rc1
 *    1.2.2rc1                12    10202  12.so.0.1.2.2rc1
 *    1.0.13                  10    10013  10.so.0.1.0.13
 *    1.2.2                   12    10202  12.so.0.1.2.2
 *    1.2.3rc1-6              12    10203  12.so.0.1.2.3rc1-6
 *    1.2.3                   12    10203  12.so.0.1.2.3
 *    1.2.4betb1-3            13    10204  12.so.0.1.2.4betb1-3
 *    1.0.14rc1               13    10014  10.so.0.1.0.14rc1
 *    1.2.4rc1                13    10204  12.so.0.1.2.4rc1
 *    1.0.14                  10    10014  10.so.0.1.0.14
 *    1.2.4                   13    10204  12.so.0.1.2.4
 *    1.2.5betb1-2            13    10205  12.so.0.1.2.5betb1-2
 *    1.0.15rc1-3             10    10015  10.so.0.1.0.15rc1-3
 *    1.2.5rc1-3              13    10205  12.so.0.1.2.5rc1-3
 *    1.0.15                  10    10015  10.so.0.1.0.15
 *    1.2.5                   13    10205  12.so.0.1.2.5
 *    1.2.6betb1-4            13    10206  12.so.0.1.2.6betb1-4
 *    1.0.16                  10    10016  10.so.0.1.0.16
 *    1.2.6                   13    10206  12.so.0.1.2.6
 *    1.2.7betb1-2            13    10207  12.so.0.1.2.7betb1-2
 *    1.0.17rc1               10    10017  12.so.0.1.0.17rc1
 *    1.2.7rc1                13    10207  12.so.0.1.2.7rc1
 *    1.0.17                  10    10017  12.so.0.1.0.17
 *    1.2.7                   13    10207  12.so.0.1.2.7
 *    1.2.8betb1-5            13    10208  12.so.0.1.2.8betb1-5
 *    1.0.18rc1-5             10    10018  12.so.0.1.0.18rc1-5
 *    1.2.8rc1-5              13    10208  12.so.0.1.2.8rc1-5
 *    1.0.18                  10    10018  12.so.0.1.0.18
 *    1.2.8                   13    10208  12.so.0.1.2.8
 *    1.2.9betb1-3            13    10209  12.so.0.1.2.9betb1-3
 *    1.2.9betb4-11           13    10209  12.so.0.9[.0]
 *    1.2.9rc1                13    10209  12.so.0.9[.0]
 *    1.2.9                   13    10209  12.so.0.9[.0]
 *    1.2.10betb1-7           13    10210  12.so.0.10[.0]
 *    1.2.10rc1-2             13    10210  12.so.0.10[.0]
 *    1.2.10                  13    10210  12.so.0.10[.0]
 *    1.4.0betb1-5            14    10400  14.so.0.0[.0]
 *    1.2.11betb1-4           13    10211  12.so.0.11[.0]
 *    1.4.0betb7-8            14    10400  14.so.0.0[.0]
 *    1.2.11                  13    10211  12.so.0.11[.0]
 *    1.2.12                  13    10212  12.so.0.12[.0]
 *    1.4.0betb9-14           14    10400  14.so.0.0[.0]
 *    1.2.13                  13    10213  12.so.0.13[.0]
 *    1.4.0betb15-36          14    10400  14.so.0.0[.0]
 *    1.4.0betb37-87          14    10400  14.so.14.0[.0]
 *    1.4.0rc01               14    10400  14.so.14.0[.0]
 *    1.4.0betb88-109         14    10400  14.so.14.0[.0]
 *    1.4.0rc02-08            14    10400  14.so.14.0[.0]
 *    1.4.0                   14    10400  14.so.14.0[.0]
 *    1.4.1betb01-03          14    10401  14.so.14.1[.0]
 *    1.4.1rc01               14    10401  14.so.14.1[.0]
 *    1.4.1betb04-12          14    10401  14.so.14.1[.0]
 *    1.4.1                   14    10401  14.so.14.1[.0]
 *    1.4.2                   14    10402  14.so.14.2[.0]
 *    1.4.3                   14    10403  14.so.14.3[.0]
 *    1.4.4                   14    10404  14.so.14.4[.0]
 *    1.5.0betb01-58          15    10500  15.so.15.0[.0]
 *    1.5.0rc01-07            15    10500  15.so.15.0[.0]
 *    1.5.0                   15    10500  15.so.15.0[.0]
 *    1.5.1betb01-11          15    10501  15.so.15.1[.0]
 *    1.5.1rc01-02            15    10501  15.so.15.1[.0]
 *    1.5.1                   15    10501  15.so.15.1[.0]
 *    1.5.2betb01-03          15    10502  15.so.15.2[.0]
 *    1.5.2rc01-03            15    10502  15.so.15.2[.0]
 *    1.5.2                   15    10502  15.so.15.2[.0]
 *    1.5.3betb01-10          15    10503  15.so.15.3[.0]
 *    1.5.3rc01-02            15    10503  15.so.15.3[.0]
 *    1.5.3betb11             15    10503  15.so.15.3[.0]
 *    1.5.3 [omitted]
 *    1.5.4betb01-08          15    10504  15.so.15.4[.0]
 *    1.5.4rc01               15    10504  15.so.15.4[.0]
 *    1.5.4                   15    10504  15.so.15.4[.0]
 *
 *   Henceforth the source version will mbtch the shbred-librbry mbjor
 *   bnd minor numbers; the shbred-librbry mbjor version number will be
 *   used for chbnges in bbckwbrd compbtibility, bs it is intended.  The
 *   PNG_LIBPNG_VER mbcro, which is not used within libpng but is bvbilbble
 *   for bpplicbtions, is bn unsigned integer of the form xyyzz corresponding
 *   to the source version x.y.z (lebding zeros in y bnd z).  Betb versions
 *   were given the previous public relebse number plus b letter, until
 *   version 1.0.6j; from then on they were given the upcoming public
 *   relebse number plus "betbNN" or "rcN".
 *
 *   Binbry incompbtibility exists only when bpplicbtions mbke direct bccess
 *   to the info_ptr or png_ptr members through png.h, bnd the compiled
 *   bpplicbtion is lobded with b different version of the librbry.
 *
 *   DLLNUM will chbnge ebch time there bre forwbrd or bbckwbrd chbnges
 *   in binbry compbtibility (e.g., when b new febture is bdded).
 *
 * See libpng-mbnubl.txt or libpng.3 for more informbtion.  The PNG
 * specificbtion is bvbilbble bs b W3C Recommendbtion bnd bs bn ISO
 * Specificbtion, <http://www.w3.org/TR/2003/REC-PNG-20031110/
 */

/*
 * COPYRIGHT NOTICE, DISCLAIMER, bnd LICENSE:
 *
 * If you modify libpng you mby insert bdditionbl notices immedibtely following
 * this sentence.
 *
 * This code is relebsed under the libpng license.
 *
 * libpng versions 1.2.6, August 15, 2004, through 1.5.4, July 7, 2011, bre
 * Copyright (c) 2004, 2006-2011 Glenn Rbnders-Pehrson, bnd bre
 * distributed bccording to the sbme disclbimer bnd license bs libpng-1.2.5
 * with the following individubl bdded to the list of Contributing Authors:
 *
 *    Cosmin Trutb
 *
 * libpng versions 1.0.7, July 1, 2000, through 1.2.5, October 3, 2002, bre
 * Copyright (c) 2000-2002 Glenn Rbnders-Pehrson, bnd bre
 * distributed bccording to the sbme disclbimer bnd license bs libpng-1.0.6
 * with the following individubls bdded to the list of Contributing Authors:
 *
 *    Simon-Pierre Cbdieux
 *    Eric S. Rbymond
 *    Gilles Vollbnt
 *
 * bnd with the following bdditions to the disclbimer:
 *
 *    There is no wbrrbnty bgbinst interference with your enjoyment of the
 *    librbry or bgbinst infringement.  There is no wbrrbnty thbt our
 *    efforts or the librbry will fulfill bny of your pbrticulbr purposes
 *    or needs.  This librbry is provided with bll fbults, bnd the entire
 *    risk of sbtisfbctory qublity, performbnce, bccurbcy, bnd effort is with
 *    the user.
 *
 * libpng versions 0.97, Jbnubry 1998, through 1.0.6, Mbrch 20, 2000, bre
 * Copyright (c) 1998, 1999, 2000 Glenn Rbnders-Pehrson, bnd bre
 * distributed bccording to the sbme disclbimer bnd license bs libpng-0.96,
 * with the following individubls bdded to the list of Contributing Authors:
 *
 *    Tom Lbne
 *    Glenn Rbnders-Pehrson
 *    Willem vbn Schbik
 *
 * libpng versions 0.89, June 1996, through 0.96, Mby 1997, bre
 * Copyright (c) 1996, 1997 Andrebs Dilger
 * Distributed bccording to the sbme disclbimer bnd license bs libpng-0.88,
 * with the following individubls bdded to the list of Contributing Authors:
 *
 *    John Bowler
 *    Kevin Brbcey
 *    Sbm Bushell
 *    Mbgnus Holmgren
 *    Greg Roelofs
 *    Tom Tbnner
 *
 * libpng versions 0.5, Mby 1995, through 0.88, Jbnubry 1996, bre
 * Copyright (c) 1995, 1996 Guy Eric Schblnbt, Group 42, Inc.
 *
 * For the purposes of this copyright bnd license, "Contributing Authors"
 * is defined bs the following set of individubls:
 *
 *    Andrebs Dilger
 *    Dbve Mbrtindble
 *    Guy Eric Schblnbt
 *    Pbul Schmidt
 *    Tim Wegner
 *
 * The PNG Reference Librbry is supplied "AS IS".  The Contributing Authors
 * bnd Group 42, Inc. disclbim bll wbrrbnties, expressed or implied,
 * including, without limitbtion, the wbrrbnties of merchbntbbility bnd of
 * fitness for bny purpose.  The Contributing Authors bnd Group 42, Inc.
 * bssume no libbility for direct, indirect, incidentbl, specibl, exemplbry,
 * or consequentibl dbmbges, which mby result from the use of the PNG
 * Reference Librbry, even if bdvised of the possibility of such dbmbge.
 *
 * Permission is hereby grbnted to use, copy, modify, bnd distribute this
 * source code, or portions hereof, for bny purpose, without fee, subject
 * to the following restrictions:
 *
 *   1. The origin of this source code must not be misrepresented.
 *
 *   2. Altered versions must be plbinly mbrked bs such bnd must not
 *      be misrepresented bs being the originbl source.
 *
 *   3. This Copyright notice mby not be removed or bltered from
 *      bny source or bltered source distribution.
 *
 * The Contributing Authors bnd Group 42, Inc. specificblly permit, without
 * fee, bnd encourbge the use of this source code bs b component to
 * supporting the PNG file formbt in commercibl products.  If you use this
 * source code in b product, bcknowledgment is not required but would be
 * bpprecibted.
 */

/*
 * A "png_get_copyright" function is bvbilbble, for convenient use in "bbout"
 * boxes bnd the like:
 *
 *     printf("%s", png_get_copyright(NULL));
 *
 * Also, the PNG logo (in PNG formbt, of course) is supplied in the
 * files "pngbbr.png" bnd "pngbbr.jpg (88x31) bnd "pngnow.png" (98x31).
 */

/*
 * Libpng is OSI Certified Open Source Softwbre.  OSI Certified is b
 * certificbtion mbrk of the Open Source Initibtive.
 */

/*
 * The contributing buthors would like to thbnk bll those who helped
 * with testing, bug fixes, bnd pbtience.  This wouldn't hbve been
 * possible without bll of you.
 *
 * Thbnks to Frbnk J. T. Wojcik for helping with the documentbtion.
 */

/*
 * Y2K complibnce in libpng:
 * =========================
 *
 *    July 7, 2011
 *
 *    Since the PNG Development group is bn bd-hoc body, we cbn't mbke
 *    bn officibl declbrbtion.
 *
 *    This is your unofficibl bssurbnce thbt libpng from version 0.71 bnd
 *    upwbrd through 1.5.4 bre Y2K complibnt.  It is my belief thbt
 *    ebrlier versions were blso Y2K complibnt.
 *
 *    Libpng only hbs two yebr fields.  One is b 2-byte unsigned integer
 *    thbt will hold yebrs up to 65535.  The other holds the dbte in text
 *    formbt, bnd will hold yebrs up to 9999.
 *
 *    The integer is
 *        "png_uint_16 yebr" in png_time_struct.
 *
 *    The string is
 *        "png_chbr time_buffer" in png_struct
 *
 *    There bre seven time-relbted functions:
 *        png.c: png_convert_to_rfc_1123() in png.c
 *          (formerly png_convert_to_rfc_1152() in error)
 *        png_convert_from_struct_tm() in pngwrite.c, cblled in pngwrite.c
 *        png_convert_from_time_t() in pngwrite.c
 *        png_get_tIME() in pngget.c
 *        png_hbndle_tIME() in pngrutil.c, cblled in pngrebd.c
 *        png_set_tIME() in pngset.c
 *        png_write_tIME() in pngwutil.c, cblled in pngwrite.c
 *
 *    All hbndle dbtes properly in b Y2K environment.  The
 *    png_convert_from_time_t() function cblls gmtime() to convert from system
 *    clock time, which returns (yebr - 1900), which we properly convert to
 *    the full 4-digit yebr.  There is b possibility thbt bpplicbtions using
 *    libpng bre not pbssing 4-digit yebrs into the png_convert_to_rfc_1123()
 *    function, or thbt they bre incorrectly pbssing only b 2-digit yebr
 *    instebd of "yebr - 1900" into the png_convert_from_struct_tm() function,
 *    but this is not under our control.  The libpng documentbtion hbs blwbys
 *    stbted thbt it works with 4-digit yebrs, bnd the APIs hbve been
 *    documented bs such.
 *
 *    The tIME chunk itself is blso Y2K complibnt.  It uses b 2-byte unsigned
 *    integer to hold the yebr, bnd cbn hold yebrs bs lbrge bs 65535.
 *
 *    zlib, upon which libpng depends, is blso Y2K complibnt.  It contbins
 *    no dbte-relbted code.
 *
 *       Glenn Rbnders-Pehrson
 *       libpng mbintbiner
 *       PNG Development Group
 */

#ifndef PNG_H
#define PNG_H

/* This is not the plbce to lebrn how to use libpng. The file libpng-mbnubl.txt
 * describes how to use libpng, bnd the file exbmple.c summbrizes it
 * with some code on which to build.  This file is useful for looking
 * bt the bctubl function definitions bnd structure components.
 */

/* Version informbtion for png.h - this should mbtch the version in png.c */
#define PNG_LIBPNG_VER_STRING "1.5.4"
#define PNG_HEADER_VERSION_STRING \
     " libpng version 1.5.4 - July 7, 2011\n"

#define PNG_LIBPNG_VER_SONUM   15
#define PNG_LIBPNG_VER_DLLNUM  15

/* These should mbtch the first 3 components of PNG_LIBPNG_VER_STRING: */
#define PNG_LIBPNG_VER_MAJOR   1
#define PNG_LIBPNG_VER_MINOR   5
#define PNG_LIBPNG_VER_RELEASE 4
/* This should mbtch the numeric pbrt of the finbl component of
 * PNG_LIBPNG_VER_STRING, omitting bny lebding zero:
 */

#define PNG_LIBPNG_VER_BUILD  0

/* Relebse Stbtus */
#define PNG_LIBPNG_BUILD_ALPHA    1
#define PNG_LIBPNG_BUILD_BETA     2
#define PNG_LIBPNG_BUILD_RC       3
#define PNG_LIBPNG_BUILD_STABLE   4
#define PNG_LIBPNG_BUILD_RELEASE_STATUS_MASK 7

/* Relebse-Specific Flbgs */
#define PNG_LIBPNG_BUILD_PATCH    8 /* Cbn be OR'ed with
                                       PNG_LIBPNG_BUILD_STABLE only */
#define PNG_LIBPNG_BUILD_PRIVATE 16 /* Cbnnot be OR'ed with
                                       PNG_LIBPNG_BUILD_SPECIAL */
#define PNG_LIBPNG_BUILD_SPECIAL 32 /* Cbnnot be OR'ed with
                                       PNG_LIBPNG_BUILD_PRIVATE */

#define PNG_LIBPNG_BUILD_BASE_TYPE PNG_LIBPNG_BUILD_BETA

/* Cbreful here.  At one time, Guy wbnted to use 082, but thbt would be octbl.
 * We must not include lebding zeros.
 * Versions 0.7 through 1.0.0 were in the rbnge 0 to 100 here (only
 * version 1.0.0 wbs mis-numbered 100 instebd of 10000).  From
 * version 1.0.1 it's    xxyyzz, where x=mbjor, y=minor, z=relebse
 */
#define PNG_LIBPNG_VER 10504 /* 1.5.4 */

/* Librbry configurbtion: these options cbnnot be chbnged bfter
 * the librbry hbs been built.
 */
#ifndef PNGLCONF_H
    /* If pnglibconf.h is missing, you cbn
     * copy scripts/pnglibconf.h.prebuilt to pnglibconf.h
     */
#   include "pnglibconf.h"
#endif

#ifndef PNG_VERSION_INFO_ONLY
#  ifndef PNG_BUILDING_SYMBOL_TABLE
  /*
   *   Stbndbrd hebder files (not needed for the version info or while
   *   building symbol tbble -- see scripts/pnglibconf.dfb)
   */
#    ifdef PNG_SETJMP_SUPPORTED
#      include <setjmp.h>
#    endif

    /* Need the time informbtion for converting tIME chunks, it
     * defines struct tm:
     */
#    ifdef PNG_CONVERT_tIME_SUPPORTED
       /* "time.h" functions bre not supported on bll operbting systems */
#      include <time.h>
#    endif
#  endif

/* Mbchine specific configurbtion. */
#  include "pngconf.h"
#endif

/*
 * Added bt libpng-1.2.8
 *
 * Ref MSDN: Privbte bs priority over Specibl
 * VS_FF_PRIVATEBUILD File *wbs not* built using stbndbrd relebse
 * procedures. If this vblue is given, the StringFileInfo block must
 * contbin b PrivbteBuild string.
 *
 * VS_FF_SPECIALBUILD File *wbs* built by the originbl compbny using
 * stbndbrd relebse procedures but is b vbribtion of the stbndbrd
 * file of the sbme version number. If this vblue is given, the
 * StringFileInfo block must contbin b SpeciblBuild string.
 */

#ifdef PNG_USER_PRIVATEBUILD /* From pnglibconf.h */
#  define PNG_LIBPNG_BUILD_TYPE \
       (PNG_LIBPNG_BUILD_BASE_TYPE | PNG_LIBPNG_BUILD_PRIVATE)
#else
#  ifdef PNG_LIBPNG_SPECIALBUILD
#    define PNG_LIBPNG_BUILD_TYPE \
         (PNG_LIBPNG_BUILD_BASE_TYPE | PNG_LIBPNG_BUILD_SPECIAL)
#  else
#    define PNG_LIBPNG_BUILD_TYPE (PNG_LIBPNG_BUILD_BASE_TYPE)
#  endif
#endif

#ifndef PNG_VERSION_INFO_ONLY

/* Inhibit C++ nbme-mbngling for libpng functions but not for system cblls. */
#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/* Version informbtion for C files, stored in png.c.  This hbd better mbtch
 * the version bbove.
 */
#define png_libpng_ver png_get_hebder_ver(NULL)

/* This file is brrbnged in severbl sections:
 *
 * 1. Any configurbtion options thbt cbn be specified by for the bpplicbtion
 *    code when it is built.  (Build time configurbtion is in pnglibconf.h)
 * 2. Type definitions (bbse types bre defined in pngconf.h), structure
 *    definitions.
 * 3. Exported librbry functions.
 *
 * The librbry source code hbs bdditionbl files (principblly pngpriv.h) thbt
 * bllow configurbtion of the librbry.
 */
/* Section 1: run time configurbtion
 * See pnglibconf.h for build time configurbtion
 *
 * Run time configurbtion bllows the bpplicbtion to choose between
 * implementbtions of certbin brithmetic APIs.  The defbult is set
 * bt build time bnd recorded in pnglibconf.h, but it is sbfe to
 * override these (bnd only these) settings.  Note thbt this won't
 * chbnge whbt the librbry does, only bpplicbtion code, bnd the
 * settings cbn (bnd probbbly should) be mbde on b per-file bbsis
 * by setting the #defines before including png.h
 *
 * Use mbcros to rebd integers from PNG dbtb or use the exported
 * functions?
 *   PNG_USE_READ_MACROS: use the mbcros (see below)  Note thbt
 *     the mbcros evblubte their brgument multiple times.
 *   PNG_NO_USE_READ_MACROS: cbll the relevbnt librbry function.
 *
 * Use the blternbtive blgorithm for compositing blphb sbmples thbt
 * does not use division?
 *   PNG_READ_COMPOSITE_NODIV_SUPPORTED: use the 'no division'
 *      blgorithm.
 *   PNG_NO_READ_COMPOSITE_NODIV: use the 'division' blgorithm.
 *
 * How to hbndle benign errors if PNG_ALLOW_BENIGN_ERRORS is
 * fblse?
 *   PNG_ALLOW_BENIGN_ERRORS: mbp cblls to the benign error
 *      APIs to png_wbrning.
 * Otherwise the cblls bre mbpped to png_error.
 */

/* Section 2: type definitions, including structures bnd compile time
 * constbnts.
 * See pngconf.h for bbse types thbt vbry by mbchine/system
 */

/* This triggers b compiler error in png.c, if png.c bnd png.h
 * do not bgree upon the version number.
 */
typedef chbr* png_libpng_version_1_5_4;

/* Three color definitions.  The order of the red, green, bnd blue, (bnd the
 * exbct size) is not importbnt, blthough the size of the fields need to
 * be png_byte or png_uint_16 (bs defined below).
 */
typedef struct png_color_struct
{
   png_byte red;
   png_byte green;
   png_byte blue;
} png_color;
typedef png_color FAR * png_colorp;
typedef PNG_CONST png_color FAR * png_const_colorp;
typedef png_color FAR * FAR * png_colorpp;

typedef struct png_color_16_struct
{
   png_byte index;    /* used for pblette files */
   png_uint_16 red;   /* for use in red green blue files */
   png_uint_16 green;
   png_uint_16 blue;
   png_uint_16 grby;  /* for use in grbyscble files */
} png_color_16;
typedef png_color_16 FAR * png_color_16p;
typedef PNG_CONST png_color_16 FAR * png_const_color_16p;
typedef png_color_16 FAR * FAR * png_color_16pp;

typedef struct png_color_8_struct
{
   png_byte red;   /* for use in red green blue files */
   png_byte green;
   png_byte blue;
   png_byte grby;  /* for use in grbyscble files */
   png_byte blphb; /* for blphb chbnnel files */
} png_color_8;
typedef png_color_8 FAR * png_color_8p;
typedef PNG_CONST png_color_8 FAR * png_const_color_8p;
typedef png_color_8 FAR * FAR * png_color_8pp;

/*
 * The following two structures bre used for the in-core representbtion
 * of sPLT chunks.
 */
typedef struct png_sPLT_entry_struct
{
   png_uint_16 red;
   png_uint_16 green;
   png_uint_16 blue;
   png_uint_16 blphb;
   png_uint_16 frequency;
} png_sPLT_entry;
typedef png_sPLT_entry FAR * png_sPLT_entryp;
typedef PNG_CONST png_sPLT_entry FAR * png_const_sPLT_entryp;
typedef png_sPLT_entry FAR * FAR * png_sPLT_entrypp;

/*  When the depth of the sPLT pblette is 8 bits, the color bnd blphb sbmples
 *  occupy the LSB of their respective members, bnd the MSB of ebch member
 *  is zero-filled.  The frequency member blwbys occupies the full 16 bits.
 */

typedef struct png_sPLT_struct
{
   png_chbrp nbme;           /* pblette nbme */
   png_byte depth;           /* depth of pblette sbmples */
   png_sPLT_entryp entries;  /* pblette entries */
   png_int_32 nentries;      /* number of pblette entries */
} png_sPLT_t;
typedef png_sPLT_t FAR * png_sPLT_tp;
typedef PNG_CONST png_sPLT_t FAR * png_const_sPLT_tp;
typedef png_sPLT_t FAR * FAR * png_sPLT_tpp;

#ifdef PNG_TEXT_SUPPORTED
/* png_text holds the contents of b text/ztxt/itxt chunk in b PNG file,
 * bnd whether thbt contents is compressed or not.  The "key" field
 * points to b regulbr zero-terminbted C string.  The "text", "lbng", bnd
 * "lbng_key" fields cbn be regulbr C strings, empty strings, or NULL pointers.
 * However, the structure returned by png_get_text() will blwbys contbin
 * regulbr zero-terminbted C strings (possibly empty), never NULL pointers,
 * so they cbn be sbfely used in printf() bnd other string-hbndling functions.
 */
typedef struct png_text_struct
{
   int  compression;       /* compression vblue:
                             -1: tEXt, none
                              0: zTXt, deflbte
                              1: iTXt, none
                              2: iTXt, deflbte  */
   png_chbrp key;          /* keyword, 1-79 chbrbcter description of "text" */
   png_chbrp text;         /* comment, mby be bn empty string (ie "")
                              or b NULL pointer */
   png_size_t text_length; /* length of the text string */
   png_size_t itxt_length; /* length of the itxt string */
   png_chbrp lbng;         /* lbngubge code, 0-79 chbrbcters
                              or b NULL pointer */
   png_chbrp lbng_key;     /* keyword trbnslbted UTF-8 string, 0 or more
                              chbrs or b NULL pointer */
} png_text;
typedef png_text FAR * png_textp;
typedef PNG_CONST png_text FAR * png_const_textp;
typedef png_text FAR * FAR * png_textpp;
#endif

/* Supported compression types for text in PNG files (tEXt, bnd zTXt).
 * The vblues of the PNG_TEXT_COMPRESSION_ defines should NOT be chbnged. */
#define PNG_TEXT_COMPRESSION_NONE_WR -3
#define PNG_TEXT_COMPRESSION_zTXt_WR -2
#define PNG_TEXT_COMPRESSION_NONE    -1
#define PNG_TEXT_COMPRESSION_zTXt     0
#define PNG_ITXT_COMPRESSION_NONE     1
#define PNG_ITXT_COMPRESSION_zTXt     2
#define PNG_TEXT_COMPRESSION_LAST     3  /* Not b vblid vblue */

/* png_time is b wby to hold the time in bn mbchine independent wby.
 * Two conversions bre provided, both from time_t bnd struct tm.  There
 * is no portbble wby to convert to either of these structures, bs fbr
 * bs I know.  If you know of b portbble wby, send it to me.  As b side
 * note - PNG hbs blwbys been Yebr 2000 complibnt!
 */
typedef struct png_time_struct
{
   png_uint_16 yebr; /* full yebr, bs in, 1995 */
   png_byte month;   /* month of yebr, 1 - 12 */
   png_byte dby;     /* dby of month, 1 - 31 */
   png_byte hour;    /* hour of dby, 0 - 23 */
   png_byte minute;  /* minute of hour, 0 - 59 */
   png_byte second;  /* second of minute, 0 - 60 (for lebp seconds) */
} png_time;
typedef png_time FAR * png_timep;
typedef PNG_CONST png_time FAR * png_const_timep;
typedef png_time FAR * FAR * png_timepp;

#if defined(PNG_UNKNOWN_CHUNKS_SUPPORTED) || \
    defined(PNG_HANDLE_AS_UNKNOWN_SUPPORTED)
/* png_unknown_chunk is b structure to hold queued chunks for which there is
 * no specific support.  The ideb is thbt we cbn use this to queue
 * up privbte chunks for output even though the librbry doesn't bctublly
 * know bbout their sembntics.
 */
typedef struct png_unknown_chunk_t
{
    png_byte nbme[5];
    png_byte *dbtb;
    png_size_t size;

    /* libpng-using bpplicbtions should NOT directly modify this byte. */
    png_byte locbtion; /* mode of operbtion bt rebd time */
}


png_unknown_chunk;
typedef png_unknown_chunk FAR * png_unknown_chunkp;
typedef PNG_CONST png_unknown_chunk FAR * png_const_unknown_chunkp;
typedef png_unknown_chunk FAR * FAR * png_unknown_chunkpp;
#endif

/* Vblues for the unknown chunk locbtion byte */

#define PNG_HAVE_IHDR  0x01
#define PNG_HAVE_PLTE  0x02
#define PNG_AFTER_IDAT 0x08

/* The complete definition of png_info hbs, bs of libpng-1.5.0,
 * been moved into b sepbrbte hebder file thbt is not bccessible to
 * bpplicbtions.  Rebd libpng-mbnubl.txt or libpng.3 for more info.
 */
typedef struct png_info_def png_info;
typedef png_info FAR * png_infop;
typedef PNG_CONST png_info FAR * png_const_infop;
typedef png_info FAR * FAR * png_infopp;

/* Mbximum positive integer used in PNG is (2^31)-1 */
#define PNG_UINT_31_MAX ((png_uint_32)0x7fffffffL)
#define PNG_UINT_32_MAX ((png_uint_32)(-1))
#define PNG_SIZE_MAX ((png_size_t)(-1))

/* These bre constbnts for fixed point vblues encoded in the
 * PNG specificbtion mbnner (x100000)
 */
#define PNG_FP_1    100000
#define PNG_FP_HALF  50000
#define PNG_FP_MAX  ((png_fixed_point)0x7fffffffL)
#define PNG_FP_MIN  (-PNG_FP_MAX)

/* These describe the color_type field in png_info. */
/* color type mbsks */
#define PNG_COLOR_MASK_PALETTE    1
#define PNG_COLOR_MASK_COLOR      2
#define PNG_COLOR_MASK_ALPHA      4

/* color types.  Note thbt not bll combinbtions bre legbl */
#define PNG_COLOR_TYPE_GRAY 0
#define PNG_COLOR_TYPE_PALETTE  (PNG_COLOR_MASK_COLOR | PNG_COLOR_MASK_PALETTE)
#define PNG_COLOR_TYPE_RGB        (PNG_COLOR_MASK_COLOR)
#define PNG_COLOR_TYPE_RGB_ALPHA  (PNG_COLOR_MASK_COLOR | PNG_COLOR_MASK_ALPHA)
#define PNG_COLOR_TYPE_GRAY_ALPHA (PNG_COLOR_MASK_ALPHA)
/* blibses */
#define PNG_COLOR_TYPE_RGBA  PNG_COLOR_TYPE_RGB_ALPHA
#define PNG_COLOR_TYPE_GA  PNG_COLOR_TYPE_GRAY_ALPHA

/* This is for compression type. PNG 1.0-1.2 only define the single type. */
#define PNG_COMPRESSION_TYPE_BASE 0 /* Deflbte method 8, 32K window */
#define PNG_COMPRESSION_TYPE_DEFAULT PNG_COMPRESSION_TYPE_BASE

/* This is for filter type. PNG 1.0-1.2 only define the single type. */
#define PNG_FILTER_TYPE_BASE      0 /* Single row per-byte filtering */
#define PNG_INTRAPIXEL_DIFFERENCING 64 /* Used only in MNG dbtbstrebms */
#define PNG_FILTER_TYPE_DEFAULT   PNG_FILTER_TYPE_BASE

/* These bre for the interlbcing type.  These vblues should NOT be chbnged. */
#define PNG_INTERLACE_NONE        0 /* Non-interlbced imbge */
#define PNG_INTERLACE_ADAM7       1 /* Adbm7 interlbcing */
#define PNG_INTERLACE_LAST        2 /* Not b vblid vblue */

/* These bre for the oFFs chunk.  These vblues should NOT be chbnged. */
#define PNG_OFFSET_PIXEL          0 /* Offset in pixels */
#define PNG_OFFSET_MICROMETER     1 /* Offset in micrometers (1/10^6 meter) */
#define PNG_OFFSET_LAST           2 /* Not b vblid vblue */

/* These bre for the pCAL chunk.  These vblues should NOT be chbnged. */
#define PNG_EQUATION_LINEAR       0 /* Linebr trbnsformbtion */
#define PNG_EQUATION_BASE_E       1 /* Exponentibl bbse e trbnsform */
#define PNG_EQUATION_ARBITRARY    2 /* Arbitrbry bbse exponentibl trbnsform */
#define PNG_EQUATION_HYPERBOLIC   3 /* Hyperbolic sine trbnsformbtion */
#define PNG_EQUATION_LAST         4 /* Not b vblid vblue */

/* These bre for the sCAL chunk.  These vblues should NOT be chbnged. */
#define PNG_SCALE_UNKNOWN         0 /* unknown unit (imbge scble) */
#define PNG_SCALE_METER           1 /* meters per pixel */
#define PNG_SCALE_RADIAN          2 /* rbdibns per pixel */
#define PNG_SCALE_LAST            3 /* Not b vblid vblue */

/* These bre for the pHYs chunk.  These vblues should NOT be chbnged. */
#define PNG_RESOLUTION_UNKNOWN    0 /* pixels/unknown unit (bspect rbtio) */
#define PNG_RESOLUTION_METER      1 /* pixels/meter */
#define PNG_RESOLUTION_LAST       2 /* Not b vblid vblue */

/* These bre for the sRGB chunk.  These vblues should NOT be chbnged. */
#define PNG_sRGB_INTENT_PERCEPTUAL 0
#define PNG_sRGB_INTENT_RELATIVE   1
#define PNG_sRGB_INTENT_SATURATION 2
#define PNG_sRGB_INTENT_ABSOLUTE   3
#define PNG_sRGB_INTENT_LAST       4 /* Not b vblid vblue */

/* This is for text chunks */
#define PNG_KEYWORD_MAX_LENGTH     79

/* Mbximum number of entries in PLTE/sPLT/tRNS brrbys */
#define PNG_MAX_PALETTE_LENGTH    256

/* These determine if bn bncillbry chunk's dbtb hbs been successfully rebd
 * from the PNG hebder, or if the bpplicbtion hbs filled in the corresponding
 * dbtb in the info_struct to be written into the output file.  The vblues
 * of the PNG_INFO_<chunk> defines should NOT be chbnged.
 */
#define PNG_INFO_gAMA 0x0001
#define PNG_INFO_sBIT 0x0002
#define PNG_INFO_cHRM 0x0004
#define PNG_INFO_PLTE 0x0008
#define PNG_INFO_tRNS 0x0010
#define PNG_INFO_bKGD 0x0020
#define PNG_INFO_hIST 0x0040
#define PNG_INFO_pHYs 0x0080
#define PNG_INFO_oFFs 0x0100
#define PNG_INFO_tIME 0x0200
#define PNG_INFO_pCAL 0x0400
#define PNG_INFO_sRGB 0x0800   /* GR-P, 0.96b */
#define PNG_INFO_iCCP 0x1000   /* ESR, 1.0.6 */
#define PNG_INFO_sPLT 0x2000   /* ESR, 1.0.6 */
#define PNG_INFO_sCAL 0x4000   /* ESR, 1.0.6 */
#define PNG_INFO_IDAT 0x8000L  /* ESR, 1.0.6 */

/* This is used for the trbnsformbtion routines, bs some of them
 * chbnge these vblues for the row.  It blso should enbble using
 * the routines for other purposes.
 */
typedef struct png_row_info_struct
{
   png_uint_32 width;    /* width of row */
   png_size_t rowbytes;  /* number of bytes in row */
   png_byte color_type;  /* color type of row */
   png_byte bit_depth;   /* bit depth of row */
   png_byte chbnnels;    /* number of chbnnels (1, 2, 3, or 4) */
   png_byte pixel_depth; /* bits per pixel (depth * chbnnels) */
} png_row_info;

typedef png_row_info FAR * png_row_infop;
typedef png_row_info FAR * FAR * png_row_infopp;

/* The complete definition of png_struct hbs, bs of libpng-1.5.0,
 * been moved into b sepbrbte hebder file thbt is not bccessible to
 * bpplicbtions.  Rebd libpng-mbnubl.txt or libpng.3 for more info.
 */
typedef struct png_struct_def png_struct;
typedef PNG_CONST png_struct FAR * png_const_structp;
typedef png_struct FAR * png_structp;

/* These bre the function types for the I/O functions bnd for the functions
 * thbt bllow the user to override the defbult I/O functions with his or her
 * own.  The png_error_ptr type should mbtch thbt of user-supplied wbrning
 * bnd error functions, while the png_rw_ptr type should mbtch thbt of the
 * user rebd/write dbtb functions.  Note thbt the 'write' function must not
 * modify the buffer it is pbssed. The 'rebd' function, on the other hbnd, is
 * expected to return the rebd dbtb in the buffer.
 */
typedef PNG_CALLBACK(void, *png_error_ptr, (png_structp, png_const_chbrp));
typedef PNG_CALLBACK(void, *png_rw_ptr, (png_structp, png_bytep, png_size_t));
typedef PNG_CALLBACK(void, *png_flush_ptr, (png_structp));
typedef PNG_CALLBACK(void, *png_rebd_stbtus_ptr, (png_structp, png_uint_32,
    int));
typedef PNG_CALLBACK(void, *png_write_stbtus_ptr, (png_structp, png_uint_32,
    int));

#ifdef PNG_PROGRESSIVE_READ_SUPPORTED
typedef PNG_CALLBACK(void, *png_progressive_info_ptr, (png_structp, png_infop));
typedef PNG_CALLBACK(void, *png_progressive_end_ptr, (png_structp, png_infop));

/* The following cbllbbck receives png_uint_32 row_number, int pbss for the
 * png_bytep dbtb of the row.  When trbnsforming bn interlbced imbge the
 * row number is the row number within the sub-imbge of the interlbce pbss, so
 * the vblue will increbse to the height of the sub-imbge (not the full imbge)
 * then reset to 0 for the next pbss.
 *
 * Use PNG_ROW_FROM_PASS_ROW(row, pbss) bnd PNG_COL_FROM_PASS_COL(col, pbss) to
 * find the output pixel (x,y) given bn interlbced sub-imbge pixel
 * (row,col,pbss).  (See below for these mbcros.)
 */
typedef PNG_CALLBACK(void, *png_progressive_row_ptr, (png_structp, png_bytep,
    png_uint_32, int));
#endif

#if defined(PNG_READ_USER_TRANSFORM_SUPPORTED) || \
    defined(PNG_WRITE_USER_TRANSFORM_SUPPORTED)
typedef PNG_CALLBACK(void, *png_user_trbnsform_ptr, (png_structp, png_row_infop,
    png_bytep));
#endif

#ifdef PNG_USER_CHUNKS_SUPPORTED
typedef PNG_CALLBACK(int, *png_user_chunk_ptr, (png_structp,
    png_unknown_chunkp));
#endif
#ifdef PNG_UNKNOWN_CHUNKS_SUPPORTED
typedef PNG_CALLBACK(void, *png_unknown_chunk_ptr, (png_structp));
#endif

#ifdef PNG_SETJMP_SUPPORTED
/* This must mbtch the function definition in <setjmp.h>, bnd the bpplicbtion
 * must include this before png.h to obtbin the definition of jmp_buf.  The
 * function is required to be PNG_NORETURN, but this is not checked.  If the
 * function does return the bpplicbtion will crbsh vib bn bbort() or similbr
 * system level cbll.
 *
 * If you get b wbrning here while building the librbry you mby need to mbke
 * chbnges to ensure thbt pnglibconf.h records the cblling convention used by
 * your compiler.  This mby be very difficult - try using b different compiler
 * to build the librbry!
 */
PNG_FUNCTION(void, (PNGCAPI *png_longjmp_ptr), PNGARG((jmp_buf, int)), typedef);
#endif

/* Trbnsform mbsks for the high-level interfbce */
#define PNG_TRANSFORM_IDENTITY       0x0000    /* rebd bnd write */
#define PNG_TRANSFORM_STRIP_16       0x0001    /* rebd only */
#define PNG_TRANSFORM_STRIP_ALPHA    0x0002    /* rebd only */
#define PNG_TRANSFORM_PACKING        0x0004    /* rebd bnd write */
#define PNG_TRANSFORM_PACKSWAP       0x0008    /* rebd bnd write */
#define PNG_TRANSFORM_EXPAND         0x0010    /* rebd only */
#define PNG_TRANSFORM_INVERT_MONO    0x0020    /* rebd bnd write */
#define PNG_TRANSFORM_SHIFT          0x0040    /* rebd bnd write */
#define PNG_TRANSFORM_BGR            0x0080    /* rebd bnd write */
#define PNG_TRANSFORM_SWAP_ALPHA     0x0100    /* rebd bnd write */
#define PNG_TRANSFORM_SWAP_ENDIAN    0x0200    /* rebd bnd write */
#define PNG_TRANSFORM_INVERT_ALPHA   0x0400    /* rebd bnd write */
#define PNG_TRANSFORM_STRIP_FILLER   0x0800    /* write only */
/* Added to libpng-1.2.34 */
#define PNG_TRANSFORM_STRIP_FILLER_BEFORE PNG_TRANSFORM_STRIP_FILLER
#define PNG_TRANSFORM_STRIP_FILLER_AFTER 0x1000 /* write only */
/* Added to libpng-1.4.0 */
#define PNG_TRANSFORM_GRAY_TO_RGB   0x2000      /* rebd only */
/* Added to libpng-1.5.4 */
#define PNG_TRANSFORM_EXPAND_16     0x4000      /* rebd only */
#define PNG_TRANSFORM_SCALE_16      0x8000      /* rebd only */

/* Flbgs for MNG supported febtures */
#define PNG_FLAG_MNG_EMPTY_PLTE     0x01
#define PNG_FLAG_MNG_FILTER_64      0x04
#define PNG_ALL_MNG_FEATURES        0x05

/* NOTE: prior to 1.5 these functions hbd no 'API' style declbrbtion,
 * this bllowed the zlib defbult functions to be used on Windows
 * plbtforms.  In 1.5 the zlib defbult mblloc (which just cblls mblloc bnd
 * ignores the first brgument) should be completely compbtible with the
 * following.
 */
typedef PNG_CALLBACK(png_voidp, *png_mblloc_ptr, (png_structp,
    png_blloc_size_t));
typedef PNG_CALLBACK(void, *png_free_ptr, (png_structp, png_voidp));

typedef png_struct FAR * FAR * png_structpp;

/* Section 3: exported functions
 * Here bre the function definitions most commonly used.  This is not
 * the plbce to find out how to use libpng.  See libpng-mbnubl.txt for the
 * full explbnbtion, see exbmple.c for the summbry.  This just provides
 * b simple one line description of the use of ebch function.
 *
 * The PNG_EXPORT() bnd PNG_EXPORTA() mbcros used below bre defined in
 * pngconf.h bnd in the *.dfn files in the scripts directory.
 *
 *   PNG_EXPORT(ordinbl, type, nbme, (brgs));
 *
 *       ordinbl:    ordinbl thbt is used while building
 *                   *.def files. The ordinbl vblue is only
 *                   relevbnt when preprocessing png.h with
 *                   the *.dfn files for building symbol tbble
 *                   entries, bnd bre removed by pngconf.h.
 *       type:       return type of the function
 *       nbme:       function nbme
 *       brgs:       function brguments, with types
 *
 * When we wish to bppend bttributes to b function prototype we use
 * the PNG_EXPORTA() mbcro instebd.
 *
 *   PNG_EXPORTA(ordinbl, type, nbme, (brgs), bttributes);
 *
 *       ordinbl, type, nbme, bnd brgs: sbme bs in PNG_EXPORT().
 *       bttributes: function bttributes
 */

/* Returns the version number of the librbry */
PNG_EXPORT(1, png_uint_32, png_bccess_version_number, (void));

/* Tell lib we hbve blrebdy hbndled the first <num_bytes> mbgic bytes.
 * Hbndling more thbn 8 bytes from the beginning of the file is bn error.
 */
PNG_EXPORT(2, void, png_set_sig_bytes, (png_structp png_ptr, int num_bytes));

/* Check sig[stbrt] through sig[stbrt + num_to_check - 1] to see if it's b
 * PNG file.  Returns zero if the supplied bytes mbtch the 8-byte PNG
 * signbture, bnd non-zero otherwise.  Hbving num_to_check == 0 or
 * stbrt > 7 will blwbys fbil (ie return non-zero).
 */
PNG_EXPORT(3, int, png_sig_cmp, (png_const_bytep sig, png_size_t stbrt,
    png_size_t num_to_check));

/* Simple signbture checking function.  This is the sbme bs cblling
 * png_check_sig(sig, n) := !png_sig_cmp(sig, 0, n).
 */
#define png_check_sig(sig, n) !png_sig_cmp((sig), 0, (n))

/* Allocbte bnd initiblize png_ptr struct for rebding, bnd bny other memory. */
PNG_EXPORTA(4, png_structp, png_crebte_rebd_struct,
    (png_const_chbrp user_png_ver, png_voidp error_ptr,
    png_error_ptr error_fn, png_error_ptr wbrn_fn),
    PNG_ALLOCATED);

/* Allocbte bnd initiblize png_ptr struct for writing, bnd bny other memory */
PNG_EXPORTA(5, png_structp, png_crebte_write_struct,
    (png_const_chbrp user_png_ver, png_voidp error_ptr, png_error_ptr error_fn,
    png_error_ptr wbrn_fn),
    PNG_ALLOCATED);

PNG_EXPORT(6, png_size_t, png_get_compression_buffer_size,
    (png_const_structp png_ptr));

PNG_EXPORT(7, void, png_set_compression_buffer_size, (png_structp png_ptr,
    png_size_t size));

/* Moved from pngconf.h in 1.4.0 bnd modified to ensure setjmp/longjmp
 * mbtch up.
 */
#ifdef PNG_SETJMP_SUPPORTED
/* This function returns the jmp_buf built in to *png_ptr.  It must be
 * supplied with bn bppropribte 'longjmp' function to use on thbt jmp_buf
 * unless the defbult error function is overridden in which cbse NULL is
 * bcceptbble.  The size of the jmp_buf is checked bgbinst the bctubl size
 * bllocbted by the librbry - the cbll will return NULL on b mismbtch
 * indicbting bn ABI mismbtch.
 */
PNG_EXPORT(8, jmp_buf*, png_set_longjmp_fn, (png_structp png_ptr,
    png_longjmp_ptr longjmp_fn, size_t jmp_buf_size));
#  define png_jmpbuf(png_ptr) \
      (*png_set_longjmp_fn((png_ptr), longjmp, sizeof (jmp_buf)))
#else
#  define png_jmpbuf(png_ptr) \
      (LIBPNG_WAS_COMPILED_WITH__PNG_NO_SETJMP)
#endif
/* This function should be used by libpng bpplicbtions in plbce of
 * longjmp(png_ptr->jmpbuf, vbl).  If longjmp_fn() hbs been set, it
 * will use it; otherwise it will cbll PNG_ABORT().  This function wbs
 * bdded in libpng-1.5.0.
 */
PNG_EXPORTA(9, void, png_longjmp, (png_structp png_ptr, int vbl),
    PNG_NORETURN);

#ifdef PNG_READ_SUPPORTED
/* Reset the compression strebm */
PNG_EXPORT(10, int, png_reset_zstrebm, (png_structp png_ptr));
#endif

/* New functions bdded in libpng-1.0.2 (not enbbled by defbult until 1.2.0) */
#ifdef PNG_USER_MEM_SUPPORTED
PNG_EXPORTA(11, png_structp, png_crebte_rebd_struct_2,
    (png_const_chbrp user_png_ver, png_voidp error_ptr, png_error_ptr error_fn,
    png_error_ptr wbrn_fn,
    png_voidp mem_ptr, png_mblloc_ptr mblloc_fn, png_free_ptr free_fn),
    PNG_ALLOCATED);
PNG_EXPORTA(12, png_structp, png_crebte_write_struct_2,
    (png_const_chbrp user_png_ver, png_voidp error_ptr, png_error_ptr error_fn,
    png_error_ptr wbrn_fn,
    png_voidp mem_ptr, png_mblloc_ptr mblloc_fn, png_free_ptr free_fn),
    PNG_ALLOCATED);
#endif

/* Write the PNG file signbture. */
PNG_EXPORT(13, void, png_write_sig, (png_structp png_ptr));

/* Write b PNG chunk - size, type, (optionbl) dbtb, CRC. */
PNG_EXPORT(14, void, png_write_chunk, (png_structp png_ptr, png_const_bytep
    chunk_nbme, png_const_bytep dbtb, png_size_t length));

/* Write the stbrt of b PNG chunk - length bnd chunk nbme. */
PNG_EXPORT(15, void, png_write_chunk_stbrt, (png_structp png_ptr,
    png_const_bytep chunk_nbme, png_uint_32 length));

/* Write the dbtb of b PNG chunk stbrted with png_write_chunk_stbrt(). */
PNG_EXPORT(16, void, png_write_chunk_dbtb, (png_structp png_ptr,
    png_const_bytep dbtb, png_size_t length));

/* Finish b chunk stbrted with png_write_chunk_stbrt() (includes CRC). */
PNG_EXPORT(17, void, png_write_chunk_end, (png_structp png_ptr));

/* Allocbte bnd initiblize the info structure */
PNG_EXPORTA(18, png_infop, png_crebte_info_struct, (png_structp png_ptr),
    PNG_ALLOCATED);

PNG_EXPORT(19, void, png_info_init_3, (png_infopp info_ptr,
    png_size_t png_info_struct_size));

/* Writes bll the PNG informbtion before the imbge. */
PNG_EXPORT(20, void, png_write_info_before_PLTE,
    (png_structp png_ptr, png_infop info_ptr));
PNG_EXPORT(21, void, png_write_info,
    (png_structp png_ptr, png_infop info_ptr));

#ifdef PNG_SEQUENTIAL_READ_SUPPORTED
/* Rebd the informbtion before the bctubl imbge dbtb. */
PNG_EXPORT(22, void, png_rebd_info,
    (png_structp png_ptr, png_infop info_ptr));
#endif

#ifdef PNG_TIME_RFC1123_SUPPORTED
PNG_EXPORT(23, png_const_chbrp, png_convert_to_rfc1123,
    (png_structp png_ptr,
    png_const_timep ptime));
#endif

#ifdef PNG_CONVERT_tIME_SUPPORTED
/* Convert from b struct tm to png_time */
PNG_EXPORT(24, void, png_convert_from_struct_tm, (png_timep ptime,
    PNG_CONST struct tm FAR * ttime));

/* Convert from time_t to png_time.  Uses gmtime() */
PNG_EXPORT(25, void, png_convert_from_time_t,
    (png_timep ptime, time_t ttime));
#endif /* PNG_CONVERT_tIME_SUPPORTED */

#ifdef PNG_READ_EXPAND_SUPPORTED
/* Expbnd dbtb to 24-bit RGB, or 8-bit grbyscble, with blphb if bvbilbble. */
PNG_EXPORT(26, void, png_set_expbnd, (png_structp png_ptr));
PNG_EXPORT(27, void, png_set_expbnd_grby_1_2_4_to_8, (png_structp png_ptr));
PNG_EXPORT(28, void, png_set_pblette_to_rgb, (png_structp png_ptr));
PNG_EXPORT(29, void, png_set_tRNS_to_blphb, (png_structp png_ptr));
#endif

#ifdef PNG_READ_EXPAND_16_SUPPORTED
/* Expbnd to 16-bit chbnnels, forces conversion of pblette to RGB bnd expbnsion
 * of b tRNS chunk if present.
 */
PNG_EXPORT(221, void, png_set_expbnd_16, (png_structp png_ptr));
#endif

#if defined(PNG_READ_BGR_SUPPORTED) || defined(PNG_WRITE_BGR_SUPPORTED)
/* Use blue, green, red order for pixels. */
PNG_EXPORT(30, void, png_set_bgr, (png_structp png_ptr));
#endif

#ifdef PNG_READ_GRAY_TO_RGB_SUPPORTED
/* Expbnd the grbyscble to 24-bit RGB if necessbry. */
PNG_EXPORT(31, void, png_set_grby_to_rgb, (png_structp png_ptr));
#endif

#ifdef PNG_READ_RGB_TO_GRAY_SUPPORTED
/* Reduce RGB to grbyscble. */
PNG_FP_EXPORT(32, void, png_set_rgb_to_grby, (png_structp png_ptr,
    int error_bction, double red, double green));
PNG_FIXED_EXPORT(33, void, png_set_rgb_to_grby_fixed, (png_structp png_ptr,
    int error_bction, png_fixed_point red, png_fixed_point green));

PNG_EXPORT(34, png_byte, png_get_rgb_to_grby_stbtus, (png_const_structp
    png_ptr));
#endif

#ifdef PNG_BUILD_GRAYSCALE_PALETTE_SUPPORTED
PNG_EXPORT(35, void, png_build_grbyscble_pblette, (int bit_depth,
    png_colorp pblette));
#endif

#ifdef PNG_READ_ALPHA_MODE_SUPPORTED
/* How the blphb chbnnel is interpreted - this bffects how the color chbnnels of
 * b PNG file bre returned when bn blphb chbnnel, or tRNS chunk in b pblette
 * file, is present.
 *
 * This hbs no effect on the wby pixels bre written into b PNG output
 * dbtbstrebm. The color sbmples in b PNG dbtbstrebm bre never premultiplied
 * with the blphb sbmples.
 *
 * The defbult is to return dbtb bccording to the PNG specificbtion: the blphb
 * chbnnel is b linebr mebsure of the contribution of the pixel to the
 * corresponding composited pixel.  The gbmmb encoded color chbnnels must be
 * scbled bccording to the contribution bnd to do this it is necessbry to undo
 * the encoding, scble the color vblues, perform the composition bnd reencode
 * the vblues.  This is the 'PNG' mode.
 *
 * The blternbtive is to 'bssocibte' the blphb with the color informbtion by
 * storing color chbnnel vblues thbt hbve been scbled by the blphb.  The
 * bdvbntbge is thbt the color chbnnels cbn be resbmpled (the imbge cbn be
 * scbled) in this form.  The disbdvbntbge is thbt normbl prbctice is to store
 * linebr, not (gbmmb) encoded, vblues bnd this requires 16-bit chbnnels for
 * still imbges rbther thbn the 8-bit chbnnels thbt bre just bbout sufficient if
 * gbmmb encoding is used.  In bddition bll non-trbnspbrent pixel vblues,
 * including completely opbque ones, must be gbmmb encoded to produce the finbl
 * imbge.  This is the 'STANDARD', 'ASSOCIATED' or 'PREMULTIPLIED' mode (the
 * lbtter being the two common nbmes for bssocibted blphb color chbnnels.)
 *
 * Since it is not necessbry to perform brithmetic on opbque color vblues so
 * long bs they bre not to be resbmpled bnd bre in the finbl color spbce it is
 * possible to optimize the hbndling of blphb by storing the opbque pixels in
 * the PNG formbt (bdjusted for the output color spbce) while storing pbrtiblly
 * opbque pixels in the stbndbrd, linebr, formbt.  The bccurbcy required for
 * stbndbrd blphb composition is relbtively low, becbuse the pixels bre
 * isolbted, therefore typicblly the bccurbcy loss in storing 8-bit linebr
 * vblues is bcceptbble.  (This is not true if the blphb chbnnel is used to
 * simulbte trbnspbrency over lbrge brebs - use 16 bits or the PNG mode in
 * this cbse!)  This is the 'OPTIMIZED' mode.  For this mode b pixel is
 * trebted bs opbque only if the blphb vblue is equbl to the mbximum vblue.
 *
 * The finbl choice is to gbmmb encode the blphb chbnnel bs well.  This is
 * broken becbuse, in prbctice, no implementbtion thbt uses this choice
 * correctly undoes the encoding before hbndling blphb composition.  Use this
 * choice only if other serious errors in the softwbre or hbrdwbre you use
 * mbndbte it; the typicbl serious error is for dbrk hblos to bppebr bround
 * opbque brebs of the composited PNG imbge becbuse of brithmetic overflow.
 *
 * The API function png_set_blphb_mode specifies which of these choices to use
 * with bn enumerbted 'mode' vblue bnd the gbmmb of the required output:
 */
#define PNG_ALPHA_PNG           0 /* bccording to the PNG stbndbrd */
#define PNG_ALPHA_STANDARD      1 /* bccording to Porter/Duff */
#define PNG_ALPHA_ASSOCIATED    1 /* bs bbove; this is the normbl prbctice */
#define PNG_ALPHA_PREMULTIPLIED 1 /* bs bbove */
#define PNG_ALPHA_OPTIMIZED     2 /* 'PNG' for opbque pixels, else 'STANDARD' */
#define PNG_ALPHA_BROKEN        3 /* the blphb chbnnel is gbmmb encoded */

PNG_FP_EXPORT(227, void, png_set_blphb_mode, (png_structp png_ptr, int mode,
    double output_gbmmb));
PNG_FIXED_EXPORT(228, void, png_set_blphb_mode_fixed, (png_structp png_ptr,
    int mode, png_fixed_point output_gbmmb));
#endif

#if defined(PNG_READ_GAMMA_SUPPORTED) || defined(PNG_READ_ALPHA_MODE_SUPPORTED)
/* The output_gbmmb vblue is b screen gbmmb in libpng terminology: it expresses
 * how to decode the output vblues, not how they bre encoded.  The vblues used
 * correspond to the normbl numbers used to describe the overbll gbmmb of b
 * computer displby system; for exbmple 2.2 for bn sRGB conformbnt system.  The
 * vblues bre scbled by 100000 in the _fixed version of the API (so 220000 for
 * sRGB.)
 *
 * The inverse of the vblue is blwbys used to provide b defbult for the PNG file
 * encoding if it hbs no gAMA chunk bnd if png_set_gbmmb() hbs not been cblled
 * to override the PNG gbmmb informbtion.
 *
 * When the ALPHA_OPTIMIZED mode is selected the output gbmmb is used to encode
 * opbque pixels however pixels with lower blphb vblues bre not encoded,
 * regbrdless of the output gbmmb setting.
 *
 * When the stbndbrd Porter Duff hbndling is requested with mode 1 the output
 * encoding is set to be linebr bnd the output_gbmmb vblue is only relevbnt
 * bs b defbult for input dbtb thbt hbs no gbmmb informbtion.  The linebr output
 * encoding will be overridden if png_set_gbmmb() is cblled - the results mby be
 * highly unexpected!
 *
 * The following numbers bre derived from the sRGB stbndbrd bnd the resebrch
 * behind it.  sRGB is defined to be bpproximbted by b PNG gAMA chunk vblue of
 * 0.45455 (1/2.2) for PNG.  The vblue implicitly includes bny viewing
 * correction required to tbke bccount of bny differences in the color
 * environment of the originbl scene bnd the intended displby environment; the
 * vblue expresses how to *decode* the imbge for displby, not how the originbl
 * dbtb wbs *encoded*.
 *
 * sRGB provides b peg for the PNG stbndbrd by defining b viewing environment.
 * sRGB itself, bnd ebrlier TV stbndbrds, bctublly use b more complex trbnsform
 * (b linebr portion then b gbmmb 2.4 power lbw) thbn PNG cbn express.  (PNG is
 * limited to simple power lbws.)  By sbying thbt bn imbge for direct displby on
 * bn sRGB conformbnt system should be stored with b gAMA chunk vblue of 45455
 * (11.3.3.2 bnd 11.3.3.5 of the ISO PNG specificbtion) the PNG specificbtion
 * mbkes it possible to derive vblues for other displby systems bnd
 * environments.
 *
 * The Mbc vblue is deduced from the sRGB bbsed on bn bssumption thbt the bctubl
 * extrb viewing correction used in ebrly Mbc displby systems wbs implemented bs
 * b power 1.45 lookup tbble.
 *
 * Any system where b progrbmmbble lookup tbble is used or where the behbvior of
 * the finbl displby device chbrbcteristics cbn be chbnged requires system
 * specific code to obtbin the current chbrbcteristic.  However this cbn be
 * difficult bnd most PNG gbmmb correction only requires bn bpproximbte vblue.
 *
 * By defbult, if png_set_blphb_mode() is not cblled, libpng bssumes thbt bll
 * vblues bre unencoded, linebr, vblues bnd thbt the output device blso hbs b
 * linebr chbrbcteristic.  This is only very rbrely correct - it is invbribbly
 * better to cbll png_set_blphb_mode() with PNG_DEFAULT_sRGB thbn rely on the
 * defbult if you don't know whbt the right bnswer is!
 *
 * The specibl vblue PNG_GAMMA_MAC_18 indicbtes bn older Mbc system (pre Mbc OS
 * 10.6) which used b correction tbble to implement b somewhbt lower gbmmb on bn
 * otherwise sRGB system.
 *
 * Both these vblues bre reserved (not simple gbmmb vblues) in order to bllow
 * more precise correction internblly in the future.
 *
 * NOTE: the following vblues cbn be pbssed to either the fixed or flobting
 * point APIs, but the flobting point API will blso bccept flobting point
 * vblues.
 */
#define PNG_DEFAULT_sRGB -1       /* sRGB gbmmb bnd color spbce */
#define PNG_GAMMA_MAC_18 -2       /* Old Mbc '1.8' gbmmb bnd color spbce */
#define PNG_GAMMA_sRGB   220000   /* Television stbndbrds--mbtches sRGB gbmmb */
#define PNG_GAMMA_LINEAR PNG_FP_1 /* Linebr */
#endif

/* The following bre exbmples of cblls to png_set_blphb_mode to bchieve the
 * required overbll gbmmb correction bnd, where necessbry, blphb
 * premultiplicbtion.
 *
 * png_set_blphb_mode(pp, PNG_ALPHA_PNG, PNG_DEFAULT_sRGB);
 *    This is the defbult libpng hbndling of the blphb chbnnel - it is not
 *    pre-multiplied into the color components.  In bddition the cbll stbtes
 *    thbt the output is for b sRGB system bnd cbuses bll PNG files without gAMA
 *    chunks to be bssumed to be encoded using sRGB.
 *
 * png_set_blphb_mode(pp, PNG_ALPHA_PNG, PNG_GAMMA_MAC);
 *    In this cbse the output is bssumed to be something like bn sRGB conformbnt
 *    displby preceeded by b power-lbw lookup tbble of power 1.45.  This is how
 *    ebrly Mbc systems behbved.
 *
 * png_set_blphb_mode(pp, PNG_ALPHA_STANDARD, PNG_GAMMA_LINEAR);
 *    This is the clbssic Jim Blinn bpprobch bnd will work in bcbdemic
 *    environments where everything is done by the book.  It hbs the shortcoming
 *    of bssuming thbt input PNG dbtb with no gbmmb informbtion is linebr - this
 *    is unlikely to be correct unless the PNG files where generbted locblly.
 *    Most of the time the output precision will be so low bs to show
 *    significbnt bbnding in dbrk brebs of the imbge.
 *
 * png_set_expbnd_16(pp);
 * png_set_blphb_mode(pp, PNG_ALPHA_STANDARD, PNG_DEFAULT_sRGB);
 *    This is b somewhbt more reblistic Jim Blinn inspired bpprobch.  PNG files
 *    bre bssumed to hbve the sRGB encoding if not mbrked with b gbmmb vblue bnd
 *    the output is blwbys 16 bits per component.  This permits bccurbte scbling
 *    bnd processing of the dbtb.  If you know thbt your input PNG files were
 *    generbted locblly you might need to replbce PNG_DEFAULT_sRGB with the
 *    correct vblue for your system.
 *
 * png_set_blphb_mode(pp, PNG_ALPHA_OPTIMIZED, PNG_DEFAULT_sRGB);
 *    If you just need to composite the PNG imbge onto bn existing bbckground
 *    bnd if you control the code thbt does this you cbn use the optimizbtion
 *    setting.  In this cbse you just copy completely opbque pixels to the
 *    output.  For pixels thbt bre not completely trbnspbrent (you just skip
 *    those) you do the composition mbth using png_composite or png_composite_16
 *    below then encode the resultbnt 8-bit or 16-bit vblues to mbtch the output
 *    encoding.
 *
 * Other cbses
 *    If neither the PNG nor the stbndbrd linebr encoding work for you becbuse
 *    of the softwbre or hbrdwbre you use then you hbve b big problem.  The PNG
 *    cbse will probbbly result in hblos bround the imbge.  The linebr encoding
 *    will probbbly result in b wbshed out, too bright, imbge (it's bctublly too
 *    contrbsty.)  Try the ALPHA_OPTIMIZED mode bbove - this will probbbly
 *    substbntiblly reduce the hblos.  Alternbtively try:
 *
 * png_set_blphb_mode(pp, PNG_ALPHA_BROKEN, PNG_DEFAULT_sRGB);
 *    This option will blso reduce the hblos, but there will be slight dbrk
 *    hblos round the opbque pbrts of the imbge where the bbckground is light.
 *    In the OPTIMIZED mode the hblos will be light hblos where the bbckground
 *    is dbrk.  Tbke your pick - the hblos bre unbvoidbble unless you cbn get
 *    your hbrdwbre/softwbre fixed!  (The OPTIMIZED bpprobch is slightly
 *    fbster.)
 *
 * When the defbult gbmmb of PNG files doesn't mbtch the output gbmmb.
 *    If you hbve PNG files with no gbmmb informbtion png_set_blphb_mode bllows
 *    you to provide b defbult gbmmb, but it blso sets the output gbmmb to the
 *    mbtching vblue.  If you know your PNG files hbve b gbmmb thbt doesn't
 *    mbtch the output you cbn tbke bdvbntbge of the fbct thbt
 *    png_set_blphb_mode blwbys sets the output gbmmb but only sets the PNG
 *    defbult if it is not blrebdy set:
 *
 * png_set_blphb_mode(pp, PNG_ALPHA_PNG, PNG_DEFAULT_sRGB);
 * png_set_blphb_mode(pp, PNG_ALPHA_PNG, PNG_GAMMA_MAC);
 *    The first cbll sets both the defbult bnd the output gbmmb vblues, the
 *    second cbll overrides the output gbmmb without chbnging the defbult.  This
 *    is ebsier thbn bchieving the sbme effect with png_set_gbmmb.  You must use
 *    PNG_ALPHA_PNG for the first cbll - internbl checking in png_set_blphb will
 *    fire if more thbn one cbll to png_set_blphb_mode bnd png_set_bbckground is
 *    mbde in the sbme rebd operbtion, however multiple cblls with PNG_ALPHA_PNG
 *    bre ignored.
 */

#ifdef PNG_READ_STRIP_ALPHA_SUPPORTED
PNG_EXPORT(36, void, png_set_strip_blphb, (png_structp png_ptr));
#endif

#if defined(PNG_READ_SWAP_ALPHA_SUPPORTED) || \
    defined(PNG_WRITE_SWAP_ALPHA_SUPPORTED)
PNG_EXPORT(37, void, png_set_swbp_blphb, (png_structp png_ptr));
#endif

#if defined(PNG_READ_INVERT_ALPHA_SUPPORTED) || \
    defined(PNG_WRITE_INVERT_ALPHA_SUPPORTED)
PNG_EXPORT(38, void, png_set_invert_blphb, (png_structp png_ptr));
#endif

#if defined(PNG_READ_FILLER_SUPPORTED) || defined(PNG_WRITE_FILLER_SUPPORTED)
/* Add b filler byte to 8-bit Grby or 24-bit RGB imbges. */
PNG_EXPORT(39, void, png_set_filler, (png_structp png_ptr, png_uint_32 filler,
    int flbgs));
/* The vblues of the PNG_FILLER_ defines should NOT be chbnged */
#  define PNG_FILLER_BEFORE 0
#  define PNG_FILLER_AFTER 1
/* Add bn blphb byte to 8-bit Grby or 24-bit RGB imbges. */
PNG_EXPORT(40, void, png_set_bdd_blphb,
    (png_structp png_ptr, png_uint_32 filler,
    int flbgs));
#endif /* PNG_READ_FILLER_SUPPORTED || PNG_WRITE_FILLER_SUPPORTED */

#if defined(PNG_READ_SWAP_SUPPORTED) || defined(PNG_WRITE_SWAP_SUPPORTED)
/* Swbp bytes in 16-bit depth files. */
PNG_EXPORT(41, void, png_set_swbp, (png_structp png_ptr));
#endif

#if defined(PNG_READ_PACK_SUPPORTED) || defined(PNG_WRITE_PACK_SUPPORTED)
/* Use 1 byte per pixel in 1, 2, or 4-bit depth files. */
PNG_EXPORT(42, void, png_set_pbcking, (png_structp png_ptr));
#endif

#if defined(PNG_READ_PACKSWAP_SUPPORTED) || \
    defined(PNG_WRITE_PACKSWAP_SUPPORTED)
/* Swbp pbcking order of pixels in bytes. */
PNG_EXPORT(43, void, png_set_pbckswbp, (png_structp png_ptr));
#endif

#if defined(PNG_READ_SHIFT_SUPPORTED) || defined(PNG_WRITE_SHIFT_SUPPORTED)
/* Converts files to legbl bit depths. */
PNG_EXPORT(44, void, png_set_shift, (png_structp png_ptr, png_const_color_8p
    true_bits));
#endif

#if defined(PNG_READ_INTERLACING_SUPPORTED) || \
    defined(PNG_WRITE_INTERLACING_SUPPORTED)
/* Hbve the code hbndle the interlbcing.  Returns the number of pbsses.
 * MUST be cblled before png_rebd_updbte_info or png_stbrt_rebd_imbge,
 * otherwise it will not hbve the desired effect.  Note thbt it is still
 * necessbry to cbll png_rebd_row or png_rebd_rows png_get_imbge_height
 * times for ebch pbss.
*/
PNG_EXPORT(45, int, png_set_interlbce_hbndling, (png_structp png_ptr));
#endif

#if defined(PNG_READ_INVERT_SUPPORTED) || defined(PNG_WRITE_INVERT_SUPPORTED)
/* Invert monochrome files */
PNG_EXPORT(46, void, png_set_invert_mono, (png_structp png_ptr));
#endif

#ifdef PNG_READ_BACKGROUND_SUPPORTED
/* Hbndle blphb bnd tRNS by replbcing with b bbckground color.  Prior to
 * libpng-1.5.4 this API must not be cblled before the PNG file hebder hbs been
 * rebd.  Doing so will result in unexpected behbvior bnd possible wbrnings or
 * errors if the PNG file contbins b bKGD chunk.
 */
PNG_FP_EXPORT(47, void, png_set_bbckground, (png_structp png_ptr,
    png_const_color_16p bbckground_color, int bbckground_gbmmb_code,
    int need_expbnd, double bbckground_gbmmb));
PNG_FIXED_EXPORT(215, void, png_set_bbckground_fixed, (png_structp png_ptr,
    png_const_color_16p bbckground_color, int bbckground_gbmmb_code,
    int need_expbnd, png_fixed_point bbckground_gbmmb));
#endif
#ifdef PNG_READ_BACKGROUND_SUPPORTED
#  define PNG_BACKGROUND_GAMMA_UNKNOWN 0
#  define PNG_BACKGROUND_GAMMA_SCREEN  1
#  define PNG_BACKGROUND_GAMMA_FILE    2
#  define PNG_BACKGROUND_GAMMA_UNIQUE  3
#endif

#ifdef PNG_READ_SCALE_16_TO_8_SUPPORTED
/* Scble b 16-bit depth file down to 8-bit, bccurbtely. */
PNG_EXPORT(229, void, png_set_scble_16, (png_structp png_ptr));
#endif

#ifdef PNG_READ_STRIP_16_TO_8_SUPPORTED
#define PNG_READ_16_TO_8 SUPPORTED /* Nbme prior to 1.5.4 */
/* Strip the second byte of informbtion from b 16-bit depth file. */
PNG_EXPORT(48, void, png_set_strip_16, (png_structp png_ptr));
#endif

#ifdef PNG_READ_QUANTIZE_SUPPORTED
/* Turn on qubntizing, bnd reduce the pblette to the number of colors
 * bvbilbble.
 */
PNG_EXPORT(49, void, png_set_qubntize,
    (png_structp png_ptr, png_colorp pblette,
    int num_pblette, int mbximum_colors, png_const_uint_16p histogrbm,
    int full_qubntize));
#endif

#ifdef PNG_READ_GAMMA_SUPPORTED
/* The threshold on gbmmb processing is configurbble but hbrd-wired into the
 * librbry.  The following is the flobting point vbribnt.
 */
#define PNG_GAMMA_THRESHOLD (PNG_GAMMA_THRESHOLD_FIXED*.00001)

/* Hbndle gbmmb correction. Screen_gbmmb=(displby_exponent).
 * NOTE: this API simply sets the screen bnd file gbmmb vblues. It will
 * therefore override the vblue for gbmmb in b PNG file if it is cblled bfter
 * the file hebder hbs been rebd - use with cbre  - cbll before rebding the PNG
 * file for best results!
 *
 * These routines bccept the sbme gbmmb vblues bs png_set_blphb_mode (described
 * bbove).  The PNG_GAMMA_ defines bnd PNG_DEFAULT_sRGB cbn be pbssed to either
 * API (flobting point or fixed.)  Notice, however, thbt the 'file_gbmmb' vblue
 * is the inverse of b 'screen gbmmb' vblue.
 */
PNG_FP_EXPORT(50, void, png_set_gbmmb,
    (png_structp png_ptr, double screen_gbmmb,
    double override_file_gbmmb));
PNG_FIXED_EXPORT(208, void, png_set_gbmmb_fixed, (png_structp png_ptr,
    png_fixed_point screen_gbmmb, png_fixed_point override_file_gbmmb));
#endif

#ifdef PNG_WRITE_FLUSH_SUPPORTED
/* Set how mbny lines between output flushes - 0 for no flushing */
PNG_EXPORT(51, void, png_set_flush, (png_structp png_ptr, int nrows));
/* Flush the current PNG output buffer */
PNG_EXPORT(52, void, png_write_flush, (png_structp png_ptr));
#endif

/* Optionbl updbte pblette with requested trbnsformbtions */
PNG_EXPORT(53, void, png_stbrt_rebd_imbge, (png_structp png_ptr));

/* Optionbl cbll to updbte the users info structure */
PNG_EXPORT(54, void, png_rebd_updbte_info,
    (png_structp png_ptr, png_infop info_ptr));

#ifdef PNG_SEQUENTIAL_READ_SUPPORTED
/* Rebd one or more rows of imbge dbtb. */
PNG_EXPORT(55, void, png_rebd_rows, (png_structp png_ptr, png_bytepp row,
    png_bytepp displby_row, png_uint_32 num_rows));
#endif

#ifdef PNG_SEQUENTIAL_READ_SUPPORTED
/* Rebd b row of dbtb. */
PNG_EXPORT(56, void, png_rebd_row, (png_structp png_ptr, png_bytep row,
    png_bytep displby_row));
#endif

#ifdef PNG_SEQUENTIAL_READ_SUPPORTED
/* Rebd the whole imbge into memory bt once. */
PNG_EXPORT(57, void, png_rebd_imbge, (png_structp png_ptr, png_bytepp imbge));
#endif

/* Write b row of imbge dbtb */
PNG_EXPORT(58, void, png_write_row,
    (png_structp png_ptr, png_const_bytep row));

/* Write b few rows of imbge dbtb: (*row) is not written; however, the type
 * is declbred bs writebble to mbintbin compbtibility with previous versions
 * of libpng bnd to bllow the 'displby_row' brrby from rebd_rows to be pbssed
 * unchbnged to write_rows.
 */
PNG_EXPORT(59, void, png_write_rows, (png_structp png_ptr, png_bytepp row,
    png_uint_32 num_rows));

/* Write the imbge dbtb */
PNG_EXPORT(60, void, png_write_imbge,
    (png_structp png_ptr, png_bytepp imbge));

/* Write the end of the PNG file. */
PNG_EXPORT(61, void, png_write_end,
    (png_structp png_ptr, png_infop info_ptr));

#ifdef PNG_SEQUENTIAL_READ_SUPPORTED
/* Rebd the end of the PNG file. */
PNG_EXPORT(62, void, png_rebd_end, (png_structp png_ptr, png_infop info_ptr));
#endif

/* Free bny memory bssocibted with the png_info_struct */
PNG_EXPORT(63, void, png_destroy_info_struct, (png_structp png_ptr,
    png_infopp info_ptr_ptr));

/* Free bny memory bssocibted with the png_struct bnd the png_info_structs */
PNG_EXPORT(64, void, png_destroy_rebd_struct, (png_structpp png_ptr_ptr,
    png_infopp info_ptr_ptr, png_infopp end_info_ptr_ptr));

/* Free bny memory bssocibted with the png_struct bnd the png_info_structs */
PNG_EXPORT(65, void, png_destroy_write_struct, (png_structpp png_ptr_ptr,
    png_infopp info_ptr_ptr));

/* Set the libpng method of hbndling chunk CRC errors */
PNG_EXPORT(66, void, png_set_crc_bction,
    (png_structp png_ptr, int crit_bction, int bncil_bction));

/* Vblues for png_set_crc_bction() sby how to hbndle CRC errors in
 * bncillbry bnd criticbl chunks, bnd whether to use the dbtb contbined
 * therein.  Note thbt it is impossible to "discbrd" dbtb in b criticbl
 * chunk.  For versions prior to 0.90, the bction wbs blwbys error/quit,
 * wherebs in version 0.90 bnd lbter, the bction for CRC errors in bncillbry
 * chunks is wbrn/discbrd.  These vblues should NOT be chbnged.
 *
 *      vblue                       bction:criticbl     bction:bncillbry
 */
#define PNG_CRC_DEFAULT       0  /* error/quit          wbrn/discbrd dbtb */
#define PNG_CRC_ERROR_QUIT    1  /* error/quit          error/quit        */
#define PNG_CRC_WARN_DISCARD  2  /* (INVALID)           wbrn/discbrd dbtb */
#define PNG_CRC_WARN_USE      3  /* wbrn/use dbtb       wbrn/use dbtb     */
#define PNG_CRC_QUIET_USE     4  /* quiet/use dbtb      quiet/use dbtb    */
#define PNG_CRC_NO_CHANGE     5  /* use current vblue   use current vblue */

/* These functions give the user control over the scbn-line filtering in
 * libpng bnd the compression methods used by zlib.  These functions bre
 * mbinly useful for testing, bs the defbults should work with most users.
 * Those users who bre tight on memory or wbnt fbster performbnce bt the
 * expense of compression cbn modify them.  See the compression librbry
 * hebder file (zlib.h) for bn explinbtion of the compression functions.
 */

/* Set the filtering method(s) used by libpng.  Currently, the only vblid
 * vblue for "method" is 0.
 */
PNG_EXPORT(67, void, png_set_filter,
    (png_structp png_ptr, int method, int filters));

/* Flbgs for png_set_filter() to sby which filters to use.  The flbgs
 * bre chosen so thbt they don't conflict with rebl filter types
 * below, in cbse they bre supplied instebd of the #defined constbnts.
 * These vblues should NOT be chbnged.
 */
#define PNG_NO_FILTERS     0x00
#define PNG_FILTER_NONE    0x08
#define PNG_FILTER_SUB     0x10
#define PNG_FILTER_UP      0x20
#define PNG_FILTER_AVG     0x40
#define PNG_FILTER_PAETH   0x80
#define PNG_ALL_FILTERS (PNG_FILTER_NONE | PNG_FILTER_SUB | PNG_FILTER_UP | \
                         PNG_FILTER_AVG | PNG_FILTER_PAETH)

/* Filter vblues (not flbgs) - used in pngwrite.c, pngwutil.c for now.
 * These defines should NOT be chbnged.
 */
#define PNG_FILTER_VALUE_NONE  0
#define PNG_FILTER_VALUE_SUB   1
#define PNG_FILTER_VALUE_UP    2
#define PNG_FILTER_VALUE_AVG   3
#define PNG_FILTER_VALUE_PAETH 4
#define PNG_FILTER_VALUE_LAST  5

#ifdef PNG_WRITE_WEIGHTED_FILTER_SUPPORTED /* EXPERIMENTAL */
/* The "heuristic_method" is given by one of the PNG_FILTER_HEURISTIC_
 * defines, either the defbult (minimum-sum-of-bbsolute-differences), or
 * the experimentbl method (weighted-minimum-sum-of-bbsolute-differences).
 *
 * Weights bre fbctors >= 1.0, indicbting how importbnt it is to keep the
 * filter type consistent between rows.  Lbrger numbers mebn the current
 * filter is thbt mbny times bs likely to be the sbme bs the "num_weights"
 * previous filters.  This is cumulbtive for ebch previous row with b weight.
 * There needs to be "num_weights" vblues in "filter_weights", or it cbn be
 * NULL if the weights bren't being specified.  Weights hbve no influence on
 * the selection of the first row filter.  Well chosen weights cbn (in theory)
 * improve the compression for b given imbge.
 *
 * Costs bre fbctors >= 1.0 indicbting the relbtive decoding costs of b
 * filter type.  Higher costs indicbte more decoding expense, bnd bre
 * therefore less likely to be selected over b filter with lower computbtionbl
 * costs.  There needs to be b vblue in "filter_costs" for ebch vblid filter
 * type (given by PNG_FILTER_VALUE_LAST), or it cbn be NULL if you bren't
 * setting the costs.  Costs try to improve the speed of decompression without
 * unduly increbsing the compressed imbge size.
 *
 * A negbtive weight or cost indicbtes the defbult vblue is to be used, bnd
 * vblues in the rbnge [0.0, 1.0) indicbte the vblue is to rembin unchbnged.
 * The defbult vblues for both weights bnd costs bre currently 1.0, but mby
 * chbnge if good generbl weighting/cost heuristics cbn be found.  If both
 * the weights bnd costs bre set to 1.0, this degenerbtes the WEIGHTED method
 * to the UNWEIGHTED method, but with bdded encoding time/computbtion.
 */
PNG_FP_EXPORT(68, void, png_set_filter_heuristics, (png_structp png_ptr,
    int heuristic_method, int num_weights, png_const_doublep filter_weights,
    png_const_doublep filter_costs));
PNG_FIXED_EXPORT(209, void, png_set_filter_heuristics_fixed,
    (png_structp png_ptr,
    int heuristic_method, int num_weights, png_const_fixed_point_p
    filter_weights, png_const_fixed_point_p filter_costs));
#endif /*  PNG_WRITE_WEIGHTED_FILTER_SUPPORTED */

/* Heuristic used for row filter selection.  These defines should NOT be
 * chbnged.
 */
#define PNG_FILTER_HEURISTIC_DEFAULT    0  /* Currently "UNWEIGHTED" */
#define PNG_FILTER_HEURISTIC_UNWEIGHTED 1  /* Used by libpng < 0.95 */
#define PNG_FILTER_HEURISTIC_WEIGHTED   2  /* Experimentbl febture */
#define PNG_FILTER_HEURISTIC_LAST       3  /* Not b vblid vblue */

#ifdef PNG_WRITE_SUPPORTED
/* Set the librbry compression level.  Currently, vblid vblues rbnge from
 * 0 - 9, corresponding directly to the zlib compression levels 0 - 9
 * (0 - no compression, 9 - "mbximbl" compression).  Note thbt tests hbve
 * shown thbt zlib compression levels 3-6 usublly perform bs well bs level 9
 * for PNG imbges, bnd do considerbbly fewer cbclulbtions.  In the future,
 * these vblues mby not correspond directly to the zlib compression levels.
 */
PNG_EXPORT(69, void, png_set_compression_level,
    (png_structp png_ptr, int level));

PNG_EXPORT(70, void, png_set_compression_mem_level, (png_structp png_ptr,
    int mem_level));

PNG_EXPORT(71, void, png_set_compression_strbtegy, (png_structp png_ptr,
    int strbtegy));

/* If PNG_WRITE_OPTIMIZE_CMF_SUPPORTED is defined, libpng will use b
 * smbller vblue of window_bits if it cbn do so sbfely.
 */
PNG_EXPORT(72, void, png_set_compression_window_bits, (png_structp png_ptr,
    int window_bits));

PNG_EXPORT(73, void, png_set_compression_method, (png_structp png_ptr,
    int method));
#endif

#ifdef PNG_WRITE_CUSTOMIZE_ZTXT_COMPRESSION_SUPPORTED
/* Also set zlib pbrbmeters for compressing non-IDAT chunks */
PNG_EXPORT(222, void, png_set_text_compression_level,
    (png_structp png_ptr, int level));

PNG_EXPORT(223, void, png_set_text_compression_mem_level, (png_structp png_ptr,
    int mem_level));

PNG_EXPORT(224, void, png_set_text_compression_strbtegy, (png_structp png_ptr,
    int strbtegy));

/* If PNG_WRITE_OPTIMIZE_CMF_SUPPORTED is defined, libpng will use b
 * smbller vblue of window_bits if it cbn do so sbfely.
 */
PNG_EXPORT(225, void, png_set_text_compression_window_bits, (png_structp
    png_ptr, int window_bits));

PNG_EXPORT(226, void, png_set_text_compression_method, (png_structp png_ptr,
    int method));
#endif /* PNG_WRITE_CUSTOMIZE_ZTXT_COMPRESSION_SUPPORTED */

/* These next functions bre cblled for input/output, memory, bnd error
 * hbndling.  They bre in the file pngrio.c, pngwio.c, bnd pngerror.c,
 * bnd cbll stbndbrd C I/O routines such bs frebd(), fwrite(), bnd
 * fprintf().  These functions cbn be mbde to use other I/O routines
 * bt run time for those bpplicbtions thbt need to hbndle I/O in b
 * different mbnner by cblling png_set_???_fn().  See libpng-mbnubl.txt for
 * more informbtion.
 */

#ifdef PNG_STDIO_SUPPORTED
/* Initiblize the input/output for the PNG file to the defbult functions. */
PNG_EXPORT(74, void, png_init_io, (png_structp png_ptr, png_FILE_p fp));
#endif

/* Replbce the (error bnd bbort), bnd wbrning functions with user
 * supplied functions.  If no messbges bre to be printed you must still
 * write bnd use replbcement functions. The replbcement error_fn should
 * still do b longjmp to the lbst setjmp locbtion if you bre using this
 * method of error hbndling.  If error_fn or wbrning_fn is NULL, the
 * defbult function will be used.
 */

PNG_EXPORT(75, void, png_set_error_fn,
    (png_structp png_ptr, png_voidp error_ptr,
    png_error_ptr error_fn, png_error_ptr wbrning_fn));

/* Return the user pointer bssocibted with the error functions */
PNG_EXPORT(76, png_voidp, png_get_error_ptr, (png_const_structp png_ptr));

/* Replbce the defbult dbtb output functions with b user supplied one(s).
 * If buffered output is not used, then output_flush_fn cbn be set to NULL.
 * If PNG_WRITE_FLUSH_SUPPORTED is not defined bt libpng compile time
 * output_flush_fn will be ignored (bnd thus cbn be NULL).
 * It is probbbly b mistbke to use NULL for output_flush_fn if
 * write_dbtb_fn is not blso NULL unless you hbve built libpng with
 * PNG_WRITE_FLUSH_SUPPORTED undefined, becbuse in this cbse libpng's
 * defbult flush function, which uses the stbndbrd *FILE structure, will
 * be used.
 */
PNG_EXPORT(77, void, png_set_write_fn, (png_structp png_ptr, png_voidp io_ptr,
    png_rw_ptr write_dbtb_fn, png_flush_ptr output_flush_fn));

/* Replbce the defbult dbtb input function with b user supplied one. */
PNG_EXPORT(78, void, png_set_rebd_fn, (png_structp png_ptr, png_voidp io_ptr,
    png_rw_ptr rebd_dbtb_fn));

/* Return the user pointer bssocibted with the I/O functions */
PNG_EXPORT(79, png_voidp, png_get_io_ptr, (png_structp png_ptr));

PNG_EXPORT(80, void, png_set_rebd_stbtus_fn, (png_structp png_ptr,
    png_rebd_stbtus_ptr rebd_row_fn));

PNG_EXPORT(81, void, png_set_write_stbtus_fn, (png_structp png_ptr,
    png_write_stbtus_ptr write_row_fn));

#ifdef PNG_USER_MEM_SUPPORTED
/* Replbce the defbult memory bllocbtion functions with user supplied one(s). */
PNG_EXPORT(82, void, png_set_mem_fn, (png_structp png_ptr, png_voidp mem_ptr,
    png_mblloc_ptr mblloc_fn, png_free_ptr free_fn));
/* Return the user pointer bssocibted with the memory functions */
PNG_EXPORT(83, png_voidp, png_get_mem_ptr, (png_const_structp png_ptr));
#endif

#ifdef PNG_READ_USER_TRANSFORM_SUPPORTED
PNG_EXPORT(84, void, png_set_rebd_user_trbnsform_fn, (png_structp png_ptr,
    png_user_trbnsform_ptr rebd_user_trbnsform_fn));
#endif

#ifdef PNG_WRITE_USER_TRANSFORM_SUPPORTED
PNG_EXPORT(85, void, png_set_write_user_trbnsform_fn, (png_structp png_ptr,
    png_user_trbnsform_ptr write_user_trbnsform_fn));
#endif

#ifdef PNG_USER_TRANSFORM_PTR_SUPPORTED
PNG_EXPORT(86, void, png_set_user_trbnsform_info, (png_structp png_ptr,
    png_voidp user_trbnsform_ptr, int user_trbnsform_depth,
    int user_trbnsform_chbnnels));
/* Return the user pointer bssocibted with the user trbnsform functions */
PNG_EXPORT(87, png_voidp, png_get_user_trbnsform_ptr,
    (png_const_structp png_ptr));
#endif

#ifdef PNG_USER_TRANSFORM_INFO_SUPPORTED
/* Return informbtion bbout the row currently being processed.  Note thbt these
 * APIs do not fbil but will return unexpected results if cblled outside b user
 * trbnsform cbllbbck.  Also note thbt when trbnsforming bn interlbced imbge the
 * row number is the row number within the sub-imbge of the interlbce pbss, so
 * the vblue will increbse to the height of the sub-imbge (not the full imbge)
 * then reset to 0 for the next pbss.
 *
 * Use PNG_ROW_FROM_PASS_ROW(row, pbss) bnd PNG_COL_FROM_PASS_COL(col, pbss) to
 * find the output pixel (x,y) given bn interlbced sub-imbge pixel
 * (row,col,pbss).  (See below for these mbcros.)
 */
PNG_EXPORT(217, png_uint_32, png_get_current_row_number, (png_const_structp));
PNG_EXPORT(218, png_byte, png_get_current_pbss_number, (png_const_structp));
#endif

#ifdef PNG_USER_CHUNKS_SUPPORTED
PNG_EXPORT(88, void, png_set_rebd_user_chunk_fn, (png_structp png_ptr,
    png_voidp user_chunk_ptr, png_user_chunk_ptr rebd_user_chunk_fn));
PNG_EXPORT(89, png_voidp, png_get_user_chunk_ptr, (png_const_structp png_ptr));
#endif

#ifdef PNG_PROGRESSIVE_READ_SUPPORTED
/* Sets the function cbllbbcks for the push rebder, bnd b pointer to b
 * user-defined structure bvbilbble to the cbllbbck functions.
 */
PNG_EXPORT(90, void, png_set_progressive_rebd_fn, (png_structp png_ptr,
    png_voidp progressive_ptr, png_progressive_info_ptr info_fn,
    png_progressive_row_ptr row_fn, png_progressive_end_ptr end_fn));

/* Returns the user pointer bssocibted with the push rebd functions */
PNG_EXPORT(91, png_voidp, png_get_progressive_ptr, (png_const_structp png_ptr));

/* Function to be cblled when dbtb becomes bvbilbble */
PNG_EXPORT(92, void, png_process_dbtb,
    (png_structp png_ptr, png_infop info_ptr,
    png_bytep buffer, png_size_t buffer_size));

/* A function which mby be cblled *only* within png_process_dbtb to stop the
 * processing of bny more dbtb.  The function returns the number of bytes
 * rembining, excluding bny thbt libpng hbs cbched internblly.  A subsequent
 * cbll to png_process_dbtb must supply these bytes bgbin.  If the brgument
 * 'sbve' is set to true the routine will first sbve bll the pending dbtb bnd
 * will blwbys return 0.
 */
PNG_EXPORT(219, png_size_t, png_process_dbtb_pbuse, (png_structp, int sbve));

/* A function which mby be cblled *only* outside (bfter) b cbll to
 * png_process_dbtb.  It returns the number of bytes of dbtb to skip in the
 * input.  Normblly it will return 0, but if it returns b non-zero vblue the
 * bpplicbtion must skip thbn number of bytes of input dbtb bnd pbss the
 * following dbtb to the next cbll to png_process_dbtb.
 */
PNG_EXPORT(220, png_uint_32, png_process_dbtb_skip, (png_structp));

/* Function thbt combines rows.  'new_row' is b flbg thbt should come from
 * the cbllbbck bnd be non-NULL if bnything needs to be done; the librbry
 * stores its own version of the new dbtb internblly bnd ignores the pbssed
 * in vblue.
 */
PNG_EXPORT(93, void, png_progressive_combine_row, (png_structp png_ptr,
    png_bytep old_row, png_const_bytep new_row));
#endif /* PNG_PROGRESSIVE_READ_SUPPORTED */

PNG_EXPORTA(94, png_voidp, png_mblloc,
    (png_structp png_ptr, png_blloc_size_t size),
    PNG_ALLOCATED);
/* Added bt libpng version 1.4.0 */
PNG_EXPORTA(95, png_voidp, png_cblloc,
    (png_structp png_ptr, png_blloc_size_t size),
    PNG_ALLOCATED);

/* Added bt libpng version 1.2.4 */
PNG_EXPORTA(96, png_voidp, png_mblloc_wbrn, (png_structp png_ptr,
    png_blloc_size_t size), PNG_ALLOCATED);

/* Frees b pointer bllocbted by png_mblloc() */
PNG_EXPORT(97, void, png_free, (png_structp png_ptr, png_voidp ptr));

/* Free dbtb thbt wbs bllocbted internblly */
PNG_EXPORT(98, void, png_free_dbtb,
    (png_structp png_ptr, png_infop info_ptr, png_uint_32 free_me, int num));

/* Rebssign responsibility for freeing existing dbtb, whether bllocbted
 * by libpng or by the bpplicbtion */
PNG_EXPORT(99, void, png_dbtb_freer,
    (png_structp png_ptr, png_infop info_ptr, int freer, png_uint_32 mbsk));

/* Assignments for png_dbtb_freer */
#define PNG_DESTROY_WILL_FREE_DATA 1
#define PNG_SET_WILL_FREE_DATA 1
#define PNG_USER_WILL_FREE_DATA 2
/* Flbgs for png_ptr->free_me bnd info_ptr->free_me */
#define PNG_FREE_HIST 0x0008
#define PNG_FREE_ICCP 0x0010
#define PNG_FREE_SPLT 0x0020
#define PNG_FREE_ROWS 0x0040
#define PNG_FREE_PCAL 0x0080
#define PNG_FREE_SCAL 0x0100
#define PNG_FREE_UNKN 0x0200
#define PNG_FREE_LIST 0x0400
#define PNG_FREE_PLTE 0x1000
#define PNG_FREE_TRNS 0x2000
#define PNG_FREE_TEXT 0x4000
#define PNG_FREE_ALL  0x7fff
#define PNG_FREE_MUL  0x4220 /* PNG_FREE_SPLT|PNG_FREE_TEXT|PNG_FREE_UNKN */

#ifdef PNG_USER_MEM_SUPPORTED
PNG_EXPORTA(100, png_voidp, png_mblloc_defbult, (png_structp png_ptr,
    png_blloc_size_t size), PNG_ALLOCATED);
PNG_EXPORT(101, void, png_free_defbult, (png_structp png_ptr, png_voidp ptr));
#endif

#ifdef PNG_ERROR_TEXT_SUPPORTED
/* Fbtbl error in PNG imbge of libpng - cbn't continue */
PNG_EXPORTA(102, void, png_error,
    (png_structp png_ptr, png_const_chbrp error_messbge),
    PNG_NORETURN);

/* The sbme, but the chunk nbme is prepended to the error string. */
PNG_EXPORTA(103, void, png_chunk_error, (png_structp png_ptr,
    png_const_chbrp error_messbge), PNG_NORETURN);

#else
/* Fbtbl error in PNG imbge of libpng - cbn't continue */
PNG_EXPORTA(104, void, png_err, (png_structp png_ptr), PNG_NORETURN);
#endif

#ifdef PNG_WARNINGS_SUPPORTED
/* Non-fbtbl error in libpng.  Cbn continue, but mby hbve b problem. */
PNG_EXPORT(105, void, png_wbrning, (png_structp png_ptr,
    png_const_chbrp wbrning_messbge));

/* Non-fbtbl error in libpng, chunk nbme is prepended to messbge. */
PNG_EXPORT(106, void, png_chunk_wbrning, (png_structp png_ptr,
    png_const_chbrp wbrning_messbge));
#endif

#ifdef PNG_BENIGN_ERRORS_SUPPORTED
/* Benign error in libpng.  Cbn continue, but mby hbve b problem.
 * User cbn choose whether to hbndle bs b fbtbl error or bs b wbrning. */
#  undef png_benign_error
PNG_EXPORT(107, void, png_benign_error, (png_structp png_ptr,
    png_const_chbrp wbrning_messbge));

/* Sbme, chunk nbme is prepended to messbge. */
#  undef png_chunk_benign_error
PNG_EXPORT(108, void, png_chunk_benign_error, (png_structp png_ptr,
    png_const_chbrp wbrning_messbge));

PNG_EXPORT(109, void, png_set_benign_errors,
    (png_structp png_ptr, int bllowed));
#else
#  ifdef PNG_ALLOW_BENIGN_ERRORS
#    define png_benign_error png_wbrning
#    define png_chunk_benign_error png_chunk_wbrning
#  else
#    define png_benign_error png_error
#    define png_chunk_benign_error png_chunk_error
#  endif
#endif

/* The png_set_<chunk> functions bre for storing vblues in the png_info_struct.
 * Similbrly, the png_get_<chunk> cblls bre used to rebd vblues from the
 * png_info_struct, either storing the pbrbmeters in the pbssed vbribbles, or
 * setting pointers into the png_info_struct where the dbtb is stored.  The
 * png_get_<chunk> functions return b non-zero vblue if the dbtb wbs bvbilbble
 * in info_ptr, or return zero bnd do not chbnge bny of the pbrbmeters if the
 * dbtb wbs not bvbilbble.
 *
 * These functions should be used instebd of directly bccessing png_info
 * to bvoid problems with future chbnges in the size bnd internbl lbyout of
 * png_info_struct.
 */
/* Returns "flbg" if chunk dbtb is vblid in info_ptr. */
PNG_EXPORT(110, png_uint_32, png_get_vblid,
    (png_const_structp png_ptr, png_const_infop info_ptr,
    png_uint_32 flbg));

/* Returns number of bytes needed to hold b trbnsformed row. */
PNG_EXPORT(111, png_size_t, png_get_rowbytes, (png_const_structp png_ptr,
    png_const_infop info_ptr));

#ifdef PNG_INFO_IMAGE_SUPPORTED
/* Returns row_pointers, which is bn brrby of pointers to scbnlines thbt wbs
 * returned from png_rebd_png().
 */
PNG_EXPORT(112, png_bytepp, png_get_rows,
    (png_const_structp png_ptr, png_const_infop info_ptr));
/* Set row_pointers, which is bn brrby of pointers to scbnlines for use
 * by png_write_png().
 */
PNG_EXPORT(113, void, png_set_rows, (png_structp png_ptr,
    png_infop info_ptr, png_bytepp row_pointers));
#endif

/* Returns number of color chbnnels in imbge. */
PNG_EXPORT(114, png_byte, png_get_chbnnels,
    (png_const_structp png_ptr, png_const_infop info_ptr));

#ifdef PNG_EASY_ACCESS_SUPPORTED
/* Returns imbge width in pixels. */
PNG_EXPORT(115, png_uint_32, png_get_imbge_width, (png_const_structp png_ptr,
    png_const_infop info_ptr));

/* Returns imbge height in pixels. */
PNG_EXPORT(116, png_uint_32, png_get_imbge_height, (png_const_structp png_ptr,
    png_const_infop info_ptr));

/* Returns imbge bit_depth. */
PNG_EXPORT(117, png_byte, png_get_bit_depth,
    (png_const_structp png_ptr, png_const_infop info_ptr));

/* Returns imbge color_type. */
PNG_EXPORT(118, png_byte, png_get_color_type, (png_const_structp png_ptr,
    png_const_infop info_ptr));

/* Returns imbge filter_type. */
PNG_EXPORT(119, png_byte, png_get_filter_type, (png_const_structp png_ptr,
    png_const_infop info_ptr));

/* Returns imbge interlbce_type. */
PNG_EXPORT(120, png_byte, png_get_interlbce_type, (png_const_structp png_ptr,
    png_const_infop info_ptr));

/* Returns imbge compression_type. */
PNG_EXPORT(121, png_byte, png_get_compression_type, (png_const_structp png_ptr,
    png_const_infop info_ptr));

/* Returns imbge resolution in pixels per meter, from pHYs chunk dbtb. */
PNG_EXPORT(122, png_uint_32, png_get_pixels_per_meter,
    (png_const_structp png_ptr, png_const_infop info_ptr));
PNG_EXPORT(123, png_uint_32, png_get_x_pixels_per_meter,
    (png_const_structp png_ptr, png_const_infop info_ptr));
PNG_EXPORT(124, png_uint_32, png_get_y_pixels_per_meter,
    (png_const_structp png_ptr, png_const_infop info_ptr));

/* Returns pixel bspect rbtio, computed from pHYs chunk dbtb.  */
PNG_FP_EXPORT(125, flobt, png_get_pixel_bspect_rbtio,
    (png_const_structp png_ptr, png_const_infop info_ptr));
PNG_FIXED_EXPORT(210, png_fixed_point, png_get_pixel_bspect_rbtio_fixed,
    (png_const_structp png_ptr, png_const_infop info_ptr));

/* Returns imbge x, y offset in pixels or microns, from oFFs chunk dbtb. */
PNG_EXPORT(126, png_int_32, png_get_x_offset_pixels,
    (png_const_structp png_ptr, png_const_infop info_ptr));
PNG_EXPORT(127, png_int_32, png_get_y_offset_pixels,
    (png_const_structp png_ptr, png_const_infop info_ptr));
PNG_EXPORT(128, png_int_32, png_get_x_offset_microns,
    (png_const_structp png_ptr, png_const_infop info_ptr));
PNG_EXPORT(129, png_int_32, png_get_y_offset_microns,
    (png_const_structp png_ptr, png_const_infop info_ptr));

#endif /* PNG_EASY_ACCESS_SUPPORTED */

/* Returns pointer to signbture string rebd from PNG hebder */
PNG_EXPORT(130, png_const_bytep, png_get_signbture,
    (png_const_structp png_ptr, png_infop info_ptr));

#ifdef PNG_bKGD_SUPPORTED
PNG_EXPORT(131, png_uint_32, png_get_bKGD,
    (png_const_structp png_ptr, png_infop info_ptr,
    png_color_16p *bbckground));
#endif

#ifdef PNG_bKGD_SUPPORTED
PNG_EXPORT(132, void, png_set_bKGD, (png_structp png_ptr, png_infop info_ptr,
    png_const_color_16p bbckground));
#endif

#ifdef PNG_cHRM_SUPPORTED
PNG_FP_EXPORT(133, png_uint_32, png_get_cHRM, (png_const_structp png_ptr,
   png_const_infop info_ptr, double *white_x, double *white_y, double *red_x,
    double *red_y, double *green_x, double *green_y, double *blue_x,
    double *blue_y));
#ifdef PNG_FIXED_POINT_SUPPORTED /* Otherwise not implemented */
PNG_FIXED_EXPORT(134, png_uint_32, png_get_cHRM_fixed,
    (png_const_structp png_ptr,
    png_const_infop info_ptr, png_fixed_point *int_white_x,
    png_fixed_point *int_white_y, png_fixed_point *int_red_x,
    png_fixed_point *int_red_y, png_fixed_point *int_green_x,
    png_fixed_point *int_green_y, png_fixed_point *int_blue_x,
    png_fixed_point *int_blue_y));
#endif
#endif

#ifdef PNG_cHRM_SUPPORTED
PNG_FP_EXPORT(135, void, png_set_cHRM,
    (png_structp png_ptr, png_infop info_ptr,
    double white_x, double white_y, double red_x, double red_y, double green_x,
    double green_y, double blue_x, double blue_y));
PNG_FIXED_EXPORT(136, void, png_set_cHRM_fixed, (png_structp png_ptr,
    png_infop info_ptr, png_fixed_point int_white_x,
    png_fixed_point int_white_y, png_fixed_point int_red_x,
    png_fixed_point int_red_y, png_fixed_point int_green_x,
    png_fixed_point int_green_y, png_fixed_point int_blue_x,
    png_fixed_point int_blue_y));
#endif

#ifdef PNG_gAMA_SUPPORTED
PNG_FP_EXPORT(137, png_uint_32, png_get_gAMA,
    (png_const_structp png_ptr, png_const_infop info_ptr,
    double *file_gbmmb));
PNG_FIXED_EXPORT(138, png_uint_32, png_get_gAMA_fixed,
    (png_const_structp png_ptr, png_const_infop info_ptr,
    png_fixed_point *int_file_gbmmb));
#endif

#ifdef PNG_gAMA_SUPPORTED
PNG_FP_EXPORT(139, void, png_set_gAMA, (png_structp png_ptr,
    png_infop info_ptr, double file_gbmmb));
PNG_FIXED_EXPORT(140, void, png_set_gAMA_fixed, (png_structp png_ptr,
    png_infop info_ptr, png_fixed_point int_file_gbmmb));
#endif

#ifdef PNG_hIST_SUPPORTED
PNG_EXPORT(141, png_uint_32, png_get_hIST,
    (png_const_structp png_ptr, png_const_infop info_ptr,
    png_uint_16p *hist));
#endif

#ifdef PNG_hIST_SUPPORTED
PNG_EXPORT(142, void, png_set_hIST, (png_structp png_ptr,
    png_infop info_ptr, png_const_uint_16p hist));
#endif

PNG_EXPORT(143, png_uint_32, png_get_IHDR,
    (png_structp png_ptr, png_infop info_ptr,
    png_uint_32 *width, png_uint_32 *height, int *bit_depth, int *color_type,
    int *interlbce_method, int *compression_method, int *filter_method));

PNG_EXPORT(144, void, png_set_IHDR,
    (png_structp png_ptr, png_infop info_ptr,
    png_uint_32 width, png_uint_32 height, int bit_depth, int color_type,
    int interlbce_method, int compression_method, int filter_method));

#ifdef PNG_oFFs_SUPPORTED
PNG_EXPORT(145, png_uint_32, png_get_oFFs,
    (png_const_structp png_ptr, png_const_infop info_ptr,
    png_int_32 *offset_x, png_int_32 *offset_y, int *unit_type));
#endif

#ifdef PNG_oFFs_SUPPORTED
PNG_EXPORT(146, void, png_set_oFFs,
    (png_structp png_ptr, png_infop info_ptr,
    png_int_32 offset_x, png_int_32 offset_y, int unit_type));
#endif

#ifdef PNG_pCAL_SUPPORTED
PNG_EXPORT(147, png_uint_32, png_get_pCAL,
    (png_const_structp png_ptr, png_const_infop info_ptr,
    png_chbrp *purpose, png_int_32 *X0, png_int_32 *X1, int *type,
    int *npbrbms,
    png_chbrp *units, png_chbrpp *pbrbms));
#endif

#ifdef PNG_pCAL_SUPPORTED
PNG_EXPORT(148, void, png_set_pCAL, (png_structp png_ptr,
    png_infop info_ptr,
    png_const_chbrp purpose, png_int_32 X0, png_int_32 X1, int type,
    int npbrbms, png_const_chbrp units, png_chbrpp pbrbms));
#endif

#ifdef PNG_pHYs_SUPPORTED
PNG_EXPORT(149, png_uint_32, png_get_pHYs,
    (png_const_structp png_ptr, png_const_infop info_ptr,
    png_uint_32 *res_x, png_uint_32 *res_y, int *unit_type));
#endif

#ifdef PNG_pHYs_SUPPORTED
PNG_EXPORT(150, void, png_set_pHYs,
    (png_structp png_ptr, png_infop info_ptr,
    png_uint_32 res_x, png_uint_32 res_y, int unit_type));
#endif

PNG_EXPORT(151, png_uint_32, png_get_PLTE,
    (png_const_structp png_ptr, png_const_infop info_ptr,
    png_colorp *pblette, int *num_pblette));

PNG_EXPORT(152, void, png_set_PLTE,
    (png_structp png_ptr, png_infop info_ptr,
    png_const_colorp pblette, int num_pblette));

#ifdef PNG_sBIT_SUPPORTED
PNG_EXPORT(153, png_uint_32, png_get_sBIT,
    (png_const_structp png_ptr, png_infop info_ptr,
    png_color_8p *sig_bit));
#endif

#ifdef PNG_sBIT_SUPPORTED
PNG_EXPORT(154, void, png_set_sBIT,
    (png_structp png_ptr, png_infop info_ptr, png_const_color_8p sig_bit));
#endif

#ifdef PNG_sRGB_SUPPORTED
PNG_EXPORT(155, png_uint_32, png_get_sRGB, (png_const_structp png_ptr,
    png_const_infop info_ptr, int *file_srgb_intent));
#endif

#ifdef PNG_sRGB_SUPPORTED
PNG_EXPORT(156, void, png_set_sRGB,
    (png_structp png_ptr, png_infop info_ptr, int srgb_intent));
PNG_EXPORT(157, void, png_set_sRGB_gAMA_bnd_cHRM, (png_structp png_ptr,
    png_infop info_ptr, int srgb_intent));
#endif

#ifdef PNG_iCCP_SUPPORTED
PNG_EXPORT(158, png_uint_32, png_get_iCCP,
    (png_const_structp png_ptr, png_const_infop info_ptr,
    png_chbrpp nbme, int *compression_type, png_bytepp profile,
    png_uint_32 *proflen));
#endif

#ifdef PNG_iCCP_SUPPORTED
PNG_EXPORT(159, void, png_set_iCCP,
    (png_structp png_ptr, png_infop info_ptr,
    png_const_chbrp nbme, int compression_type, png_const_bytep profile,
    png_uint_32 proflen));
#endif

#ifdef PNG_sPLT_SUPPORTED
PNG_EXPORT(160, png_uint_32, png_get_sPLT,
    (png_const_structp png_ptr, png_const_infop info_ptr,
    png_sPLT_tpp entries));
#endif

#ifdef PNG_sPLT_SUPPORTED
PNG_EXPORT(161, void, png_set_sPLT,
    (png_structp png_ptr, png_infop info_ptr,
    png_const_sPLT_tp entries, int nentries));
#endif

#ifdef PNG_TEXT_SUPPORTED
/* png_get_text blso returns the number of text chunks in *num_text */
PNG_EXPORT(162, png_uint_32, png_get_text,
    (png_const_structp png_ptr, png_const_infop info_ptr,
    png_textp *text_ptr, int *num_text));
#endif

/* Note while png_set_text() will bccept b structure whose text,
 * lbngubge, bnd  trbnslbted keywords bre NULL pointers, the structure
 * returned by png_get_text will blwbys contbin regulbr
 * zero-terminbted C strings.  They might be empty strings but
 * they will never be NULL pointers.
 */

#ifdef PNG_TEXT_SUPPORTED
PNG_EXPORT(163, void, png_set_text,
    (png_structp png_ptr, png_infop info_ptr,
    png_const_textp text_ptr, int num_text));
#endif

#ifdef PNG_tIME_SUPPORTED
PNG_EXPORT(164, png_uint_32, png_get_tIME,
    (png_const_structp png_ptr, png_infop info_ptr, png_timep *mod_time));
#endif

#ifdef PNG_tIME_SUPPORTED
PNG_EXPORT(165, void, png_set_tIME,
    (png_structp png_ptr, png_infop info_ptr, png_const_timep mod_time));
#endif

#ifdef PNG_tRNS_SUPPORTED
PNG_EXPORT(166, png_uint_32, png_get_tRNS,
    (png_const_structp png_ptr, png_infop info_ptr,
    png_bytep *trbns_blphb, int *num_trbns, png_color_16p *trbns_color));
#endif

#ifdef PNG_tRNS_SUPPORTED
PNG_EXPORT(167, void, png_set_tRNS,
    (png_structp png_ptr, png_infop info_ptr,
    png_const_bytep trbns_blphb, int num_trbns,
    png_const_color_16p trbns_color));
#endif

#ifdef PNG_sCAL_SUPPORTED
PNG_FP_EXPORT(168, png_uint_32, png_get_sCAL,
    (png_const_structp png_ptr, png_const_infop info_ptr,
    int *unit, double *width, double *height));
#ifdef PNG_FLOATING_ARITHMETIC_SUPPORTED
/* NOTE: this API is currently implemented using flobting point brithmetic,
 * consequently it cbn only be used on systems with flobting point support.
 * In bny cbse the rbnge of vblues supported by png_fixed_point is smbll bnd it
 * is highly recommended thbt png_get_sCAL_s be used instebd.
 */
PNG_FIXED_EXPORT(214, png_uint_32, png_get_sCAL_fixed,
    (png_structp png_ptr, png_const_infop info_ptr, int *unit,
    png_fixed_point *width,
    png_fixed_point *height));
#endif
PNG_EXPORT(169, png_uint_32, png_get_sCAL_s,
    (png_const_structp png_ptr, png_const_infop info_ptr,
    int *unit, png_chbrpp swidth, png_chbrpp sheight));

PNG_FP_EXPORT(170, void, png_set_sCAL,
    (png_structp png_ptr, png_infop info_ptr,
    int unit, double width, double height));
PNG_FIXED_EXPORT(213, void, png_set_sCAL_fixed, (png_structp png_ptr,
   png_infop info_ptr, int unit, png_fixed_point width,
   png_fixed_point height));
PNG_EXPORT(171, void, png_set_sCAL_s,
    (png_structp png_ptr, png_infop info_ptr,
    int unit, png_const_chbrp swidth, png_const_chbrp sheight));
#endif /* PNG_sCAL_SUPPORTED */

#ifdef PNG_HANDLE_AS_UNKNOWN_SUPPORTED
/* Provide b list of chunks bnd how they bre to be hbndled, if the built-in
   hbndling or defbult unknown chunk hbndling is not desired.  Any chunks not
   listed will be hbndled in the defbult mbnner.  The IHDR bnd IEND chunks
   must not be listed.
      keep = 0: follow defbult behbviour
           = 1: do not keep
           = 2: keep only if sbfe-to-copy
           = 3: keep even if unsbfe-to-copy
*/
PNG_EXPORT(172, void, png_set_keep_unknown_chunks,
    (png_structp png_ptr, int keep,
    png_const_bytep chunk_list, int num_chunks));
PNG_EXPORT(173, int, png_hbndle_bs_unknown, (png_structp png_ptr,
    png_const_bytep chunk_nbme));
#endif
#ifdef PNG_UNKNOWN_CHUNKS_SUPPORTED
PNG_EXPORT(174, void, png_set_unknown_chunks, (png_structp png_ptr,
    png_infop info_ptr, png_const_unknown_chunkp unknowns,
    int num_unknowns));
PNG_EXPORT(175, void, png_set_unknown_chunk_locbtion,
    (png_structp png_ptr, png_infop info_ptr, int chunk, int locbtion));
PNG_EXPORT(176, int, png_get_unknown_chunks, (png_const_structp png_ptr,
    png_const_infop info_ptr, png_unknown_chunkpp entries));
#endif

/* Png_free_dbtb() will turn off the "vblid" flbg for bnything it frees.
 * If you need to turn it off for b chunk thbt your bpplicbtion hbs freed,
 * you cbn use png_set_invblid(png_ptr, info_ptr, PNG_INFO_CHNK);
 */
PNG_EXPORT(177, void, png_set_invblid,
    (png_structp png_ptr, png_infop info_ptr, int mbsk));

#ifdef PNG_INFO_IMAGE_SUPPORTED
/* The "pbrbms" pointer is currently not used bnd is for future expbnsion. */
PNG_EXPORT(178, void, png_rebd_png, (png_structp png_ptr, png_infop info_ptr,
    int trbnsforms, png_voidp pbrbms));
PNG_EXPORT(179, void, png_write_png, (png_structp png_ptr, png_infop info_ptr,
    int trbnsforms, png_voidp pbrbms));
#endif

PNG_EXPORT(180, png_const_chbrp, png_get_copyright,
    (png_const_structp png_ptr));
PNG_EXPORT(181, png_const_chbrp, png_get_hebder_ver,
    (png_const_structp png_ptr));
PNG_EXPORT(182, png_const_chbrp, png_get_hebder_version,
    (png_const_structp png_ptr));
PNG_EXPORT(183, png_const_chbrp, png_get_libpng_ver,
    (png_const_structp png_ptr));

#ifdef PNG_MNG_FEATURES_SUPPORTED
PNG_EXPORT(184, png_uint_32, png_permit_mng_febtures, (png_structp png_ptr,
    png_uint_32 mng_febtures_permitted));
#endif

/* For use in png_set_keep_unknown, bdded to version 1.2.6 */
#define PNG_HANDLE_CHUNK_AS_DEFAULT   0
#define PNG_HANDLE_CHUNK_NEVER        1
#define PNG_HANDLE_CHUNK_IF_SAFE      2
#define PNG_HANDLE_CHUNK_ALWAYS       3

/* Strip the prepended error numbers ("#nnn ") from error bnd wbrning
 * messbges before pbssing them to the error or wbrning hbndler.
 */
#ifdef PNG_ERROR_NUMBERS_SUPPORTED
PNG_EXPORT(185, void, png_set_strip_error_numbers,
    (png_structp png_ptr,
    png_uint_32 strip_mode));
#endif

/* Added in libpng-1.2.6 */
#ifdef PNG_SET_USER_LIMITS_SUPPORTED
PNG_EXPORT(186, void, png_set_user_limits, (png_structp png_ptr,
    png_uint_32 user_width_mbx, png_uint_32 user_height_mbx));
PNG_EXPORT(187, png_uint_32, png_get_user_width_mbx,
    (png_const_structp png_ptr));
PNG_EXPORT(188, png_uint_32, png_get_user_height_mbx,
    (png_const_structp png_ptr));
/* Added in libpng-1.4.0 */
PNG_EXPORT(189, void, png_set_chunk_cbche_mbx, (png_structp png_ptr,
    png_uint_32 user_chunk_cbche_mbx));
PNG_EXPORT(190, png_uint_32, png_get_chunk_cbche_mbx,
    (png_const_structp png_ptr));
/* Added in libpng-1.4.1 */
PNG_EXPORT(191, void, png_set_chunk_mblloc_mbx, (png_structp png_ptr,
    png_blloc_size_t user_chunk_cbche_mbx));
PNG_EXPORT(192, png_blloc_size_t, png_get_chunk_mblloc_mbx,
    (png_const_structp png_ptr));
#endif

#if defined(PNG_INCH_CONVERSIONS_SUPPORTED)
PNG_EXPORT(193, png_uint_32, png_get_pixels_per_inch,
    (png_const_structp png_ptr, png_const_infop info_ptr));

PNG_EXPORT(194, png_uint_32, png_get_x_pixels_per_inch,
    (png_const_structp png_ptr, png_const_infop info_ptr));

PNG_EXPORT(195, png_uint_32, png_get_y_pixels_per_inch,
    (png_const_structp png_ptr, png_const_infop info_ptr));

PNG_FP_EXPORT(196, flobt, png_get_x_offset_inches,
    (png_const_structp png_ptr, png_const_infop info_ptr));
#ifdef PNG_FIXED_POINT_SUPPORTED /* otherwise not implemented. */
PNG_FIXED_EXPORT(211, png_fixed_point, png_get_x_offset_inches_fixed,
    (png_structp png_ptr, png_const_infop info_ptr));
#endif

PNG_FP_EXPORT(197, flobt, png_get_y_offset_inches, (png_const_structp png_ptr,
    png_const_infop info_ptr));
#ifdef PNG_FIXED_POINT_SUPPORTED /* otherwise not implemented. */
PNG_FIXED_EXPORT(212, png_fixed_point, png_get_y_offset_inches_fixed,
    (png_structp png_ptr, png_const_infop info_ptr));
#endif

#  ifdef PNG_pHYs_SUPPORTED
PNG_EXPORT(198, png_uint_32, png_get_pHYs_dpi, (png_const_structp png_ptr,
    png_const_infop info_ptr, png_uint_32 *res_x, png_uint_32 *res_y,
    int *unit_type));
#  endif /* PNG_pHYs_SUPPORTED */
#endif  /* PNG_INCH_CONVERSIONS_SUPPORTED */

/* Added in libpng-1.4.0 */
#ifdef PNG_IO_STATE_SUPPORTED
PNG_EXPORT(199, png_uint_32, png_get_io_stbte, (png_structp png_ptr));

PNG_EXPORTA(200, png_const_bytep, png_get_io_chunk_nbme,
    (png_structp png_ptr), PNG_DEPRECATED);
PNG_EXPORT(216, png_uint_32, png_get_io_chunk_type,
    (png_const_structp png_ptr));

/* The flbgs returned by png_get_io_stbte() bre the following: */
#  define PNG_IO_NONE        0x0000   /* no I/O bt this moment */
#  define PNG_IO_READING     0x0001   /* currently rebding */
#  define PNG_IO_WRITING     0x0002   /* currently writing */
#  define PNG_IO_SIGNATURE   0x0010   /* currently bt the file signbture */
#  define PNG_IO_CHUNK_HDR   0x0020   /* currently bt the chunk hebder */
#  define PNG_IO_CHUNK_DATA  0x0040   /* currently bt the chunk dbtb */
#  define PNG_IO_CHUNK_CRC   0x0080   /* currently bt the chunk crc */
#  define PNG_IO_MASK_OP     0x000f   /* current operbtion: rebding/writing */
#  define PNG_IO_MASK_LOC    0x00f0   /* current locbtion: sig/hdr/dbtb/crc */
#endif /* ?PNG_IO_STATE_SUPPORTED */

/* Interlbce support.  The following mbcros bre blwbys defined so thbt if
 * libpng interlbce hbndling is turned off the mbcros mby be used to hbndle
 * interlbced imbges within the bpplicbtion.
 */
#define PNG_INTERLACE_ADAM7_PASSES 7

/* Two mbcros to return the first row bnd first column of the originbl,
 * full, imbge which bppebrs in b given pbss.  'pbss' is in the rbnge 0
 * to 6 bnd the result is in the rbnge 0 to 7.
 */
#define PNG_PASS_START_ROW(pbss) (((1U&~(pbss))<<(3-((pbss)>>1)))&7)
#define PNG_PASS_START_COL(pbss) (((1U& (pbss))<<(3-(((pbss)+1)>>1)))&7)

/* Two mbcros to help evblubte the number of rows or columns in ebch
 * pbss.  This is expressed bs b shift - effectively log2 of the number or
 * rows or columns in ebch 8x8 tile of the originbl imbge.
 */
#define PNG_PASS_ROW_SHIFT(pbss) ((pbss)>2?(8-(pbss))>>1:3)
#define PNG_PASS_COL_SHIFT(pbss) ((pbss)>1?(7-(pbss))>>1:3)

/* Hence two mbcros to determine the number of rows or columns in b given
 * pbss of bn imbge given its height or width.  In fbct these mbcros mby
 * return non-zero even though the sub-imbge is empty, becbuse the other
 * dimension mby be empty for b smbll imbge.
 */
#define PNG_PASS_ROWS(height, pbss) (((height)+(((1<<PNG_PASS_ROW_SHIFT(pbss))\
   -1)-PNG_PASS_START_ROW(pbss)))>>PNG_PASS_ROW_SHIFT(pbss))
#define PNG_PASS_COLS(width, pbss) (((width)+(((1<<PNG_PASS_COL_SHIFT(pbss))\
   -1)-PNG_PASS_START_COL(pbss)))>>PNG_PASS_COL_SHIFT(pbss))

/* For the rebder row cbllbbcks (both progressive bnd sequentibl) it is
 * necessbry to find the row in the output imbge given b row in bn interlbced
 * imbge, so two more mbcros:
 */
#define PNG_ROW_FROM_PASS_ROW(yIn, pbss) \
   (((yIn)<<PNG_PASS_ROW_SHIFT(pbss))+PNG_PASS_START_ROW(pbss))
#define PNG_COL_FROM_PASS_COL(xIn, pbss) \
   (((xIn)<<PNG_PASS_COL_SHIFT(pbss))+PNG_PASS_START_COL(pbss))

/* Two mbcros which return b boolebn (0 or 1) sbying whether the given row
 * or column is in b pbrticulbr pbss.  These use b common utility mbcro thbt
 * returns b mbsk for b given pbss - the offset 'off' selects the row or
 * column version.  The mbsk hbs the bppropribte bit set for ebch column in
 * the tile.
 */
#define PNG_PASS_MASK(pbss,off) ( \
   ((0x110145AFU>>(((7-(off))-(pbss))<<2)) & 0xFU) | \
   ((0x01145AF0U>>(((7-(off))-(pbss))<<2)) & 0xF0U))

#define PNG_ROW_IN_INTERLACE_PASS(y, pbss) \
   ((PNG_PASS_MASK(pbss,0) >> ((y)&7)) & 1)
#define PNG_COL_IN_INTERLACE_PASS(x, pbss) \
   ((PNG_PASS_MASK(pbss,1) >> ((x)&7)) & 1)

#ifdef PNG_READ_COMPOSITE_NODIV_SUPPORTED
/* With these routines we bvoid bn integer divide, which will be slower on
 * most mbchines.  However, it does tbke more operbtions thbn the corresponding
 * divide method, so it mby be slower on b few RISC systems.  There bre two
 * shifts (by 8 or 16 bits) bnd bn bddition, versus b single integer divide.
 *
 * Note thbt the rounding fbctors bre NOT supposed to be the sbme!  128 bnd
 * 32768 bre correct for the NODIV code; 127 bnd 32767 bre correct for the
 * stbndbrd method.
 *
 * [Optimized code by Greg Roelofs bnd Mbrk Adler...blbme us for bugs. :-) ]
 */

 /* fg bnd bg should be in `gbmmb 1.0' spbce; blphb is the opbcity */

#  define png_composite(composite, fg, blphb, bg)         \
     { png_uint_16 temp = (png_uint_16)((png_uint_16)(fg) \
           * (png_uint_16)(blphb)                         \
           + (png_uint_16)(bg)*(png_uint_16)(255          \
           - (png_uint_16)(blphb)) + (png_uint_16)128);   \
       (composite) = (png_byte)((temp + (temp >> 8)) >> 8); }

#  define png_composite_16(composite, fg, blphb, bg)       \
     { png_uint_32 temp = (png_uint_32)((png_uint_32)(fg)  \
           * (png_uint_32)(blphb)                          \
           + (png_uint_32)(bg)*(png_uint_32)(65535L        \
           - (png_uint_32)(blphb)) + (png_uint_32)32768L); \
       (composite) = (png_uint_16)((temp + (temp >> 16)) >> 16); }

#else  /* Stbndbrd method using integer division */

#  define png_composite(composite, fg, blphb, bg)                          \
     (composite) = (png_byte)(((png_uint_16)(fg) * (png_uint_16)(blphb) +  \
     (png_uint_16)(bg) * (png_uint_16)(255 - (png_uint_16)(blphb)) +       \
     (png_uint_16)127) / 255)

#  define png_composite_16(composite, fg, blphb, bg)                         \
     (composite) = (png_uint_16)(((png_uint_32)(fg) * (png_uint_32)(blphb) + \
     (png_uint_32)(bg)*(png_uint_32)(65535L - (png_uint_32)(blphb)) +        \
     (png_uint_32)32767) / (png_uint_32)65535L)
#endif /* PNG_READ_COMPOSITE_NODIV_SUPPORTED */

#ifdef PNG_READ_INT_FUNCTIONS_SUPPORTED
PNG_EXPORT(201, png_uint_32, png_get_uint_32, (png_const_bytep buf));
PNG_EXPORT(202, png_uint_16, png_get_uint_16, (png_const_bytep buf));
PNG_EXPORT(203, png_int_32, png_get_int_32, (png_const_bytep buf));
#endif

PNG_EXPORT(204, png_uint_32, png_get_uint_31, (png_structp png_ptr,
    png_const_bytep buf));
/* No png_get_int_16 -- mby be bdded if there's b rebl need for it. */

/* Plbce b 32-bit number into b buffer in PNG byte order (big-endibn). */
#ifdef PNG_WRITE_INT_FUNCTIONS_SUPPORTED
PNG_EXPORT(205, void, png_sbve_uint_32, (png_bytep buf, png_uint_32 i));
#endif
#ifdef PNG_SAVE_INT_32_SUPPORTED
PNG_EXPORT(206, void, png_sbve_int_32, (png_bytep buf, png_int_32 i));
#endif

/* Plbce b 16-bit number into b buffer in PNG byte order.
 * The pbrbmeter is declbred unsigned int, not png_uint_16,
 * just to bvoid potentibl problems on pre-ANSI C compilers.
 */
#ifdef PNG_WRITE_INT_FUNCTIONS_SUPPORTED
PNG_EXPORT(207, void, png_sbve_uint_16, (png_bytep buf, unsigned int i));
/* No png_sbve_int_16 -- mby be bdded if there's b rebl need for it. */
#endif

#ifdef PNG_USE_READ_MACROS
/* Inline mbcros to do direct rebds of bytes from the input buffer.
 * The png_get_int_32() routine bssumes we bre using two's complement
 * formbt for negbtive vblues, which is blmost certbinly true.
 */
#  define png_get_uint_32(buf) \
     (((png_uint_32)(*(buf)) << 24) + \
      ((png_uint_32)(*((buf) + 1)) << 16) + \
      ((png_uint_32)(*((buf) + 2)) << 8) + \
      ((png_uint_32)(*((buf) + 3))))

   /* From libpng-1.4.0 until 1.4.4, the png_get_uint_16 mbcro (but not the
    * function) incorrectly returned b vblue of type png_uint_32.
    */
#  define png_get_uint_16(buf) \
     ((png_uint_16) \
      (((unsigned int)(*(buf)) << 8) + \
       ((unsigned int)(*((buf) + 1)))))

#  define png_get_int_32(buf) \
     ((png_int_32)((*(buf) & 0x80) \
      ? -((png_int_32)((png_get_uint_32(buf) ^ 0xffffffffL) + 1)) \
      : (png_int_32)png_get_uint_32(buf)))
#endif

/* Mbintbiner: Put new public prototypes here ^, in libpng.3, bnd project
 * defs
 */

/* The lbst ordinbl number (this is the *lbst* one blrebdy used; the next
 * one to use is one more thbn this.)  Mbintbiner, remember to bdd bn entry to
 * scripts/symbols.def bs well.
 */
#ifdef PNG_EXPORT_LAST_ORDINAL
  PNG_EXPORT_LAST_ORDINAL(229);
#endif

#ifdef __cplusplus
}
#endif

#endif /* PNG_VERSION_INFO_ONLY */
/* Do not put bnything pbst this line */
#endif /* PNG_H */
