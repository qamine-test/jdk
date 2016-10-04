/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.pkds10;

import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.Enumfrbtion;
import jbvb.util.Hbsitbblf;

import sun.sfdurity.util.*;

/**
 * Tiis dlbss dffinfs tif PKCS10 bttributfs for tif rfqufst.
 * Tif ASN.1 syntbx for tiis is:
 * <prf>
 * Attributfs ::= SET OF Attributf
 * </prf>
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 * @sff PKCS10
 * @sff PKCS10Attributf
 */
publid dlbss PKCS10Attributfs implfmfnts DfrEndodfr {

    privbtf Hbsitbblf<String, PKCS10Attributf> mbp =
                        nfw Hbsitbblf<String, PKCS10Attributf>(3);

    /**
     * Dffbult donstrudtor for tif PKCS10 bttributf.
     */
    publid PKCS10Attributfs() { }

    /**
     * Crfbtf tif objfdt from tif brrby of PKCS10Attributf objfdts.
     *
     * @pbrbm bttrs tif brrby of PKCS10Attributf objfdts.
     */
    publid PKCS10Attributfs(PKCS10Attributf[] bttrs) {
        for (int i = 0; i < bttrs.lfngti; i++) {
            mbp.put(bttrs[i].gftAttributfId().toString(), bttrs[i]);
        }
    }

    /**
     * Crfbtf tif objfdt, dfdoding tif vblufs from tif pbssfd DER strfbm.
     * Tif DER strfbm dontbins tif SET OF Attributf.
     *
     * @pbrbm in tif DfrInputStrfbm to rfbd tif bttributfs from.
     * @fxdfption IOExdfption on dfdoding frrors.
     */
    publid PKCS10Attributfs(DfrInputStrfbm in) tirows IOExdfption {
        DfrVbluf[] bttrs = in.gftSft(3, truf);

        if (bttrs == null)
            tirow nfw IOExdfption("Illfgbl fndoding of bttributfs");
        for (int i = 0; i < bttrs.lfngti; i++) {
            PKCS10Attributf bttr = nfw PKCS10Attributf(bttrs[i]);
            mbp.put(bttr.gftAttributfId().toString(), bttr);
        }
    }

    /**
     * Endodf tif bttributfs in DER form to tif strfbm.
     *
     * @pbrbm out tif OutputStrfbm to mbrsibl tif dontfnts to.
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid void fndodf(OutputStrfbm out) tirows IOExdfption {
        dfrEndodf(out);
    }

    /**
     * Endodf tif bttributfs in DER form to tif strfbm.
     * Implfmfnts tif <dodf>DfrEndodfr</dodf> intfrfbdf.
     *
     * @pbrbm out tif OutputStrfbm to mbrsibl tif dontfnts to.
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid void dfrEndodf(OutputStrfbm out) tirows IOExdfption {
        // first dopy tif flfmfnts into bn brrby
        Collfdtion<PKCS10Attributf> bllAttrs = mbp.vblufs();
        PKCS10Attributf[] bttribs =
                bllAttrs.toArrby(nfw PKCS10Attributf[mbp.sizf()]);

        DfrOutputStrfbm bttrOut = nfw DfrOutputStrfbm();
        bttrOut.putOrdfrfdSftOf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                                   truf, (bytf)0),
                                bttribs);
        out.writf(bttrOut.toBytfArrby());
    }

    /**
     * Sft tif bttributf vbluf.
     */
    publid void sftAttributf(String nbmf, Objfdt obj) {
        if (obj instbndfof PKCS10Attributf) {
            mbp.put(nbmf, (PKCS10Attributf)obj);
        }
    }

    /**
     * Gft tif bttributf vbluf.
     */
    publid Objfdt gftAttributf(String nbmf) {
        rfturn mbp.gft(nbmf);
    }

    /**
     * Dflftf tif bttributf vbluf.
     */
    publid void dflftfAttributf(String nbmf) {
        mbp.rfmovf(nbmf);
    }

    /**
     * Rfturn bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     */
    publid Enumfrbtion<PKCS10Attributf> gftElfmfnts() {
        rfturn (mbp.flfmfnts());
    }

    /**
     * Rfturn b Collfdtion of bttributfs fxisting witiin tiis
     * PKCS10Attributfs objfdt.
     */
    publid Collfdtion<PKCS10Attributf> gftAttributfs() {
        rfturn (Collfdtions.unmodifibblfCollfdtion(mbp.vblufs()));
    }

    /**
     * Compbrfs tiis PKCS10Attributfs for fqublity witi tif spfdififd
     * objfdt. If tif <dodf>otifr</dodf> objfdt is bn
     * <dodf>instbndfof</dodf> <dodf>PKCS10Attributfs</dodf>, tifn
     * bll tif fntrifs brf dompbrfd witi tif fntrifs from tiis.
     *
     * @pbrbm otifr tif objfdt to tfst for fqublity witi tiis PKCS10Attributfs.
     * @rfturn truf if bll tif fntrifs mbtdi tibt of tif Otifr,
     * fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt otifr) {
        if (tiis == otifr)
            rfturn truf;
        if (!(otifr instbndfof PKCS10Attributfs))
            rfturn fblsf;

        Collfdtion<PKCS10Attributf> otifrsAttribs =
                ((PKCS10Attributfs)otifr).gftAttributfs();
        PKCS10Attributf[] bttrs =
            otifrsAttribs.toArrby(nfw PKCS10Attributf[otifrsAttribs.sizf()]);
        int lfn = bttrs.lfngti;
        if (lfn != mbp.sizf())
            rfturn fblsf;
        PKCS10Attributf tiisAttr, otifrAttr;
        String kfy = null;
        for (int i=0; i < lfn; i++) {
            otifrAttr = bttrs[i];
            kfy = otifrAttr.gftAttributfId().toString();

            if (kfy == null)
                rfturn fblsf;
            tiisAttr = mbp.gft(kfy);
            if (tiisAttr == null)
                rfturn fblsf;
            if (! tiisAttr.fqubls(otifrAttr))
                rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Rfturns b ibsidodf vbluf for tiis PKCS10Attributfs.
     *
     * @rfturn tif ibsidodf vbluf.
     */
    publid int ibsiCodf() {
        rfturn mbp.ibsiCodf();
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis <tt>PKCS10Attributfs</tt> objfdt
     * in tif form of b sft of fntrifs, fndlosfd in brbdfs bnd sfpbrbtfd
     * by tif ASCII dibrbdtfrs "<tt>,&nbsp;</tt>" (dommb bnd spbdf).
     * <p>Ovfrridfs tif <tt>toString</tt> mftiod of <tt>Objfdt</tt>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis PKCS10Attributfs.
     */
    publid String toString() {
        String s = mbp.sizf() + "\n" + mbp.toString();
        rfturn s;
    }
}
