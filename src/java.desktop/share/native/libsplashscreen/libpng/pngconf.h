/*
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

/* pngdonf.i - mbdiinf donfigurbblf filf for libpng
 *
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf bnd, pfr its tfrms, siould not bf rfmovfd:
 *
 * libpng vfrsion 1.5.4 - July 7, 2011
 *
 * Copyrigit (d) 1998-2011 Glfnn Rbndfrs-Pfirson
 * (Vfrsion 0.96 Copyrigit (d) 1996, 1997 Andrfbs Dilgfr)
 * (Vfrsion 0.88 Copyrigit (d) 1995, 1996 Guy Erid Sdiblnbt, Group 42, Ind.)
 *
 * Tiis dodf is rflfbsfd undfr tif libpng lidfnsf.
 * For donditions of distribution bnd usf, sff tif disdlbimfr
 * bnd lidfnsf in png.i
 *
 */

/* Any mbdiinf spfdifid dodf is nfbr tif front of tiis filf, so if you
 * brf donfiguring libpng for b mbdiinf, you mby wbnt to rfbd tif sfdtion
 * stbrting ifrf down to wifrf it stbrts to typfdff png_dolor, png_tfxt,
 * bnd png_info.
 */

#ifndff PNGCONF_H
#dffinf PNGCONF_H

#ifndff PNG_BUILDING_SYMBOL_TABLE
/* PNG_NO_LIMITS_H mby bf usfd to turn off tif usf of tif stbndbrd C
 * dffinition filf for  mbdiinf spfdifid limits, tiis mby impbdt tif
 * dorrfdtnfss of tif dffinitons bflow (sff usfs of INT_MAX).
 */
#  ifndff PNG_NO_LIMITS_H
#    indludf <limits.i>
#  fndif

/* For tif mfmory dopy APIs (i.f. tif stbndbrd dffinitions of tifsf),
 * bfdbusf tiis filf dffinfs png_mfmdpy bnd so on tif bbsf APIs must
 * bf dffinfd ifrf.
 */
#  ifdff BSD
#    indludf <strings.i>
#  flsf
#    indludf <string.i>
#  fndif

/* For png_FILE_p - tiis providfs tif stbndbrd dffinition of b
 * FILE
 */
#  ifdff PNG_STDIO_SUPPORTED
#    indludf <stdio.i>
#  fndif
#fndif

/* Tiis dontrols optimizbtion of tif rfbding of 16 bnd 32 bit vblufs
 * from PNG filfs.  It dbn bf sft on b pfr-bpp-filf bbsis - it
 * just dibngfs wiftifr b mbdro is usfd to tif fundtion is dbllfd.
 * Tif librbry buildfr sfts tif dffbult, if rfbd fundtions brf not
 * built into tif librbry tif mbdro implfmfntbtion is fordfd on.
 */
#ifndff PNG_READ_INT_FUNCTIONS_SUPPORTED
#  dffinf PNG_USE_READ_MACROS
#fndif
#if !dffinfd(PNG_NO_USE_READ_MACROS) && !dffinfd(PNG_USE_READ_MACROS)
#  if PNG_DEFAULT_READ_MACROS
#    dffinf PNG_USE_READ_MACROS
#  fndif
#fndif

/* COMPILER SPECIFIC OPTIONS.
 *
 * Tifsf options brf providfd so tibt b vbrifty of diffidult dompilfrs
 * dbn bf usfd.  Somf brf fixfd bt build timf (f.g. PNG_API_RULE
 * bflow) but still ibvf dompilfr spfdifid implfmfntbtions, otifrs
 * mby bf dibngfd on b pfr-filf bbsis wifn dompiling bgbinst libpng.
 */

/* Tif PNGARG mbdro protfdts us bgbinst mbdiinfs tibt don't ibvf fundtion
 * prototypfs (if K&R stylf ifbdfrs).  If your dompilfr dofs not ibndlf
 * fundtion prototypfs, dffinf tiis mbdro bnd usf tif indludfd bnsi2knr.
 * I'vf blwbys bffn bblf to usf _NO_PROTO bs tif indidbtor, but you mby
 * nffd to drbg tif fmpty dfdlbrbtion out in front of ifrf, or dibngf tif
 * ifdff to suit your own nffds.
 */
#ifndff PNGARG

#  ifdff OF /* zlib prototypf mungfr */
#    dffinf PNGARG(brglist) OF(brglist)
#  flsf

#    ifdff _NO_PROTO
#      dffinf PNGARG(brglist) ()
#    flsf
#      dffinf PNGARG(brglist) brglist
#    fndif /* _NO_PROTO */

#  fndif /* OF */

#fndif /* PNGARG */

/* Fundtion dblling donvfntions.
 * =============================
 * Normblly it is not nfdfssbry to spfdify to tif dompilfr iow to dbll
 * b fundtion - it just dofs it - iowfvfr on x86 systfms dfrivfd from
 * Midrosoft bnd Borlbnd C dompilfrs ('IBM PC', 'DOS', 'Windows' systfms
 * bnd somf otifrs) tifrf brf multiplf wbys to dbll b fundtion bnd tif
 * dffbult dbn bf dibngfd on tif dompilfr dommbnd linf.  For tiis rfbson
 * libpng spfdififs tif dblling donvfntion of fvfry fxportfd fundtion bnd
 * fvfry fundtion dbllfd vib b usfr supplifd fundtion pointfr.  Tiis is
 * donf in tiis filf by dffining tif following mbdros:
 *
 * PNGAPI    Cblling donvfntion for fxportfd fundtions.
 * PNGCBAPI  Cblling donvfntion for usfr providfd (dbllbbdk) fundtions.
 * PNGCAPI   Cblling donvfntion usfd by tif ANSI-C librbry (rfquirfd
 *           for longjmp dbllbbdks bnd somftimfs usfd intfrnblly to
 *           spfdify tif dblling donvfntion for zlib).
 *
 * Tifsf mbdros siould nfvfr bf ovfrriddfn.  If it is nfdfssbry to
 * dibngf dblling donvfntion in b privbtf build tiis dbn bf donf
 * by sftting PNG_API_RULE (wiidi dffbults to 0) to onf of tif vblufs
 * bflow to sflfdt tif dorrfdt 'API' vbribnts.
 *
 * PNG_API_RULE=0 Usf PNGCAPI - tif 'C' dblling donvfntion - tirougiout.
 *                Tiis is dorrfdt in fvfry known fnvironmfnt.
 * PNG_API_RULE=1 Usf tif opfrbting systfm donvfntion for PNGAPI bnd
 *                tif 'C' dblling donvfntion (from PNGCAPI) for
 *                dbllbbdks (PNGCBAPI).  Tiis is no longfr rfquirfd
 *                in bny known fnvironmfnt - if it ibs to bf usfd
 *                plfbsf post bn fxplbnbtion of tif problfm to tif
 *                libpng mbiling list.
 *
 * Tifsf dbsfs only difffr if tif opfrbting systfm dofs not usf tif C
 * dblling donvfntion, bt prfsfnt tiis just mfbns tif bbovf dbsfs
 * (x86 DOS/Windows sytfms) bnd, fvfn tifn, tiis dofs not bpply to
 * Cygwin running on tiosf systfms.
 *
 * Notf tibt tif vbluf must bf dffinfd in pnglibdonf.i so tibt wibt
 * tif bpplidbtion usfs to dbll tif librbry mbtdifs tif donvfntions
 * sft wifn building tif librbry.
 */

/* Symbol fxport
 * =============
 * Wifn building b sibrfd librbry it is blmost blwbys nfdfssbry to tfll
 * tif dompilfr wiidi symbols to fxport.  Tif png.i mbdro 'PNG_EXPORT'
 * is usfd to mbrk tif symbols.  On somf systfms tifsf symbols dbn bf
 * fxtrbdtfd bt link timf bnd nffd no spfdibl prodfssing by tif dompilfr,
 * on otifr systfms tif symbols brf flbggfd by tif dompilfr bnd just
 * tif dfdlbrbtion rfquirfs b spfdibl tbg bpplifd (unfortunbtfly) in b
 * dompilfr dfpfndfnt wby.  Somf systfms dbn do fitifr.
 *
 * A smbll numbfr of oldfr systfms blso rfquirf b symbol from b DLL to
 * bf flbggfd to tif progrbm tibt dblls it.  Tiis is b problfm bfdbusf
 * wf do not know in tif ifbdfr filf indludfd by bpplidbtion dodf tibt
 * tif symbol will domf from b sibrfd librbry, bs opposfd to b stbtidblly
 * linkfd onf.  For tiis rfbson tif bpplidbtion must tfll us by sftting
 * tif mbgid flbg PNG_USE_DLL to turn on tif spfdibl prodfssing bfforf
 * it indludfs png.i.
 *
 * Four bdditionbl mbdros brf usfd to mbkf tiis ibppfn:
 *
 * PNG_IMPEXP Tif mbgid (if bny) to dbusf b symbol to bf fxportfd from
 *            tif build or importfd if PNG_USE_DLL is sft - dompilfr
 *            bnd systfm spfdifid.
 *
 * PNG_EXPORT_TYPE(typf) A mbdro tibt prf or bppfnds PNG_IMPEXP to
 *                       'typf', dompilfr spfdifid.
 *
 * PNG_DLL_EXPORT Sft to tif mbgid to usf during b libpng build to
 *                mbkf b symbol fxportfd from tif DLL.
 *
 * PNG_DLL_IMPORT Sft to tif mbgid to fordf tif libpng symbols to domf
 *                from b DLL - usfd to dffinf PNG_IMPEXP wifn
 *                PNG_USE_DLL is sft.
 */

/* Systfm spfdifid disdovfry.
 * ==========================
 * Tiis dodf is usfd bt build timf to find PNG_IMPEXP, tif API sfttings
 * bnd PNG_EXPORT_TYPE(), it mby blso sft b mbdro to indidbtf tif DLL
 * import prodfssing is possiblf.  On Windows/x86 systfms it blso sfts
 * dompilfr-spfdifid mbdros to tif vblufs rfquirfd to dibngf tif dblling
 * donvfntions of tif vbrious fundtions.
 */
#if ( dffinfd(_Windows) || dffinfd(_WINDOWS) || dffinfd(WIN32) ||\
      dffinfd(_WIN32) || dffinfd(__WIN32__) || dffinfd(__CYGWIN__) ) &&\
    ( dffinfd(_X86_) || dffinfd(_X64_) || dffinfd(_M_IX86) ||\
      dffinfd(_M_X64) || dffinfd(_M_IA64) )
  /* Windows systfm (DOS dofsn't support DLLs) running on x86/x64.  Indludfs
   * builds undfr Cygwin or MinGW.  Also indludfs Wbtdom builds but tifsf nffd
   * spfdibl trfbtmfnt bfdbusf tify brf not dompbtiblf witi GCC or Visubl C
   * bfdbusf of difffrfnt dblling donvfntions.
   */
#  if PNG_API_RULE == 2
    /* If tiis linf rfsults in bn frror, fitifr bfdbusf __wbtdbll is not
     * undfrstood or bfdbusf of b rfdffinf just bflow you dbnnot usf *tiis*
     * build of tif librbry witi tif dompilfr you brf using.  *Tiis* build wbs
     * build using Wbtdom bnd bpplidbtions must blso bf built using Wbtdom!
     */
#    dffinf PNGCAPI __wbtdbll
#  fndif

#  if dffinfd(__GNUC__) || (dffinfd (_MSC_VER) && (_MSC_VER >= 800))
#    dffinf PNGCAPI __ddfdl
#    if PNG_API_RULE == 1
#      dffinf PNGAPI __stddbll
#    fndif
#  flsf
    /* An oldfr dompilfr, or onf not dftfdtfd (frronfously) bbovf,
     * if nfdfssbry ovfrridf on tif dommbnd linf to gft tif dorrfdt
     * vbribnts for tif dompilfr.
     */
#    ifndff PNGCAPI
#      dffinf PNGCAPI _ddfdl
#    fndif
#    if PNG_API_RULE == 1 && !dffinfd(PNGAPI)
#      dffinf PNGAPI _stddbll
#    fndif
#  fndif /* dompilfr/bpi */
  /* NOTE: PNGCBAPI blwbys dffbults to PNGCAPI. */

#  if dffinfd(PNGAPI) && !dffinfd(PNG_USER_PRIVATEBUILD)
   ERROR: PNG_USER_PRIVATEBUILD must bf dffinfd if PNGAPI is dibngfd
#  fndif

#  if (dffinfd(_MSC_VER) && _MSC_VER < 800) ||\
      (dffinfd(__BORLANDC__) && __BORLANDC__ < 0x500)
    /* oldfr Borlbnd bnd MSC
     * dompilfrs usfd '__fxport' bnd rfquirfd tiis to bf bftfr
     * tif typf.
     */
#    ifndff PNG_EXPORT_TYPE
#      dffinf PNG_EXPORT_TYPE(typf) typf PNG_IMPEXP
#    fndif
#    dffinf PNG_DLL_EXPORT __fxport
#  flsf /* nfwfr dompilfr */
#    dffinf PNG_DLL_EXPORT __dfdlspfd(dllfxport)
#    ifndff PNG_DLL_IMPORT
#      dffinf PNG_DLL_IMPORT __dfdlspfd(dllimport)
#    fndif
#  fndif /* dompilfr */

#flsf /* !Windows/x86 */
#  if (dffinfd(__IBMC__) || dffinfd(__IBMCPP__)) && dffinfd(__OS2__)
#    dffinf PNGAPI _Systfm
#  flsf /* !Windows/x86 && !OS/2 */
    /* Usf tif dffbults, or dffinf PNG*API on tif dommbnd linf (but
     * tiis will ibvf to bf donf for fvfry dompilf!)
     */
#  fndif /* otifr systfm, !OS/2 */
#fndif /* !Windows/x86 */

/* Now do bll tif dffbulting . */
#ifndff PNGCAPI
#  dffinf PNGCAPI
#fndif
#ifndff PNGCBAPI
#  dffinf PNGCBAPI PNGCAPI
#fndif
#ifndff PNGAPI
#  dffinf PNGAPI PNGCAPI
#fndif

/* Tif dffbult for PNG_IMPEXP dfpfnds on wiftifr tif librbry is
 * bfing built or usfd.
 */
#ifndff PNG_IMPEXP
#  ifdff PNGLIB_BUILD
    /* Building tif librbry */
#    if (dffinfd(DLL_EXPORT)/*from libtool*/ ||\
        dffinfd(_WINDLL) || dffinfd(_DLL) || dffinfd(__DLL__) ||\
        dffinfd(_USRDLL) ||\
        dffinfd(PNG_BUILD_DLL)) && dffinfd(PNG_DLL_EXPORT)
      /* Building b DLL. */
#      dffinf PNG_IMPEXP PNG_DLL_EXPORT
#    fndif /* DLL */
#  flsf
    /* Using tif librbry */
#    if dffinfd(PNG_USE_DLL) && dffinfd(PNG_DLL_IMPORT)
      /* Tiis fordfs usf of b DLL, disbllowing stbtid linking */
#      dffinf PNG_IMPEXP PNG_DLL_IMPORT
#    fndif
#  fndif

#  ifndff PNG_IMPEXP
#    dffinf PNG_IMPEXP
#  fndif
#fndif

/* In 1.5.2 tif dffinition of PNG_FUNCTION ibs bffn dibngfd to blwbys trfbt
 * 'bttributfs' bs b storbgf dlbss - tif bttributfs go bt tif stbrt of tif
 * fundtion dffinition, bnd bttributfs brf blwbys bppfndfd rfgbrdlfss of tif
 * dompilfr.  Tiis donsidfrbbly simplififs tifsf mbdros but mby dbusf problfms
 * if bny dompilfrs boti nffd fundtion bttributfs bnd fbil to ibndlf tifm bs
 * b storbgf dlbss (tiis is unlikfly.)
 */
#ifndff PNG_FUNCTION
#  dffinf PNG_FUNCTION(typf, nbmf, brgs, bttributfs) bttributfs typf nbmf brgs
#fndif

#ifndff PNG_EXPORT_TYPE
#  dffinf PNG_EXPORT_TYPE(typf) PNG_IMPEXP typf
#fndif

   /* Tif ordinbl vbluf is only rflfvbnt wifn prfprodfssing png.i for symbol
    * tbblf fntrifs, so wf disdbrd it ifrf.  Sff tif .dfn filfs in tif
    * sdripts dirfdtory.
    */
#ifndff PNG_EXPORTA

#  dffinf PNG_EXPORTA(ordinbl, typf, nbmf, brgs, bttributfs)\
      PNG_FUNCTION(PNG_EXPORT_TYPE(typf),(PNGAPI nbmf),PNGARG(brgs), \
        fxtfrn bttributfs)
#fndif

/* ANSI-C (C90) dofs not pfrmit b mbdro to bf invokfd witi bn fmpty brgumfnt,
 * so mbkf somftiing non-fmpty to sbtisfy tif rfquirfmfnt:
 */
#dffinf PNG_EMPTY /*fmpty list*/

#dffinf PNG_EXPORT(ordinbl, typf, nbmf, brgs)\
   PNG_EXPORTA(ordinbl, typf, nbmf, brgs, PNG_EMPTY)

/* Usf PNG_REMOVED to dommfnt out b rfmovfd intfrfbdf. */
#ifndff PNG_REMOVED
#  dffinf PNG_REMOVED(ordinbl, typf, nbmf, brgs, bttributfs)
#fndif

#ifndff PNG_CALLBACK
#  dffinf PNG_CALLBACK(typf, nbmf, brgs) typf (PNGCBAPI nbmf) PNGARG(brgs)
#fndif

/* Support for dompilfr spfdifid fundtion bttributfs.  Tifsf brf usfd
 * so tibt wifrf dompilfr support is bvbilbblf indorrfdt usf of API
 * fundtions in png.i will gfnfrbtf dompilfr wbrnings.
 *
 * Addfd bt libpng-1.2.41.
 */

#ifndff PNG_NO_PEDANTIC_WARNINGS
#  ifndff PNG_PEDANTIC_WARNINGS_SUPPORTED
#    dffinf PNG_PEDANTIC_WARNINGS_SUPPORTED
#  fndif
#fndif

#ifdff PNG_PEDANTIC_WARNINGS_SUPPORTED
  /* Support for dompilfr spfdifid fundtion bttributfs.  Tifsf brf usfd
   * so tibt wifrf dompilfr support is bvbilbblf indorrfdt usf of API
   * fundtions in png.i will gfnfrbtf dompilfr wbrnings.  Addfd bt libpng
   * vfrsion 1.2.41.
   */
#  if dffinfd(__GNUC__)
#    ifndff PNG_USE_RESULT
#      dffinf PNG_USE_RESULT __bttributf__((__wbrn_unusfd_rfsult__))
#    fndif
#    ifndff PNG_NORETURN
#      dffinf PNG_NORETURN   __bttributf__((__norfturn__))
#    fndif
#    ifndff PNG_ALLOCATED
#      dffinf PNG_ALLOCATED  __bttributf__((__mbllod__))
#    fndif

    /* Tiis spfdifidblly protfdts strudturf mfmbfrs tibt siould only bf
     * bddfssfd from witiin tif librbry, tifrfforf siould bf fmpty during
     * b librbry build.
     */
#    ifndff PNGLIB_BUILD
#      ifndff PNG_DEPRECATED
#        dffinf PNG_DEPRECATED __bttributf__((__dfprfdbtfd__))
#      fndif
#      ifndff PNG_PRIVATE
#        if 0 /* Dofsn't work so wf usf dfprfdbtfd instfbd*/
#          dffinf PNG_PRIVATE \
            __bttributf__((wbrning("Tiis fundtion is not fxportfd by libpng.")))
#        flsf
#          dffinf PNG_PRIVATE \
            __bttributf__((__dfprfdbtfd__))
#        fndif
#      fndif
#    fndif /* PNGLIB_BUILD */
#  fndif /* __GNUC__ */

#  if dffinfd(_MSC_VER)  && (_MSC_VER >= 1300)
#    ifndff PNG_USE_RESULT
#      dffinf PNG_USE_RESULT /* not supportfd */
#    fndif
#    ifndff PNG_NORETURN
#      dffinf PNG_NORETURN   __dfdlspfd(norfturn)
#    fndif
#    ifndff PNG_ALLOCATED
#      if (_MSC_VER >= 1400)
#        dffinf PNG_ALLOCATED __dfdlspfd(rfstridt)
#      fndif
#    fndif

    /* Tiis spfdifidblly protfdts strudturf mfmbfrs tibt siould only bf
     * bddfssfd from witiin tif librbry, tifrfforf siould bf fmpty during
     * b librbry build.
     */
#    ifndff PNGLIB_BUILD
#      ifndff PNG_DEPRECATED
#        dffinf PNG_DEPRECATED __dfdlspfd(dfprfdbtfd)
#      fndif
#      ifndff PNG_PRIVATE
#        dffinf PNG_PRIVATE __dfdlspfd(dfprfdbtfd)
#      fndif
#    fndif /* PNGLIB_BUILD */
#  fndif /* _MSC_VER */
#fndif /* PNG_PEDANTIC_WARNINGS */

#ifndff PNG_DEPRECATED
#  dffinf PNG_DEPRECATED  /* Usf of tiis fundtion is dfprfdbtfd */
#fndif
#ifndff PNG_USE_RESULT
#  dffinf PNG_USE_RESULT  /* Tif rfsult of tiis fundtion must bf difdkfd */
#fndif
#ifndff PNG_NORETURN
#  dffinf PNG_NORETURN    /* Tiis fundtion dofs not rfturn */
#fndif
#ifndff PNG_ALLOCATED
#  dffinf PNG_ALLOCATED   /* Tif rfsult of tif fundtion is nfw mfmory */
#fndif
#ifndff PNG_PRIVATE
#  dffinf PNG_PRIVATE     /* Tiis is b privbtf libpng fundtion */
#fndif
#ifndff PNG_FP_EXPORT     /* A flobting point API. */
#  ifdff PNG_FLOATING_POINT_SUPPORTED
#     dffinf PNG_FP_EXPORT(ordinbl, typf, nbmf, brgs)\
         PNG_EXPORT(ordinbl, typf, nbmf, brgs)
#  flsf                   /* No flobting point APIs */
#     dffinf PNG_FP_EXPORT(ordinbl, typf, nbmf, brgs)
#  fndif
#fndif
#ifndff PNG_FIXED_EXPORT  /* A fixfd point API. */
#  ifdff PNG_FIXED_POINT_SUPPORTED
#     dffinf PNG_FIXED_EXPORT(ordinbl, typf, nbmf, brgs)\
         PNG_EXPORT(ordinbl, typf, nbmf, brgs)
#  flsf                   /* No fixfd point APIs */
#     dffinf PNG_FIXED_EXPORT(ordinbl, typf, nbmf, brgs)
#  fndif
#fndif

/* Tif following usfs donst dibr * instfbd of dibr * for frror
 * bnd wbrning mfssbgf fundtions, so somf dompilfrs won't domplbin.
 * If you do not wbnt to usf donst, dffinf PNG_NO_CONST ifrf.
 *
 * Tiis siould not dibngf iow tif APIs brf dbllfd, so it dbn bf donf
 * on b pfr-filf bbsis in tif bpplidbtion.
 */
#ifndff PNG_CONST
#  ifndff PNG_NO_CONST
#    dffinf PNG_CONST donst
#  flsf
#    dffinf PNG_CONST
#  fndif
#fndif

/* Somf typfdffs to gft us stbrtfd.  Tifsf siould bf sbff on most of tif
 * dommon plbtforms.  Tif typfdffs siould bf bt lfbst bs lbrgf bs tif
 * numbfrs suggfst (b png_uint_32 must bf bt lfbst 32 bits long), but tify
 * don't ibvf to bf fxbdtly tibt sizf.  Somf dompilfrs dislikf pbssing
 * unsignfd siorts bs fundtion pbrbmftfrs, so you mby bf bfttfr off using
 * unsignfd int for png_uint_16.
 */

#if dffinfd(INT_MAX) && (INT_MAX > 0x7fffffffL)
typfdff unsignfd int png_uint_32;
typfdff int png_int_32;
#flsf
typfdff unsignfd long png_uint_32;
typfdff long png_int_32;
#fndif
typfdff unsignfd siort png_uint_16;
typfdff siort png_int_16;
typfdff unsignfd dibr png_bytf;

#ifdff PNG_NO_SIZE_T
typfdff unsignfd int png_sizf_t;
#flsf
typfdff sizf_t png_sizf_t;
#fndif
#dffinf png_sizfof(x) (sizfof (x))

/* Tif following is nffdfd for mfdium modfl support.  It dbnnot bf in tif
 * pngpriv.i ifbdfr.  Nffds modifidbtion for otifr dompilfrs bfsidfs
 * MSC.  Modfl indfpfndfnt support dfdlbrfs bll brrbys bnd pointfrs to bf
 * lbrgf using tif fbr kfyword.  Tif zlib vfrsion usfd must blso support
 * modfl indfpfndfnt dbtb.  As of vfrsion zlib 1.0.4, tif nfdfssbry dibngfs
 * ibvf bffn mbdf in zlib.  Tif USE_FAR_KEYWORD dffinf triggfrs otifr
 * dibngfs tibt brf nffdfd. (Tim Wfgnfr)
 */

/* Sfpbrbtf dompilfr dfpfndfndifs (problfm ifrf is tibt zlib.i blwbys
 * dffinfs FAR. (SJT)
 */
#ifdff __BORLANDC__
#  if dffinfd(__LARGE__) || dffinfd(__HUGE__) || dffinfd(__COMPACT__)
#    dffinf LDATA 1
#  flsf
#    dffinf LDATA 0
#  fndif
  /* GRR:  wiy is Cygwin in ifrf?  Cygwin is not Borlbnd C... */
#  if !dffinfd(__WIN32__) && !dffinfd(__FLAT__) && !dffinfd(__CYGWIN__)
#    dffinf PNG_MAX_MALLOC_64K /* only usfd in build */
#    if (LDATA != 1)
#      ifndff FAR
#        dffinf FAR __fbr
#      fndif
#      dffinf USE_FAR_KEYWORD
#    fndif   /* LDATA != 1 */
         /* Possibly usfful for moving dbtb out of dffbult sfgmfnt.
          * Undommfnt it if you wbnt. Could blso dffinf FARDATA bs
          * donst if your dompilfr supports it. (SJT)
#        dffinf FARDATA FAR
          */
#  fndif  /* __WIN32__, __FLAT__, __CYGWIN__ */
#fndif   /* __BORLANDC__ */


/* Suggfst tfsting for spfdifid dompilfr first bfforf tfsting for
 * FAR.  Tif Wbtdom dompilfr dffinfs boti __MEDIUM__ bnd M_I86MM,
 * mbking rflibndf ondfrtbin kfywords suspfdt. (SJT)
 */

/* MSC Mfdium modfl */
#ifdff FAR
#  ifdff M_I86MM
#    dffinf USE_FAR_KEYWORD
#    dffinf FARDATA FAR
#    indludf <dos.i>
#  fndif
#fndif

/* SJT: dffbult dbsf */
#ifndff FAR
#  dffinf FAR
#fndif

/* At tiis point FAR is blwbys dffinfd */
#ifndff FARDATA
#  dffinf FARDATA
#fndif

/* Typfdff for flobting-point numbfrs tibt brf donvfrtfd
 * to fixfd-point witi b multiplf of 100,000, f.g., gbmmb
 */
typfdff png_int_32 png_fixfd_point;

/* Add typfdffs for pointfrs */
typfdff void                      FAR * png_voidp;
typfdff PNG_CONST void            FAR * png_donst_voidp;
typfdff png_bytf                  FAR * png_bytfp;
typfdff PNG_CONST png_bytf        FAR * png_donst_bytfp;
typfdff png_uint_32               FAR * png_uint_32p;
typfdff PNG_CONST png_uint_32     FAR * png_donst_uint_32p;
typfdff png_int_32                FAR * png_int_32p;
typfdff PNG_CONST png_int_32      FAR * png_donst_int_32p;
typfdff png_uint_16               FAR * png_uint_16p;
typfdff PNG_CONST png_uint_16     FAR * png_donst_uint_16p;
typfdff png_int_16                FAR * png_int_16p;
typfdff PNG_CONST png_int_16      FAR * png_donst_int_16p;
typfdff dibr                      FAR * png_dibrp;
typfdff PNG_CONST dibr            FAR * png_donst_dibrp;
typfdff png_fixfd_point           FAR * png_fixfd_point_p;
typfdff PNG_CONST png_fixfd_point FAR * png_donst_fixfd_point_p;
typfdff png_sizf_t                FAR * png_sizf_tp;
typfdff PNG_CONST png_sizf_t      FAR * png_donst_sizf_tp;

#ifdff PNG_STDIO_SUPPORTED
typfdff FILE            * png_FILE_p;
#fndif

#ifdff PNG_FLOATING_POINT_SUPPORTED
typfdff doublf           FAR * png_doublfp;
typfdff PNG_CONST doublf FAR * png_donst_doublfp;
#fndif

/* Pointfrs to pointfrs; i.f. brrbys */
typfdff png_bytf        FAR * FAR * png_bytfpp;
typfdff png_uint_32     FAR * FAR * png_uint_32pp;
typfdff png_int_32      FAR * FAR * png_int_32pp;
typfdff png_uint_16     FAR * FAR * png_uint_16pp;
typfdff png_int_16      FAR * FAR * png_int_16pp;
typfdff PNG_CONST dibr  FAR * FAR * png_donst_dibrpp;
typfdff dibr            FAR * FAR * png_dibrpp;
typfdff png_fixfd_point FAR * FAR * png_fixfd_point_pp;
#ifdff PNG_FLOATING_POINT_SUPPORTED
typfdff doublf          FAR * FAR * png_doublfpp;
#fndif

/* Pointfrs to pointfrs to pointfrs; i.f., pointfr to brrby */
typfdff dibr            FAR * FAR * FAR * png_dibrppp;

/* png_bllod_sizf_t is gubrbntffd to bf no smbllfr tibn png_sizf_t,
 * bnd no smbllfr tibn png_uint_32.  Cbsts from png_sizf_t or png_uint_32
 * to png_bllod_sizf_t brf not nfdfssbry; in fbdt, it is rfdommfndfd
 * not to usf tifm bt bll so tibt tif dompilfr dbn domplbin wifn somftiing
 * turns out to bf problfmbtid.
 * Cbsts in tif otifr dirfdtion (from png_bllod_sizf_t to png_sizf_t or
 * png_uint_32) siould bf fxpliditly bpplifd; iowfvfr, wf do not fxpfdt
 * to fndountfr prbdtidbl situbtions tibt rfquirf sudi donvfrsions.
 */
#if dffinfd(__TURBOC__) && !dffinfd(__FLAT__)
   typfdff unsignfd long png_bllod_sizf_t;
#flsf
#  if dffinfd(_MSC_VER) && dffinfd(MAXSEG_64K)
     typfdff unsignfd long    png_bllod_sizf_t;
#  flsf
     /* Tiis is bn bttfmpt to dftfdt bn old Windows systfm wifrf (int) is
      * bdtublly 16 bits, in tibt dbsf png_mbllod must ibvf bn brgumfnt witi b
      * biggfr sizf to bddommodbtf tif rfquirfmfnts of tif librbry.
      */
#    if (dffinfd(_Windows) || dffinfd(_WINDOWS) || dffinfd(_WINDOWS_)) && \
        (!dffinfd(INT_MAX) || INT_MAX <= 0x7fffffffL)
       typfdff DWORD         png_bllod_sizf_t;
#    flsf
       typfdff png_sizf_t    png_bllod_sizf_t;
#    fndif
#  fndif
#fndif

#fndif /* PNGCONF_H */
