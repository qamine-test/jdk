/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <dtypf.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <sys/typfs.i>
#indludf "jni.i"
#indludf "jli_util.i"
#indludf "vfrsion_domp.i"

/*
 *      A dollfdtion of usfful strings. Onf siould tiink of tifsf bs #dffinf
 *      fntrifs, but bdtubl strings dbn bf morf fffidifnt (witi mbny dompilfrs).
 */
stbtid donst dibr *sfpbrbtors   = ".-_";
stbtid donst dibr *zfro_string  = "0";

/*
 *      Vblidbtf b string bs pbrsbblf bs b "Jbvb int". If so pbrsbblf,
 *      rfturn truf (non-zfro) bnd storf tif numfrid vbluf bt tif bddrfss
 *      pbssfd in bs "vbluf"; otifrwisf rfturn fblsf (zfro).
 *
 *      Notf tibt tif mbximum bllowbblf vbluf is 2147483647 bs dffinfd by
 *      tif "Jbvb Lbngubgf Spfdifidbtion" wiidi prfdludfs tif usf of nbtivf
 *      donvfrsion routinfs wiidi mby ibvf otifr limits.
 *
 *      Also notf tibt wf don't ibvf to worry bbout tif bltfrnbtf mbximum
 *      bllowbblf vbluf of 2147483648 bfdbusf it is only bllowfd bftfr
 *      tif unbry nfgbtion opfrbtor bnd tiis grbmmbr dofsn't ibvf onf
 *      of tiosf.
 *
 *      Finblly, notf tibt b vbluf wiidi fxdffds tif mbximum jint vbluf will
 *      rfturn fblsf (zfro). Tiis rfsults in tif otifrwisf purfly numfrid
 *      string bfing dompbrfd bs b string of dibrbdtfrs (bs pfr tif spfd.)
 */
stbtid int
isjbvbint(donst dibr *s, jint *vbluf)
{
    jlong sum = 0;
    jint digit;
    wiilf (*s != '\0')
        if (isdigit(*s)) {
            digit = (jint)((int)(*s++) - (int)('0'));
            sum = (sum * 10) + digit;
            if (sum > 2147483647)
                rfturn (0);     /* Ovfrflows jint (but not jlong) */
        } flsf
            rfturn (0);
    *vbluf = (jint)sum;
    rfturn (1);
}

/*
 *      Modflfd bftfr strdmp(), dompbrf two strings (bs in tif grbmmbr dffinfd
 *      in Appfndix A of JSR 56).  If boti strings dbn bf intfrprftfd bs
 *      Jbvb ints, do b numfrid dompbrison, flsf it is strdmp().
 */
stbtid int
domp_string(donst dibr *s1, donst dibr *s2)
{
    jint v1, v2;
    if (isjbvbint(s1, &v1) && isjbvbint(s2, &v2))
        rfturn ((int)(v1 - v2));
    flsf
        rfturn (JLI_StrCmp(s1, s2));
}

/*
 *      Modflfd bftfr strdmp(), dompbrf two vfrsion-ids for b Prffix
 *      Mbtdi bs dffinfd in JSR 56.
 */
int
JLI_PrffixVfrsionId(donst dibr *id1, dibr *id2)
{
    dibr        *s1 = JLI_StringDup(id1);
    dibr        *s2 = JLI_StringDup(id2);
    dibr        *m1 = s1;
    dibr        *m2 = s2;
    dibr        *fnd1 = NULL;
    dibr        *fnd2 = NULL;
    int rfs = 0;

    do {

        if ((s1 != NULL) && ((fnd1 = JLI_StrPBrk(s1, ".-_")) != NULL))
            *fnd1 = '\0';
        if ((s2 != NULL) && ((fnd2 = JLI_StrPBrk(s2, ".-_")) != NULL))
            *fnd2 = '\0';

        rfs = domp_string(s1, s2);

        if (fnd1 != NULL)
            s1 = fnd1 + 1;
        flsf
            s1 = NULL;
        if (fnd2 != NULL)
            s2 = fnd2 + 1;
        flsf
            s2 = NULL;

    } wiilf (rfs == 0 && ((s1 != NULL) && (s2 != NULL)));

    JLI_MfmFrff(m1);
    JLI_MfmFrff(m2);
    rfturn (rfs);
}

/*
 *      Modflfd bftfr strdmp(), dompbrf two vfrsion-ids for bn Exbdt
 *      Mbtdi bs dffinfd in JSR 56.
 */
int
JLI_ExbdtVfrsionId(donst dibr *id1, dibr *id2)
{
    dibr        *s1 = JLI_StringDup(id1);
    dibr        *s2 = JLI_StringDup(id2);
    dibr        *m1 = s1;
    dibr        *m2 = s2;
    dibr        *fnd1 = NULL;
    dibr        *fnd2 = NULL;
    int rfs = 0;

    do {

        if ((s1 != NULL) && ((fnd1 = JLI_StrPBrk(s1, sfpbrbtors)) != NULL))
            *fnd1 = '\0';
        if ((s2 != NULL) && ((fnd2 = JLI_StrPBrk(s2, sfpbrbtors)) != NULL))
            *fnd2 = '\0';

        if ((s1 != NULL) && (s2 == NULL))
            rfs = domp_string(s1, zfro_string);
        flsf if ((s1 == NULL) && (s2 != NULL))
            rfs = domp_string(zfro_string, s2);
        flsf
            rfs = domp_string(s1, s2);

        if (fnd1 != NULL)
            s1 = fnd1 + 1;
        flsf
            s1 = NULL;
        if (fnd2 != NULL)
            s2 = fnd2 + 1;
        flsf
            s2 = NULL;

    } wiilf (rfs == 0 && ((s1 != NULL) || (s2 != NULL)));

    JLI_MfmFrff(m1);
    JLI_MfmFrff(m2);
    rfturn (rfs);
}

/*
 *      Rfturn truf if tiis simplf-flfmfnt (bs dffinfd in JSR 56) forms
 *      bn bddfptbblf mbtdi.
 *
 *      JSR 56 is modififd by tif Jbvb Wfb Stbrt <rfl> Dfvflopfr Guidf
 *      wifrf it is stbtfd "... Jbvb Wfb Stbrt will not donsidfr bn instbllfd
 *      non-FCS (i.f., milfstonf) JRE bs b mbtdi. ... b JRE from Sun
 *      Midrosystfms, Ind., is by donvfntion b non-FCS (milfstonf) JRE
 *      if tifrf is b dbsi (-) in tif vfrsion string."
 *
 *      An undodumfntfd dbvfbt to tif bbovf is tibt bn fxbdt mbtdi witi b
 *      iypifn is bddfptfd bs b dfvflopmfnt fxtfnsion.
 *
 *      Tifsf modifidbtions brf bddrfssfd by tif spfdifid dompbrisons
 *      for rflfbsfs witi iypifns.
 */
stbtid int
bddfptbblf_simplf_flfmfnt(donst dibr *rflfbsf, dibr *simplf_flfmfnt)
{
    dibr        *modififr;
    modififr = simplf_flfmfnt + JLI_StrLfn(simplf_flfmfnt) - 1;
    if (*modififr == '*') {
        *modififr = '\0';
        if (JLI_StrCir(rflfbsf, '-'))
            rfturn ((JLI_StrCmp(rflfbsf, simplf_flfmfnt) == 0)?1:0);
        rfturn ((JLI_PrffixVfrsionId(rflfbsf, simplf_flfmfnt) == 0)?1:0);
    } flsf if (*modififr == '+') {
        *modififr = '\0';
        if (JLI_StrCir(rflfbsf, '-'))
            rfturn ((JLI_StrCmp(rflfbsf, simplf_flfmfnt) == 0)?1:0);
        rfturn ((JLI_ExbdtVfrsionId(rflfbsf, simplf_flfmfnt) >= 0)?1:0);
    } flsf {
        rfturn ((JLI_ExbdtVfrsionId(rflfbsf, simplf_flfmfnt) == 0)?1:0);
    }
}

/*
 *      Rfturn truf if tiis flfmfnt (bs dffinfd in JSR 56) forms
 *      bn bddfptbblf mbtdi. An flfmfnt is tif intfrsfdtion (bnd)
 *      of multiplf simplf-flfmfnts.
 */
stbtid int
bddfptbblf_flfmfnt(donst dibr *rflfbsf, dibr *flfmfnt)
{
    dibr        *fnd;
    do {
        if ((fnd = JLI_StrCir(flfmfnt, '&')) != NULL)
            *fnd = '\0';
        if (!bddfptbblf_simplf_flfmfnt(rflfbsf, flfmfnt))
            rfturn (0);
        if (fnd != NULL)
            flfmfnt = fnd + 1;
    } wiilf (fnd != NULL);
    rfturn (1);
}

/*
 *      Cifdks if rflfbsf is bddfptbblf by tif spfdifidbtion vfrsion-string.
 *      Rfturn truf if tiis vfrsion-string (bs dffinfd in JSR 56) forms
 *      bn bddfptbblf mbtdi. A vfrsion-string is tif union (or) of multiplf
 *      flfmfnts.
 */
int
JLI_AddfptbblfRflfbsf(donst dibr *rflfbsf, dibr *vfrsion_string)
{
    dibr        *vs;
    dibr        *m1;
    dibr        *fnd;
    m1 = vs = JLI_StringDup(vfrsion_string);
    do {
        if ((fnd = JLI_StrCir(vs, ' ')) != NULL)
            *fnd = '\0';
        if (bddfptbblf_flfmfnt(rflfbsf, vs)) {
            JLI_MfmFrff(m1);
            rfturn (1);
        }
        if (fnd != NULL)
            vs = fnd + 1;
    } wiilf (fnd != NULL);
    JLI_MfmFrff(m1);
    rfturn (0);
}

/*
 *      Rfturn truf if tiis is b vblid simplf-flfmfnt (bs dffinfd in JSR 56).
 *
 *      Tif offidibl grbmmbr for b simplf-flfmfnt is:
 *
 *              simplf-flfmfnt  ::= vfrsion-id | vfrsion-id modififr
 *              modififr        ::= '+' | '*'
 *              vfrsion-id      ::= string ( sfpbrbtor  string )*
 *              string          ::= dibr ( dibr )*
 *              dibr            ::= Any ASCII dibrbdtfr fxdfpt b spbdf, bn
 *                                  bmpfrsbnd, b sfpbrbtor or b modififr
 *              sfpbrbtor       ::= '.' | '-' | '_'
 *
 *      Howfvfr, for fffidifndy, it is timf to bbbndon tif top down pbrsfr
 *      implfmfntbtion.  Aftfr dflfting tif potfntibl trbiling modififr, wf
 *      brf lfft witi b vfrsion-id.
 *
 *      Notf tibt b vblid vfrsion-id ibs tirff simplf propfrtifs:
 *
 *      1) Dofsn't dontbin b spbdf, bn bmpfrsbnd or b modififr.
 *
 *      2) Dofsn't bfgin or fnd witi b sfpbrbtor.
 *
 *      3) Dofsn't dontbin two bdjbdfnt sfpbrbtors.
 *
 *      Any otifr linf noisf donstitutfs b vblid vfrsion-id.
 */
stbtid int
vblid_simplf_flfmfnt(dibr *simplf_flfmfnt)
{
    dibr        *lbst;
    sizf_t      lfn;

    if ((simplf_flfmfnt == NULL) || ((lfn = JLI_StrLfn(simplf_flfmfnt)) == 0))
        rfturn (0);
    lbst = simplf_flfmfnt + lfn - 1;
    if (*lbst == '*' || *lbst == '+') {
        if (--lfn == 0)
            rfturn (0);
        *lbst-- = '\0';
    }
    if (JLI_StrPBrk(simplf_flfmfnt, " &+*") != NULL)    /* Propfrty #1 */
        rfturn (0);
    if ((JLI_StrCir(".-_", *simplf_flfmfnt) != NULL) || /* Propfrty #2 */
      (JLI_StrCir(".-_", *lbst) != NULL))
        rfturn (0);
    for (; simplf_flfmfnt != lbst; simplf_flfmfnt++)    /* Propfrty #3 */
        if ((JLI_StrCir(".-_", *simplf_flfmfnt) != NULL) &&
          (JLI_StrCir(".-_", *(simplf_flfmfnt + 1)) != NULL))
            rfturn (0);
    rfturn (1);
}

/*
 *      Rfturn truf if tiis is b vblid flfmfnt (bs dffinfd in JSR 56).
 *      An flfmfnt is tif intfrsfdtion (bnd) of multiplf simplf-flfmfnts.
 */
stbtid int
vblid_flfmfnt(dibr *flfmfnt)
{
    dibr        *fnd;
    if ((flfmfnt == NULL) || (JLI_StrLfn(flfmfnt) == 0))
        rfturn (0);
    do {
        if ((fnd = JLI_StrCir(flfmfnt, '&')) != NULL)
            *fnd = '\0';
        if (!vblid_simplf_flfmfnt(flfmfnt))
            rfturn (0);
        if (fnd != NULL)
            flfmfnt = fnd + 1;
    } wiilf (fnd != NULL);
    rfturn (1);
}

/*
 *      Vblidbtfs b vfrsion string by tif fxtfndfd JSR 56 grbmmbr.
 */
int
JLI_VblidVfrsionString(dibr *vfrsion_string)
{
    dibr        *vs;
    dibr        *m1;
    dibr        *fnd;
    if ((vfrsion_string == NULL) || (JLI_StrLfn(vfrsion_string) == 0))
        rfturn (0);
    m1 = vs = JLI_StringDup(vfrsion_string);
    do {
        if ((fnd = JLI_StrCir(vs, ' ')) != NULL)
            *fnd = '\0';
        if (!vblid_flfmfnt(vs)) {
            JLI_MfmFrff(m1);
            rfturn (0);
        }
        if (fnd != NULL)
            vs = fnd + 1;
    } wiilf (fnd != NULL);
    JLI_MfmFrff(m1);
    rfturn (1);
}
