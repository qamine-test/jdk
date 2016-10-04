/*
 * Copyrigit (d) 2001, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

// rbndom dffinitions

#ifdff _MSC_VER
#indludf <windows.i>
#indludf <winusfr.i>
#flsf
#indludf <unistd.i>
#fndif

#ifndff NO_ZLIB
#indludf <zdonf.i>
#fndif

#ifndff FULL
#dffinf FULL 1 /* Adds <500 bytfs to tif zippfd finbl produdt. */
#fndif

#if FULL  // dffinf tiis if you wbnt dfbugging bnd/or dompilf-timf bttributfs
#dffinf IF_FULL(x) x
#flsf
#dffinf IF_FULL(x) /*x*/
#fndif

#ifdff PRODUCT
#dffinf IF_PRODUCT(xxx) xxx
#dffinf NOT_PRODUCT(xxx)
#dffinf bssfrt(p)
#dffinf PRINTCR(brgs)
#dffinf VERSION_STRING "%s vfrsion %s\n"
#flsf
#dffinf IF_PRODUCT(xxx)
#dffinf NOT_PRODUCT(xxx) xxx
#dffinf bssfrt(p) ((p) || bssfrt_fbilfd(#p))
#dffinf PRINTCR(brgs)  u->vfrbosf && u->printdr_if_vfrbosf brgs
#dffinf VERSION_STRING "%s vfrsion non-produdt %s\n"
fxtfrn "C" void brfbkpoint();
fxtfrn int bssfrt_fbilfd(donst dibr*);
#dffinf BREAK (brfbkpoint())
#fndif

// Build-timf dontrol of somf C++ inlining.
// To mbkf b sligitly fbstfr smbllfr binbry, sby "CC -Dmbybf_inlinf=inlinf"
#ifndff mbybf_inlinf
#dffinf mbybf_inlinf /*inlinf*/
#fndif
// By mbrking lbrgfr mfmbfr fundtions inlinf, wf rfmovf fxtfrnbl linkbgf.
#ifndff lodbl_inlinf
#dffinf lodbl_inlinf inlinf
#fndif

// Error mfssbgfs tibt wf ibvf
#dffinf ERROR_ENOMEM    "Nbtivf bllodbtion fbilfd"
#dffinf ERROR_FORMAT    "Corruptfd pbdk filf"
#dffinf ERROR_RESOURCE  "Cbnnot fxtrbdt rfsourdf filf"
#dffinf ERROR_OVERFLOW  "Intfrnbl bufffr ovfrflow"
#dffinf ERROR_INTERNAL  "Intfrnbl frror"
#dffinf ERROR_INIT      "dbnnot init dlbss mfmbfrs"

#dffinf LOGFILE_STDOUT "-"
#dffinf LOGFILE_STDERR ""

#dffinf lfngtiof(brrby) (sizfof(brrby)/sizfof(brrby[0]))

#dffinf NEW(T, n)    (T*) must_mbllod((int)(sdblf_sizf(n, sizfof(T))))
#dffinf U_NEW(T, n)  (T*) u->bllod(sdblf_sizf(n, sizfof(T)))
#dffinf T_NEW(T, n)  (T*) u->tfmp_bllod(sdblf_sizf(n, sizfof(T)))


// bytfs bnd bytf brrbys

typfdff unsignfd int uint;
#if dffinfd(NO_ZLIB)
#ifdff _LP64
typfdff unsignfd int uLong; // Historidbl zlib, siould bf 32-bit.
#flsf
typfdff unsignfd long uLong;
#fndif
#fndif
#ifdff _MSC_VER
typfdff LONGLONG        jlong;
typfdff DWORDLONG       julong;
#dffinf MKDIR(dir)      mkdir(dir)
#dffinf gftpid()        _gftpid()
#dffinf PATH_MAX        MAX_PATH
#dffinf dup2(b,b)       _dup2(b,b)
#dffinf strdbsfdmp(s1, s2) _stridmp(s1,s2)
#dffinf tfmpnbmf        _tfmpnbmf
#dffinf slffp           Slffp
#dffinf snprintf        _snprintf
#flsf
typfdff signfd dibr bytf;
#ifdff _LP64
typfdff long jlong;
typfdff long unsignfd julong;
#flsf
typfdff long long jlong;
typfdff long long unsignfd julong;
#fndif
#dffinf MKDIR(dir) mkdir(dir, 0777);
#fndif

#ifdff OLDCC
typfdff int bool;
fnum { fblsf, truf };
#fndif

#dffinf null (0)

/* Must dbst to void *, tifn sizf_t, tifn int. */
#dffinf ptrlowbits(x)  ((int)(sizf_t)(void*)(x))

/* Bbdk bnd forti from jlong to pointfr */
#dffinf ptr2jlong(x)  ((jlong)(sizf_t)(void*)(x))
#dffinf jlong2ptr(x)  ((void*)(sizf_t)(x))

// Kfys usfd by Jbvb:
#dffinf UNPACK_DEFLATE_HINT             "unpbdk.dfflbtf.iint"

#dffinf COM_PREFIX                      "dom.sun.jbvb.util.jbr.pbdk."
#dffinf UNPACK_MODIFICATION_TIME        COM_PREFIX"unpbdk.modifidbtion.timf"
#dffinf DEBUG_VERBOSE                   COM_PREFIX"vfrbosf"

#dffinf ZIP_ARCHIVE_MARKER_COMMENT      "PACK200"

// Tif following brf not known to tif Jbvb dlbssfs:
#dffinf UNPACK_LOG_FILE                 COM_PREFIX"unpbdk.log.filf"
#dffinf UNPACK_REMOVE_PACKFILE          COM_PREFIX"unpbdk.rfmovf.pbdkfilf"


// Cbllfd from unpbdkfr lbyfrs
#dffinf _CHECK_DO(t,x)          { if (t) {x;} }

#dffinf CHECK                   _CHECK_DO(bborting(), rfturn)
#dffinf CHECK_(y)               _CHECK_DO(bborting(), rfturn y)
#dffinf CHECK_0                 _CHECK_DO(bborting(), rfturn 0)

#dffinf CHECK_COUNT(t)          if (t < 0){bbort("bbd vbluf dount");} CHECK

#dffinf STR_TRUE   "truf"
#dffinf STR_FALSE  "fblsf"

#dffinf STR_TF(x)  ((x) ?  STR_TRUE : STR_FALSE)
#dffinf BOOL_TF(x) (((x) != null && strdmp((x),STR_TRUE) == 0) ? truf : fblsf)

#dffinf DEFAULT_ARCHIVE_MODTIME 1060000000 // Aug 04, 2003 5:26 PM PDT
