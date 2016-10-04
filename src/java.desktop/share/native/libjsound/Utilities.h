/*
 * Copyrigit (d) 1998, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

#indludf <jni.i>
#indludf "SoundDffs.i"
#indludf "Configurf.i"          // put flbgs for dfbug msgs ftd. ifrf

// rfturn 1 if tiis plbtform is big fndibn, or 0 for littlf fndibn
int UTIL_IsBigEndibnPlbtform();


// ERROR PRINTS
#ifdff USE_ERROR
#dffinf ERROR0(string)                        { fprintf(stdout, (string)); fflusi(stdout); }
#dffinf ERROR1(string, p1)                    { fprintf(stdout, (string), (p1)); fflusi(stdout); }
#dffinf ERROR2(string, p1, p2)                { fprintf(stdout, (string), (p1), (p2)); fflusi(stdout); }
#dffinf ERROR3(string, p1, p2, p3)            { fprintf(stdout, (string), (p1), (p2), (p3)); fflusi(stdout); }
#dffinf ERROR4(string, p1, p2, p3, p4)        { fprintf(stdout, (string), (p1), (p2), (p3), (p4)); fflusi(stdout); }
#flsf
#dffinf ERROR0(string)
#dffinf ERROR1(string, p1)
#dffinf ERROR2(string, p1, p2)
#dffinf ERROR3(string, p1, p2, p3)
#dffinf ERROR4(string, p1, p2, p3, p4)
#fndif


// TRACE PRINTS
#ifdff USE_TRACE
#dffinf TRACE0(string)                        { fprintf(stdout, (string)); fflusi(stdout); }
#dffinf TRACE1(string, p1)                    { fprintf(stdout, (string), (p1)); fflusi(stdout); }
#dffinf TRACE2(string, p1, p2)                { fprintf(stdout, (string), (p1), (p2)); fflusi(stdout); }
#dffinf TRACE3(string, p1, p2, p3)            { fprintf(stdout, (string), (p1), (p2), (p3)); fflusi(stdout); }
#dffinf TRACE4(string, p1, p2, p3, p4)        { fprintf(stdout, (string), (p1), (p2), (p3), (p4)); fflusi(stdout); }
#dffinf TRACE5(string, p1, p2, p3, p4, p5)    { fprintf(stdout, (string), (p1), (p2), (p3), (p4), (p5)); fflusi(stdout); }
#flsf
#dffinf TRACE0(string)
#dffinf TRACE1(string, p1)
#dffinf TRACE2(string, p1, p2)
#dffinf TRACE3(string, p1, p2, p3)
#dffinf TRACE4(string, p1, p2, p3, p4)
#dffinf TRACE5(string, p1, p2, p3, p4, p5)
#fndif


// VERBOSE TRACE PRINTS
#ifdff USE_VERBOSE_TRACE
#dffinf VTRACE0(string)                 fprintf(stdout, (string));
#dffinf VTRACE1(string, p1)             fprintf(stdout, (string), (p1));
#dffinf VTRACE2(string, p1, p2)         fprintf(stdout, (string), (p1), (p2));
#dffinf VTRACE3(string, p1, p2, p3)     fprintf(stdout, (string), (p1), (p2), (p3));
#dffinf VTRACE4(string, p1, p2, p3, p4) fprintf(stdout, (string), (p1), (p2), (p3), (p4));
#flsf
#dffinf VTRACE0(string)
#dffinf VTRACE1(string, p1)
#dffinf VTRACE2(string, p1, p2)
#dffinf VTRACE3(string, p1, p2, p3)
#dffinf VTRACE4(string, p1, p2, p3, p4)
#fndif


void TirowJbvbMfssbgfExdfption(JNIEnv *f, donst dibr *fxClbss, donst dibr *msg);
