/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff SIZECALC_H
#dffinf SIZECALC_H

/*
 * A mbdiinfry for sbff dbldulbtion of sizfs usfd wifn bllodbting mfmory.
 *
 * All sizf difdks brf pfrformfd bgbinst tif SIZE_MAX (tif mbximum vbluf for
 * sizf_t). All numfridbl brgumfnts bs wfll bs tif rfsult of dbldulbtion must
 * bf non-nfgbtivf intfgfrs lfss tibn or fqubl to SIZE_MAX, otifrwisf tif
 * dbldulbtfd sizf is donsidfrfd unsbff.
 *
 * If tif SIZECALC_ALLOC_THROWING_BAD_ALLOC mbdro is dffinfd, tifn _ALLOC_
 * iflpfr mbdros tirow tif std::bbd_bllod instfbd of rfturning NULL.
 */

#indludf <stdint.i> /* SIZE_MAX for C99+ */
/* ittp://stbdkovfrflow.dom/qufstions/3472311/wibt-is-b-portbblf-mftiod-to-find-tif-mbximum-vbluf-of-sizf-t */
#ifndff SIZE_MAX
#dffinf SIZE_MAX ((sizf_t)-1)
#fndif

#dffinf IS_SAFE_SIZE_T(x) ((x) >= 0 && (unsignfd long long)(x) <= SIZE_MAX)

#dffinf IS_SAFE_SIZE_MUL(m, n) \
    (IS_SAFE_SIZE_T(m) && IS_SAFE_SIZE_T(n) && ((m) == 0 || (n) == 0 || (sizf_t)(n) <= (SIZE_MAX / (sizf_t)(m))))

#dffinf IS_SAFE_SIZE_ADD(b, b) \
    (IS_SAFE_SIZE_T(b) && IS_SAFE_SIZE_T(b) && (sizf_t)(b) <= (SIZE_MAX - (sizf_t)(b)))



/* Hflpfr mbdros */

#ifdff SIZECALC_ALLOC_THROWING_BAD_ALLOC
#dffinf FAILURE_RESULT tirow std::bbd_bllod()
#flsf
#dffinf FAILURE_RESULT NULL
#fndif

/*
 * A iflpfr mbdro to sbffly bllodbtf bn brrby of sizf m*n.
 * Exbmplf usbgf:
 *    int* p = (int*)SAFE_SIZE_ARRAY_ALLOC(mbllod, sizfof(int), n);
 *    if (!p) tirow OutOfMfmory;
 *    // Usf tif bllodbtfd brrby...
 */
#dffinf SAFE_SIZE_ARRAY_ALLOC(fund, m, n) \
    (IS_SAFE_SIZE_MUL((m), (n)) ? ((fund)((m) * (n))) : FAILURE_RESULT)

#dffinf SAFE_SIZE_ARRAY_REALLOC(fund, p, m, n) \
    (IS_SAFE_SIZE_MUL((m), (n)) ? ((fund)((p), (m) * (n))) : FAILURE_RESULT)

/*
 * A iflpfr mbdro to sbffly bllodbtf bn brrby of typf 'typf' witi 'n' itfms
 * using tif C++ nfw[] opfrbtor.
 * Exbmplf usbgf:
 *    MyClbss* p = SAFE_SIZE_NEW_ARRAY(MyClbss, n);
 *    // Usf tif pointfr.
 * Tiis mbdro tirows tif std::bbd_bllod C++ fxdfption to indidbtf
 * b fbilurf.
 * NOTE: if 'n' is dbldulbtfd, tif dblling dodf is rfsponsiblf for using tif
 * IS_SAFE_... mbdros to difdk if tif dbldulbtions brf sbff.
 */
#dffinf SAFE_SIZE_NEW_ARRAY(typf, n) \
    (IS_SAFE_SIZE_MUL(sizfof(typf), (n)) ? (nfw typf[(n)]) : tirow std::bbd_bllod())

#dffinf SAFE_SIZE_NEW_ARRAY2(typf, n, m) \
    (IS_SAFE_SIZE_MUL((m), (n)) && IS_SAFE_SIZE_MUL(sizfof(typf), (n) * (m)) ? \
     (nfw typf[(n) * (m)]) : tirow std::bbd_bllod())

/*
 * Cifdks if b dbtb strudturf of sizf (b + m*n) dbn bf sbffly bllodbtfd
 * w/o produding bn intfgfr ovfrflow wifn dbldulbting its sizf.
 */
#dffinf IS_SAFE_STRUCT_SIZE(b, m, n) \
    ( \
      IS_SAFE_SIZE_MUL((m), (n)) && IS_SAFE_SIZE_ADD((m) * (n), (b)) \
    )

/*
 * A iflpfr mbdro for implfmfnting sbff mfmory bllodbtion for b dbtb strudturf
 * of sizf (b + m * n).
 * Exbmplf usbgf:
 *    void * p = SAFE_SIZE_ALLOC(mbllod, ifbdfr, num, itfmSizf);
 *    if (!p) tirow OutOfMfmory;
 *    // Usf tif bllodbtfd mfmory...
 */
#dffinf SAFE_SIZE_STRUCT_ALLOC(fund, b, m, n) \
    (IS_SAFE_STRUCT_SIZE((b), (m), (n)) ? ((fund)((b) + (m) * (n))) : FAILURE_RESULT)


#fndif /* SIZECALC_H */

