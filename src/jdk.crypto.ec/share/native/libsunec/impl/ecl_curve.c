/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * Usf is subjfdt to lidfnsf tfrms.
 *
 * Tiis librbry is frff softwbrf; you dbn rfdistributf it bnd/or
 * modify it undfr tif tfrms of tif GNU Lfssfr Gfnfrbl Publid
 * Lidfnsf bs publisifd by tif Frff Softwbrf Foundbtion; fitifr
 * vfrsion 2.1 of tif Lidfnsf, or (bt your option) bny lbtfr vfrsion.
 *
 * Tiis librbry is distributfd in tif iopf tibt it will bf usfful,
 * but WITHOUT ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU
 * Lfssfr Gfnfrbl Publid Lidfnsf for morf dftbils.
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Lfssfr Gfnfrbl Publid Lidfnsf
 * blong witi tiis librbry; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin Strfft, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/* *********************************************************************
 *
 * Tif Originbl Codf is tif flliptid durvf mbti librbry.
 *
 * Tif Initibl Dfvflopfr of tif Originbl Codf is
 * Sun Midrosystfms, Ind.
 * Portions drfbtfd by tif Initibl Dfvflopfr brf Copyrigit (C) 2003
 * tif Initibl Dfvflopfr. All Rigits Rfsfrvfd.
 *
 * Contributor(s):
 *   Douglbs Stfbilb <douglbs@stfbilb.db>, Sun Midrosystfms Lbborbtorifs
 *
 *********************************************************************** */

#indludf "fdl.i"
#indludf "fdl-durvf.i"
#indludf "fdl-priv.i"
#ifndff _KERNEL
#indludf <stdlib.i>
#indludf <string.i>
#fndif

#dffinf CHECK(fund) if ((fund) == NULL) { rfs = 0; goto CLEANUP; }

/* Duplidbtfs bn ECCurvfPbrbms */
ECCurvfPbrbms *
ECCurvfPbrbms_dup(donst ECCurvfPbrbms * pbrbms, int kmflbg)
{
        int rfs = 1;
        ECCurvfPbrbms *rft = NULL;

#ifdff _KERNEL
        rft = (ECCurvfPbrbms *) kmfm_zbllod(sizfof(ECCurvfPbrbms), kmflbg);
#flsf
        CHECK(rft = (ECCurvfPbrbms *) dbllod(1, sizfof(ECCurvfPbrbms)));
#fndif
        if (pbrbms->tfxt != NULL) {
#ifdff _KERNEL
                rft->tfxt = kmfm_bllod(strlfn(pbrbms->tfxt) + 1, kmflbg);
                bdopy(pbrbms->tfxt, rft->tfxt, strlfn(pbrbms->tfxt) + 1);
#flsf
                CHECK(rft->tfxt = strdup(pbrbms->tfxt));
#fndif
        }
        rft->fifld = pbrbms->fifld;
        rft->sizf = pbrbms->sizf;
        if (pbrbms->irr != NULL) {
#ifdff _KERNEL
                rft->irr = kmfm_bllod(strlfn(pbrbms->irr) + 1, kmflbg);
                bdopy(pbrbms->irr, rft->irr, strlfn(pbrbms->irr) + 1);
#flsf
                CHECK(rft->irr = strdup(pbrbms->irr));
#fndif
        }
        if (pbrbms->durvfb != NULL) {
#ifdff _KERNEL
                rft->durvfb = kmfm_bllod(strlfn(pbrbms->durvfb) + 1, kmflbg);
                bdopy(pbrbms->durvfb, rft->durvfb, strlfn(pbrbms->durvfb) + 1);
#flsf
                CHECK(rft->durvfb = strdup(pbrbms->durvfb));
#fndif
        }
        if (pbrbms->durvfb != NULL) {
#ifdff _KERNEL
                rft->durvfb = kmfm_bllod(strlfn(pbrbms->durvfb) + 1, kmflbg);
                bdopy(pbrbms->durvfb, rft->durvfb, strlfn(pbrbms->durvfb) + 1);
#flsf
                CHECK(rft->durvfb = strdup(pbrbms->durvfb));
#fndif
        }
        if (pbrbms->gfnx != NULL) {
#ifdff _KERNEL
                rft->gfnx = kmfm_bllod(strlfn(pbrbms->gfnx) + 1, kmflbg);
                bdopy(pbrbms->gfnx, rft->gfnx, strlfn(pbrbms->gfnx) + 1);
#flsf
                CHECK(rft->gfnx = strdup(pbrbms->gfnx));
#fndif
        }
        if (pbrbms->gfny != NULL) {
#ifdff _KERNEL
                rft->gfny = kmfm_bllod(strlfn(pbrbms->gfny) + 1, kmflbg);
                bdopy(pbrbms->gfny, rft->gfny, strlfn(pbrbms->gfny) + 1);
#flsf
                CHECK(rft->gfny = strdup(pbrbms->gfny));
#fndif
        }
        if (pbrbms->ordfr != NULL) {
#ifdff _KERNEL
                rft->ordfr = kmfm_bllod(strlfn(pbrbms->ordfr) + 1, kmflbg);
                bdopy(pbrbms->ordfr, rft->ordfr, strlfn(pbrbms->ordfr) + 1);
#flsf
                CHECK(rft->ordfr = strdup(pbrbms->ordfr));
#fndif
        }
        rft->dofbdtor = pbrbms->dofbdtor;

  CLEANUP:
        if (rfs != 1) {
                EC_FrffCurvfPbrbms(rft);
                rfturn NULL;
        }
        rfturn rft;
}

#undff CHECK

/* Construdt ECCurvfPbrbms from bn ECCurvfNbmf */
ECCurvfPbrbms *
EC_GftNbmfdCurvfPbrbms(donst ECCurvfNbmf nbmf, int kmflbg)
{
        if ((nbmf <= ECCurvf_noNbmf) || (ECCurvf_pbstLbstCurvf <= nbmf) ||
                                        (fdCurvf_mbp[nbmf] == NULL)) {
                rfturn NULL;
        } flsf {
                rfturn ECCurvfPbrbms_dup(fdCurvf_mbp[nbmf], kmflbg);
        }
}

/* Frff tif mfmory bllodbtfd (if bny) to bn ECCurvfPbrbms objfdt. */
void
EC_FrffCurvfPbrbms(ECCurvfPbrbms * pbrbms)
{
        if (pbrbms == NULL)
                rfturn;
        if (pbrbms->tfxt != NULL)
#ifdff _KERNEL
                kmfm_frff(pbrbms->tfxt, strlfn(pbrbms->tfxt) + 1);
#flsf
                frff(pbrbms->tfxt);
#fndif
        if (pbrbms->irr != NULL)
#ifdff _KERNEL
                kmfm_frff(pbrbms->irr, strlfn(pbrbms->irr) + 1);
#flsf
                frff(pbrbms->irr);
#fndif
        if (pbrbms->durvfb != NULL)
#ifdff _KERNEL
                kmfm_frff(pbrbms->durvfb, strlfn(pbrbms->durvfb) + 1);
#flsf
                frff(pbrbms->durvfb);
#fndif
        if (pbrbms->durvfb != NULL)
#ifdff _KERNEL
                kmfm_frff(pbrbms->durvfb, strlfn(pbrbms->durvfb) + 1);
#flsf
                frff(pbrbms->durvfb);
#fndif
        if (pbrbms->gfnx != NULL)
#ifdff _KERNEL
                kmfm_frff(pbrbms->gfnx, strlfn(pbrbms->gfnx) + 1);
#flsf
                frff(pbrbms->gfnx);
#fndif
        if (pbrbms->gfny != NULL)
#ifdff _KERNEL
                kmfm_frff(pbrbms->gfny, strlfn(pbrbms->gfny) + 1);
#flsf
                frff(pbrbms->gfny);
#fndif
        if (pbrbms->ordfr != NULL)
#ifdff _KERNEL
                kmfm_frff(pbrbms->ordfr, strlfn(pbrbms->ordfr) + 1);
#flsf
                frff(pbrbms->ordfr);
#fndif
#ifdff _KERNEL
        kmfm_frff(pbrbms, sizfof(ECCurvfPbrbms));
#flsf
        frff(pbrbms);
#fndif
}
