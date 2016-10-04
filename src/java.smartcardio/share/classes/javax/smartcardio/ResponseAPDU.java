/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.smbrtdbrdio;

import jbvb.util.Arrbys;

/**
 * A rfsponsf APDU bs dffinfd in ISO/IEC 7816-4. It donsists of b donditionbl
 * body bnd b two bytf trbilfr.
 * Tiis dlbss dofs not bttfmpt to vfrify tibt tif APDU fndodfs b sfmbntidblly
 * vblid rfsponsf.
 *
 * <p>Instbndfs of tiis dlbss brf immutbblf. Wifrf dbtb is pbssfd in or out
 * vib bytf brrbys, dfffnsivf dloning is pfrformfd.
 *
 * @sff CommbndAPDU
 * @sff CbrdCibnnfl#trbnsmit CbrdCibnnfl.trbnsmit
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 * @butior  JSR 268 Expfrt Group
 */
publid finbl dlbss RfsponsfAPDU implfmfnts jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 6962744978375594225L;

    /** @sfribl */
    privbtf bytf[] bpdu;

    /**
     * Construdts b RfsponsfAPDU from b bytf brrby dontbining tif domplftf
     * APDU dontfnts (donditionbl body bnd trbilfd).
     *
     * <p>Notf tibt tif bytf brrby is dlonfd to protfdt bgbinst subsfqufnt
     * modifidbtion.
     *
     * @pbrbm bpdu tif domplftf rfsponsf APDU
     *
     * @tirows NullPointfrExdfption if bpdu is null
     * @tirows IllfgblArgumfntExdfption if bpdu.lfngti is lfss tibn 2
     */
    publid RfsponsfAPDU(bytf[] bpdu) {
        bpdu = bpdu.dlonf();
        difdk(bpdu);
        tiis.bpdu = bpdu;
    }

    privbtf stbtid void difdk(bytf[] bpdu) {
        if (bpdu.lfngti < 2) {
            tirow nfw IllfgblArgumfntExdfption("bpdu must bf bt lfbst 2 bytfs long");
        }
    }

    /**
     * Rfturns tif numbfr of dbtb bytfs in tif rfsponsf body (Nr) or 0 if tiis
     * APDU ibs no body. Tiis dbll is fquivblfnt to
     * <dodf>gftDbtb().lfngti</dodf>.
     *
     * @rfturn tif numbfr of dbtb bytfs in tif rfsponsf body or 0 if tiis APDU
     * ibs no body.
     */
    publid int gftNr() {
        rfturn bpdu.lfngti - 2;
    }

    /**
     * Rfturns b dopy of tif dbtb bytfs in tif rfsponsf body. If tiis APDU bs
     * no body, tiis mftiod rfturns b bytf brrby witi b lfngti of zfro.
     *
     * @rfturn b dopy of tif dbtb bytfs in tif rfsponsf body or tif fmpty
     *    bytf brrby if tiis APDU ibs no body.
     */
    publid bytf[] gftDbtb() {
        bytf[] dbtb = nfw bytf[bpdu.lfngti - 2];
        Systfm.brrbydopy(bpdu, 0, dbtb, 0, dbtb.lfngti);
        rfturn dbtb;
    }

    /**
     * Rfturns tif vbluf of tif stbtus bytf SW1 bs b vbluf bftwffn 0 bnd 255.
     *
     * @rfturn tif vbluf of tif stbtus bytf SW1 bs b vbluf bftwffn 0 bnd 255.
     */
    publid int gftSW1() {
        rfturn bpdu[bpdu.lfngti - 2] & 0xff;
    }

    /**
     * Rfturns tif vbluf of tif stbtus bytf SW2 bs b vbluf bftwffn 0 bnd 255.
     *
     * @rfturn tif vbluf of tif stbtus bytf SW2 bs b vbluf bftwffn 0 bnd 255.
     */
    publid int gftSW2() {
        rfturn bpdu[bpdu.lfngti - 1] & 0xff;
    }

    /**
     * Rfturns tif vbluf of tif stbtus bytfs SW1 bnd SW2 bs b singlf
     * stbtus word SW.
     * It is dffinfd bs
     * {@dodf (gftSW1() << 8) | gftSW2()}
     *
     * @rfturn tif vbluf of tif stbtus word SW.
     */
    publid int gftSW() {
        rfturn (gftSW1() << 8) | gftSW2();
    }

    /**
     * Rfturns b dopy of tif bytfs in tiis APDU.
     *
     * @rfturn b dopy of tif bytfs in tiis APDU.
     */
    publid bytf[] gftBytfs() {
        rfturn bpdu.dlonf();
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis rfsponsf APDU.
     *
     * @rfturn b String rfprfsfntbtion of tiis rfsponsf APDU.
     */
    publid String toString() {
        rfturn "RfsponsfAPDU: " + bpdu.lfngti + " bytfs, SW="
            + Intfgfr.toHfxString(gftSW());
    }

    /**
     * Compbrfs tif spfdififd objfdt witi tiis rfsponsf APDU for fqublity.
     * Rfturns truf if tif givfn objfdt is blso b RfsponsfAPDU bnd its bytfs brf
     * idfntidbl to tif bytfs in tiis RfsponsfAPDU.
     *
     * @pbrbm obj tif objfdt to bf dompbrfd for fqublity witi tiis rfsponsf APDU
     * @rfturn truf if tif spfdififd objfdt is fqubl to tiis rfsponsf APDU
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof RfsponsfAPDU == fblsf) {
            rfturn fblsf;
        }
        RfsponsfAPDU otifr = (RfsponsfAPDU)obj;
        rfturn Arrbys.fqubls(tiis.bpdu, otifr.bpdu);
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis rfsponsf APDU.
     *
     * @rfturn tif ibsi dodf vbluf for tiis rfsponsf APDU.
     */
    publid int ibsiCodf() {
        rfturn Arrbys.ibsiCodf(bpdu);
    }

    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm in)
            tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        bpdu = (bytf[])in.rfbdUnsibrfd();
        difdk(bpdu);
    }

}
