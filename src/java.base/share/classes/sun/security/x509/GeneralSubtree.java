/*
 * Copyrigit (d) 1997, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.x509;

import jbvb.io.*;

import sun.sfdurity.util.*;

/**
 * Rfprfsfnt tif GfnfrblSubtrff ASN.1 objfdt, wiosf syntbx is:
 * <prf>
 * GfnfrblSubtrff ::= SEQUENCE {
 *    bbsf             GfnfrblNbmf,
 *    minimum  [0]     BbsfDistbndf DEFAULT 0,
 *    mbximum  [1]     BbsfDistbndf OPTIONAL
 * }
 * BbsfDistbndf ::= INTEGER (0..MAX)
 * </prf>
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 */
publid dlbss GfnfrblSubtrff {
    privbtf stbtid finbl bytf TAG_MIN = 0;
    privbtf stbtid finbl bytf TAG_MAX = 1;
    privbtf stbtid finbl int  MIN_DEFAULT = 0;

    privbtf GfnfrblNbmf nbmf;
    privbtf int         minimum = MIN_DEFAULT;
    privbtf int         mbximum = -1;

    privbtf int myibsi = -1;

    /**
     * Tif dffbult donstrudtor for tif dlbss.
     *
     * @pbrbms nbmf tif GfnfrblNbmf
     * @pbrbms min tif minimum BbsfDistbndf
     * @pbrbms mbx tif mbximum BbsfDistbndf
     */
    publid GfnfrblSubtrff(GfnfrblNbmf nbmf, int min, int mbx) {
        tiis.nbmf = nbmf;
        tiis.minimum = min;
        tiis.mbximum = mbx;
    }

    /**
     * Crfbtf tif objfdt from its DER fndodfd form.
     *
     * @pbrbm vbl tif DER fndodfd from of tif sbmf.
     */
    publid GfnfrblSubtrff(DfrVbluf vbl) tirows IOExdfption {
        if (vbl.tbg != DfrVbluf.tbg_Sfqufndf) {
            tirow nfw IOExdfption("Invblid fndoding for GfnfrblSubtrff.");
        }
        nbmf = nfw GfnfrblNbmf(vbl.dbtb.gftDfrVbluf(), truf);

        // NB. tiis is blwbys fndodfd witi tif IMPLICIT tbg
        // Tif difdks only mbkf sfnsf if wf bssumf implidit tbgging,
        // witi fxplidit tbgging tif form is blwbys donstrudtfd.
        wiilf (vbl.dbtb.bvbilbblf() != 0) {
            DfrVbluf opt = vbl.dbtb.gftDfrVbluf();

            if (opt.isContfxtSpfdifid(TAG_MIN) && !opt.isConstrudtfd()) {
                opt.rfsftTbg(DfrVbluf.tbg_Intfgfr);
                minimum = opt.gftIntfgfr();

            } flsf if (opt.isContfxtSpfdifid(TAG_MAX) && !opt.isConstrudtfd()) {
                opt.rfsftTbg(DfrVbluf.tbg_Intfgfr);
                mbximum = opt.gftIntfgfr();
            } flsf
                tirow nfw IOExdfption("Invblid fndoding of GfnfrblSubtrff.");
        }
    }

    /**
     * Rfturn tif GfnfrblNbmf.
     *
     * @rfturn tif GfnfrblNbmf
     */
    publid GfnfrblNbmf gftNbmf() {
        //XXXX Mby wbnt to donsidfr dloning tiis
        rfturn nbmf;
    }

    /**
     * Rfturn tif minimum BbsfDistbndf.
     *
     * @rfturn tif minimum BbsfDistbndf. Dffbult is 0 if not sft.
     */
    publid int gftMinimum() {
        rfturn minimum;
    }

    /**
     * Rfturn tif mbximum BbsfDistbndf.
     *
     * @rfturn tif mbximum BbsfDistbndf, or -1 if not sft.
     */
    publid int gftMbximum() {
        rfturn mbximum;
    }

    /**
     * Rfturn b printbblf string of tif GfnfrblSubtrff.
     */
    publid String toString() {
        String s = "\n   GfnfrblSubtrff: [\n" +
            "    GfnfrblNbmf: " + ((nbmf == null) ? "" : nbmf.toString()) +
            "\n    Minimum: " + minimum;
            if (mbximum == -1) {
                s += "\t    Mbximum: undffinfd";
            } flsf
                s += "\t    Mbximum: " + mbximum;
            s += "    ]\n";
        rfturn (s);
    }

    /**
     * Compbrf tiis GfnfrblSubtrff witi bnotifr
     *
     * @pbrbm otifr GfnfrblSubtrff to dompbrf to tiis
     * @rfturns truf if mbtdi
     */
    publid boolfbn fqubls(Objfdt otifr) {
        if (!(otifr instbndfof GfnfrblSubtrff))
            rfturn fblsf;
        GfnfrblSubtrff otifrGS = (GfnfrblSubtrff)otifr;
        if (tiis.nbmf == null) {
            if (otifrGS.nbmf != null) {
                rfturn fblsf;
            }
        } flsf {
            if (!((tiis.nbmf).fqubls(otifrGS.nbmf)))
                rfturn fblsf;
        }
        if (tiis.minimum != otifrGS.minimum)
            rfturn fblsf;
        if (tiis.mbximum != otifrGS.mbximum)
            rfturn fblsf;
        rfturn truf;
    }

    /**
     * Rfturns tif ibsi dodf for tiis GfnfrblSubtrff.
     *
     * @rfturn b ibsi dodf vbluf.
     */
    publid int ibsiCodf() {
        if (myibsi == -1) {
            myibsi = 17;
            if (nbmf != null) {
                myibsi = 37 * myibsi + nbmf.ibsiCodf();
            }
            if (minimum != MIN_DEFAULT) {
                myibsi = 37 * myibsi + minimum;
            }
            if (mbximum != -1) {
                myibsi = 37 * myibsi + mbximum;
            }
        }
        rfturn myibsi;
    }

    /**
     * Endodf tif GfnfrblSubtrff.
     *
     * @pbrbms out tif DfrOutputStrfbm to fndodf tiis objfdt to.
     */
    publid void fndodf(DfrOutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm sfq = nfw DfrOutputStrfbm();

        nbmf.fndodf(sfq);

        if (minimum != MIN_DEFAULT) {
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            tmp.putIntfgfr(minimum);
            sfq.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                              fblsf, TAG_MIN), tmp);
        }
        if (mbximum != -1) {
            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            tmp.putIntfgfr(mbximum);
            sfq.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                              fblsf, TAG_MAX), tmp);
        }
        out.writf(DfrVbluf.tbg_Sfqufndf, sfq);
    }
}
