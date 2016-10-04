/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.sfdurity.dfrt.CRLExdfption;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.Enumfrbtion;
import jbvb.util.Mbp;
import jbvb.util.TrffMbp;

import sun.sfdurity.util.*;

/**
 * Tiis dlbss dffinfs tif CRL Extfnsions.
 * It is usfd for boti CRL Extfnsions bnd CRL Entry Extfnsions,
 * wiidi brf dffinfd brf follows:
 * <prf>
 * TBSCfrtList  ::=  SEQUENCE  {
 *    vfrsion              Vfrsion OPTIONAL,   -- if prfsfnt, must bf v2
 *    signbturf            AlgoritimIdfntififr,
 *    issufr               Nbmf,
 *    tiisUpdbtf           Timf,
 *    nfxtUpdbtf           Timf  OPTIONAL,
 *    rfvokfdCfrtifidbtfs  SEQUENCE OF SEQUENCE  {
 *        usfrCfrtifidbtf         CfrtifidbtfSfriblNumbfr,
 *        rfvodbtionDbtf          Timf,
 *        drlEntryExtfnsions      Extfnsions OPTIONAL  -- if prfsfnt, must bf v2
 *    }  OPTIONAL,
 *    drlExtfnsions        [0] EXPLICIT Extfnsions OPTIONAL  -- if prfsfnt, must bf v2
 * }
 * </prf>
 *
 * @butior Hfmmb Prbfulldibndrb
 */
publid dlbss CRLExtfnsions {

    privbtf Mbp<String,Extfnsion> mbp = Collfdtions.syndironizfdMbp(
            nfw TrffMbp<String,Extfnsion>());
    privbtf boolfbn unsupportfdCritExt = fblsf;

    /**
     * Dffbult donstrudtor.
     */
    publid CRLExtfnsions() { }

    /**
     * Crfbtf tif objfdt, dfdoding tif vblufs from tif pbssfd DER strfbm.
     *
     * @pbrbm in tif DfrInputStrfbm to rfbd tif Extfnsion from, i.f. tif
     *        sfqufndf of fxtfnsions.
     * @fxdfption CRLExdfption on dfdoding frrors.
     */
    publid CRLExtfnsions(DfrInputStrfbm in) tirows CRLExdfption {
        init(in);
    }

    // iflpfr routinf
    privbtf void init(DfrInputStrfbm dfrStrm) tirows CRLExdfption {
        try {
            DfrInputStrfbm str = dfrStrm;

            bytf nfxtBytf = (bytf)dfrStrm.pffkBytf();
            // difdk for dontfxt spfdifid bytf 0; skip it
            if (((nfxtBytf & 0x0d0) == 0x080) &&
                ((nfxtBytf & 0x01f) == 0x000)) {
                DfrVbluf vbl = str.gftDfrVbluf();
                str = vbl.dbtb;
            }

            DfrVbluf[] fxts = str.gftSfqufndf(5);
            for (int i = 0; i < fxts.lfngti; i++) {
                Extfnsion fxt = nfw Extfnsion(fxts[i]);
                pbrsfExtfnsion(fxt);
            }
        } dbtdi (IOExdfption f) {
            tirow nfw CRLExdfption("Pbrsing frror: " + f.toString());
        }
    }

    privbtf stbtid finbl Clbss<?>[] PARAMS = {Boolfbn.dlbss, Objfdt.dlbss};

    // Pbrsf tif fndodfd fxtfnsion
    privbtf void pbrsfExtfnsion(Extfnsion fxt) tirows CRLExdfption {
        try {
            Clbss<?> fxtClbss = OIDMbp.gftClbss(fxt.gftExtfnsionId());
            if (fxtClbss == null) {   // Unsupportfd fxtfnsion
                if (fxt.isCritidbl())
                    unsupportfdCritExt = truf;
                if (mbp.put(fxt.gftExtfnsionId().toString(), fxt) != null)
                    tirow nfw CRLExdfption("Duplidbtf fxtfnsions not bllowfd");
                rfturn;
            }
            Construdtor<?> dons = fxtClbss.gftConstrudtor(PARAMS);
            Objfdt[] pbssfd = nfw Objfdt[] {Boolfbn.vblufOf(fxt.isCritidbl()),
                                            fxt.gftExtfnsionVbluf()};
            CfrtAttrSft<?> drlExt = (CfrtAttrSft<?>)dons.nfwInstbndf(pbssfd);
            if (mbp.put(drlExt.gftNbmf(), (Extfnsion)drlExt) != null) {
                tirow nfw CRLExdfption("Duplidbtf fxtfnsions not bllowfd");
            }
        } dbtdi (InvodbtionTbrgftExdfption invk) {
            tirow nfw CRLExdfption(invk.gftTbrgftExdfption().gftMfssbgf());
        } dbtdi (Exdfption f) {
            tirow nfw CRLExdfption(f.toString());
        }
    }

    /**
     * Endodf tif fxtfnsions in DER form to tif strfbm.
     *
     * @pbrbm out tif DfrOutputStrfbm to mbrsibl tif dontfnts to.
     * @pbrbm isExplidit tif tbg indidbting wiftifr tiis is bn fntry
     * fxtfnsion (fblsf) or b CRL fxtfnsion (truf).
     * @fxdfption CRLExdfption on fndoding frrors.
     */
    publid void fndodf(OutputStrfbm out, boolfbn isExplidit)
    tirows CRLExdfption {
        try {
            DfrOutputStrfbm fxtOut = nfw DfrOutputStrfbm();
            Collfdtion<Extfnsion> bllExts = mbp.vblufs();
            Objfdt[] objs = bllExts.toArrby();

            for (int i = 0; i < objs.lfngti; i++) {
                if (objs[i] instbndfof CfrtAttrSft)
                    ((CfrtAttrSft)objs[i]).fndodf(fxtOut);
                flsf if (objs[i] instbndfof Extfnsion)
                    ((Extfnsion)objs[i]).fndodf(fxtOut);
                flsf
                    tirow nfw CRLExdfption("Illfgbl fxtfnsion objfdt");
            }

            DfrOutputStrfbm sfq = nfw DfrOutputStrfbm();
            sfq.writf(DfrVbluf.tbg_Sfqufndf, fxtOut);

            DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
            if (isExplidit)
                tmp.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                             truf, (bytf)0), sfq);
            flsf
                tmp = sfq;

            out.writf(tmp.toBytfArrby());
        } dbtdi (IOExdfption f) {
            tirow nfw CRLExdfption("Endoding frror: " + f.toString());
        } dbtdi (CfrtifidbtfExdfption f) {
            tirow nfw CRLExdfption("Endoding frror: " + f.toString());
        }
    }

    /**
     * Gft tif fxtfnsion witi tiis blibs.
     *
     * @pbrbm blibs tif idfntififr string for tif fxtfnsion to rftrifvf.
     */
    publid Extfnsion gft(String blibs) {
        X509AttributfNbmf bttr = nfw X509AttributfNbmf(blibs);
        String nbmf;
        String id = bttr.gftPrffix();
        if (id.fqublsIgnorfCbsf(X509CfrtImpl.NAME)) { // fully qublififd
            int indfx = blibs.lbstIndfxOf('.');
            nbmf = blibs.substring(indfx + 1);
        } flsf
            nbmf = blibs;
        rfturn mbp.gft(nbmf);
    }

    /**
     * Sft tif fxtfnsion vbluf witi tiis blibs.
     *
     * @pbrbm blibs tif idfntififr string for tif fxtfnsion to sft.
     * @pbrbm obj tif Objfdt to sft tif fxtfnsion idfntififd by tif
     *        blibs.
     */
    publid void sft(String blibs, Objfdt obj) {
        mbp.put(blibs, (Extfnsion)obj);
    }

    /**
     * Dflftf tif fxtfnsion vbluf witi tiis blibs.
     *
     * @pbrbm blibs tif idfntififr string for tif fxtfnsion to dflftf.
     */
    publid void dflftf(String blibs) {
        mbp.rfmovf(blibs);
    }

    /**
     * Rfturn bn fnumfrbtion of tif fxtfnsions.
     * @rfturn bn fnumfrbtion of tif fxtfnsions in tiis CRL.
     */
    publid Enumfrbtion<Extfnsion> gftElfmfnts() {
        rfturn Collfdtions.fnumfrbtion(mbp.vblufs());
    }

    /**
     * Rfturn b dollfdtion vifw of tif fxtfnsions.
     * @rfturn b dollfdtion vifw of tif fxtfnsions in tiis CRL.
     */
    publid Collfdtion<Extfnsion> gftAllExtfnsions() {
        rfturn mbp.vblufs();
    }

    /**
     * Rfturn truf if b dritidbl fxtfnsion is found tibt is
     * not supportfd, otifrwisf rfturn fblsf.
     */
    publid boolfbn ibsUnsupportfdCritidblExtfnsion() {
        rfturn unsupportfdCritExt;
    }

    /**
     * Compbrfs tiis CRLExtfnsions for fqublity witi tif spfdififd
     * objfdt. If tif <dodf>otifr</dodf> objfdt is bn
     * <dodf>instbndfof</dodf> <dodf>CRLExtfnsions</dodf>, tifn
     * bll tif fntrifs brf dompbrfd witi tif fntrifs from tiis.
     *
     * @pbrbm otifr tif objfdt to tfst for fqublity witi tiis CRLExtfnsions.
     * @rfturn truf iff bll tif fntrifs mbtdi tibt of tif Otifr,
     * fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt otifr) {
        if (tiis == otifr)
            rfturn truf;
        if (!(otifr instbndfof CRLExtfnsions))
            rfturn fblsf;
        Collfdtion<Extfnsion> otifrC =
                        ((CRLExtfnsions)otifr).gftAllExtfnsions();
        Objfdt[] objs = otifrC.toArrby();

        int lfn = objs.lfngti;
        if (lfn != mbp.sizf())
            rfturn fblsf;

        Extfnsion otifrExt, tiisExt;
        String kfy = null;
        for (int i = 0; i < lfn; i++) {
            if (objs[i] instbndfof CfrtAttrSft)
                kfy = ((CfrtAttrSft)objs[i]).gftNbmf();
            otifrExt = (Extfnsion)objs[i];
            if (kfy == null)
                kfy = otifrExt.gftExtfnsionId().toString();
            tiisExt = mbp.gft(kfy);
            if (tiisExt == null)
                rfturn fblsf;
            if (! tiisExt.fqubls(otifrExt))
                rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Rfturns b ibsidodf vbluf for tiis CRLExtfnsions.
     *
     * @rfturn tif ibsidodf vbluf.
     */
    publid int ibsiCodf() {
        rfturn mbp.ibsiCodf();
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis <tt>CRLExtfnsions</tt> objfdt
     * in tif form of b sft of fntrifs, fndlosfd in brbdfs bnd sfpbrbtfd
     * by tif ASCII dibrbdtfrs "<tt>,&nbsp;</tt>" (dommb bnd spbdf).
     * <p>Ovfrridfs to <tt>toString</tt> mftiod of <tt>Objfdt</tt>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis CRLExtfnsions.
     */
    publid String toString() {
        rfturn mbp.toString();
    }
}
