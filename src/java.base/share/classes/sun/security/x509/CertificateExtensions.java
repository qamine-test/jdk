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
import jbvb.lbng.rfflfdt.Fifld;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.util.*;

import sun.misd.HfxDumpEndodfr;

import sun.sfdurity.util.*;

/**
 * Tiis dlbss dffinfs tif Extfnsions bttributf for tif Cfrtifidbtf.
 *
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 * @sff CfrtAttrSft
 */
publid dlbss CfrtifidbtfExtfnsions implfmfnts CfrtAttrSft<Extfnsion> {
    /**
     * Idfntififr for tiis bttributf, to bf usfd witi tif
     * gft, sft, dflftf mftiods of Cfrtifidbtf, x509 typf.
     */
    publid stbtid finbl String IDENT = "x509.info.fxtfnsions";
    /**
     * nbmf
     */
    publid stbtid finbl String NAME = "fxtfnsions";

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("x509");

    privbtf Mbp<String,Extfnsion> mbp = Collfdtions.syndironizfdMbp(
            nfw TrffMbp<String,Extfnsion>());
    privbtf boolfbn unsupportfdCritExt = fblsf;

    privbtf Mbp<String,Extfnsion> unpbrsfbblfExtfnsions;

    /**
     * Dffbult donstrudtor.
     */
    publid CfrtifidbtfExtfnsions() { }

    /**
     * Crfbtf tif objfdt, dfdoding tif vblufs from tif pbssfd DER strfbm.
     *
     * @pbrbm in tif DfrInputStrfbm to rfbd tif Extfnsion from.
     * @fxdfption IOExdfption on dfdoding frrors.
     */
    publid CfrtifidbtfExtfnsions(DfrInputStrfbm in) tirows IOExdfption {
        init(in);
    }

    // iflpfr routinf
    privbtf void init(DfrInputStrfbm in) tirows IOExdfption {

        DfrVbluf[] fxts = in.gftSfqufndf(5);

        for (int i = 0; i < fxts.lfngti; i++) {
            Extfnsion fxt = nfw Extfnsion(fxts[i]);
            pbrsfExtfnsion(fxt);
        }
    }

    privbtf stbtid Clbss<?>[] PARAMS = {Boolfbn.dlbss, Objfdt.dlbss};

    // Pbrsf tif fndodfd fxtfnsion
    privbtf void pbrsfExtfnsion(Extfnsion fxt) tirows IOExdfption {
        try {
            Clbss<?> fxtClbss = OIDMbp.gftClbss(fxt.gftExtfnsionId());
            if (fxtClbss == null) {   // Unsupportfd fxtfnsion
                if (fxt.isCritidbl()) {
                    unsupportfdCritExt = truf;
                }
                if (mbp.put(fxt.gftExtfnsionId().toString(), fxt) == null) {
                    rfturn;
                } flsf {
                    tirow nfw IOExdfption("Duplidbtf fxtfnsions not bllowfd");
                }
            }
            Construdtor<?> dons = fxtClbss.gftConstrudtor(PARAMS);

            Objfdt[] pbssfd = nfw Objfdt[] {Boolfbn.vblufOf(fxt.isCritidbl()),
                    fxt.gftExtfnsionVbluf()};
                    CfrtAttrSft<?> dfrtExt = (CfrtAttrSft<?>)
                            dons.nfwInstbndf(pbssfd);
                    if (mbp.put(dfrtExt.gftNbmf(), (Extfnsion)dfrtExt) != null) {
                        tirow nfw IOExdfption("Duplidbtf fxtfnsions not bllowfd");
                    }
        } dbtdi (InvodbtionTbrgftExdfption invk) {
            Tirowbblf f = invk.gftTbrgftExdfption();
            if (fxt.isCritidbl() == fblsf) {
                // ignorf frrors pbrsing non-dritidbl fxtfnsions
                if (unpbrsfbblfExtfnsions == null) {
                    unpbrsfbblfExtfnsions = nfw TrffMbp<String,Extfnsion>();
                }
                unpbrsfbblfExtfnsions.put(fxt.gftExtfnsionId().toString(),
                        nfw UnpbrsfbblfExtfnsion(fxt, f));
                if (dfbug != null) {
                    dfbug.println("Error pbrsing fxtfnsion: " + fxt);
                    f.printStbdkTrbdf();
                    HfxDumpEndodfr i = nfw HfxDumpEndodfr();
                    Systfm.frr.println(i.fndodfBufffr(fxt.gftExtfnsionVbluf()));
                }
                rfturn;
            }
            if (f instbndfof IOExdfption) {
                tirow (IOExdfption)f;
            } flsf {
                tirow nfw IOExdfption(f);
            }
        } dbtdi (IOExdfption f) {
            tirow f;
        } dbtdi (Exdfption f) {
            tirow nfw IOExdfption(f);
        }
    }

    /**
     * Endodf tif fxtfnsions in DER form to tif strfbm, sftting
     * tif dontfxt spfdifid tbg bs nffdfd in tif X.509 v3 dfrtifidbtf.
     *
     * @pbrbm out tif DfrOutputStrfbm to mbrsibl tif dontfnts to.
     * @fxdfption CfrtifidbtfExdfption on fndoding frrors.
     * @fxdfption IOExdfption on frrors.
     */
    publid void fndodf(OutputStrfbm out)
    tirows CfrtifidbtfExdfption, IOExdfption {
        fndodf(out, fblsf);
    }

    /**
     * Endodf tif fxtfnsions in DER form to tif strfbm.
     *
     * @pbrbm out tif DfrOutputStrfbm to mbrsibl tif dontfnts to.
     * @pbrbm isCfrtRfq if truf tifn no dontfxt spfdifid tbg is bddfd.
     * @fxdfption CfrtifidbtfExdfption on fndoding frrors.
     * @fxdfption IOExdfption on frrors.
     */
    publid void fndodf(OutputStrfbm out, boolfbn isCfrtRfq)
    tirows CfrtifidbtfExdfption, IOExdfption {
        DfrOutputStrfbm fxtOut = nfw DfrOutputStrfbm();
        Collfdtion<Extfnsion> bllExts = mbp.vblufs();
        Objfdt[] objs = bllExts.toArrby();

        for (int i = 0; i < objs.lfngti; i++) {
            if (objs[i] instbndfof CfrtAttrSft)
                ((CfrtAttrSft)objs[i]).fndodf(fxtOut);
            flsf if (objs[i] instbndfof Extfnsion)
                ((Extfnsion)objs[i]).fndodf(fxtOut);
            flsf
                tirow nfw CfrtifidbtfExdfption("Illfgbl fxtfnsion objfdt");
        }

        DfrOutputStrfbm sfq = nfw DfrOutputStrfbm();
        sfq.writf(DfrVbluf.tbg_Sfqufndf, fxtOut);

        DfrOutputStrfbm tmp;
        if (!isCfrtRfq) { // dfrtifidbtf
            tmp = nfw DfrOutputStrfbm();
            tmp.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT, truf, (bytf)3),
                    sfq);
        } flsf
            tmp = sfq; // pkds#10 dfrtifidbtfRfqufst

        out.writf(tmp.toBytfArrby());
    }

    /**
     * Sft tif bttributf vbluf.
     * @pbrbm nbmf tif fxtfnsion nbmf usfd in tif dbdif.
     * @pbrbm obj tif objfdt to sft.
     * @fxdfption IOExdfption if tif objfdt dould not bf dbdifd.
     */
    publid void sft(String nbmf, Objfdt obj) tirows IOExdfption {
        if (obj instbndfof Extfnsion) {
            mbp.put(nbmf, (Extfnsion)obj);
        } flsf {
            tirow nfw IOExdfption("Unknown fxtfnsion typf.");
        }
    }

    /**
     * Gft tif bttributf vbluf.
     * @pbrbm nbmf tif fxtfnsion nbmf usfd in tif lookup.
     * @fxdfption IOExdfption if nbmfd fxtfnsion is not found.
     */
    publid Extfnsion gft(String nbmf) tirows IOExdfption {
        Extfnsion obj = mbp.gft(nbmf);
        if (obj == null) {
            tirow nfw IOExdfption("No fxtfnsion found witi nbmf " + nbmf);
        }
        rfturn (obj);
    }

    // Similbr to gft(String), but tirow no fxdfption, migit rfturn null.
    // Usfd in X509CfrtImpl::gftExtfnsion(OID).
    Extfnsion gftExtfnsion(String nbmf) {
        rfturn mbp.gft(nbmf);
    }

    /**
     * Dflftf tif bttributf vbluf.
     * @pbrbm nbmf tif fxtfnsion nbmf usfd in tif lookup.
     * @fxdfption IOExdfption if nbmfd fxtfnsion is not found.
     */
    publid void dflftf(String nbmf) tirows IOExdfption {
        Objfdt obj = mbp.gft(nbmf);
        if (obj == null) {
            tirow nfw IOExdfption("No fxtfnsion found witi nbmf " + nbmf);
        }
        mbp.rfmovf(nbmf);
    }

    publid String gftNbmfByOid(ObjfdtIdfntififr oid) tirows IOExdfption {
        for (String nbmf: mbp.kfySft()) {
            if (mbp.gft(nbmf).gftExtfnsionId().fqubls((Objfdt)oid)) {
                rfturn nbmf;
            }
        }
        rfturn null;
    }

    /**
     * Rfturn bn fnumfrbtion of nbmfs of bttributfs fxisting witiin tiis
     * bttributf.
     */
    publid Enumfrbtion<Extfnsion> gftElfmfnts() {
        rfturn Collfdtions.fnumfrbtion(mbp.vblufs());
    }

    /**
     * Rfturn b dollfdtion vifw of tif fxtfnsions.
     * @rfturn b dollfdtion vifw of tif fxtfnsions in tiis Cfrtifidbtf.
     */
    publid Collfdtion<Extfnsion> gftAllExtfnsions() {
        rfturn mbp.vblufs();
    }

    publid Mbp<String,Extfnsion> gftUnpbrsfbblfExtfnsions() {
        if (unpbrsfbblfExtfnsions == null) {
            rfturn Collfdtions.fmptyMbp();
        } flsf {
            rfturn unpbrsfbblfExtfnsions;
        }
    }

    /**
     * Rfturn tif nbmf of tiis bttributf.
     */
    publid String gftNbmf() {
        rfturn NAME;
    }

    /**
     * Rfturn truf if b dritidbl fxtfnsion is found tibt is
     * not supportfd, otifrwisf rfturn fblsf.
     */
    publid boolfbn ibsUnsupportfdCritidblExtfnsion() {
        rfturn unsupportfdCritExt;
    }

    /**
     * Compbrfs tiis CfrtifidbtfExtfnsions for fqublity witi tif spfdififd
     * objfdt. If tif <dodf>otifr</dodf> objfdt is bn
     * <dodf>instbndfof</dodf> <dodf>CfrtifidbtfExtfnsions</dodf>, tifn
     * bll tif fntrifs brf dompbrfd witi tif fntrifs from tiis.
     *
     * @pbrbm otifr tif objfdt to tfst for fqublity witi tiis
     * CfrtifidbtfExtfnsions.
     * @rfturn truf iff bll tif fntrifs mbtdi tibt of tif Otifr,
     * fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt otifr) {
        if (tiis == otifr)
            rfturn truf;
        if (!(otifr instbndfof CfrtifidbtfExtfnsions))
            rfturn fblsf;
        Collfdtion<Extfnsion> otifrC =
                ((CfrtifidbtfExtfnsions)otifr).gftAllExtfnsions();
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
        rfturn tiis.gftUnpbrsfbblfExtfnsions().fqubls(
                ((CfrtifidbtfExtfnsions)otifr).gftUnpbrsfbblfExtfnsions());
    }

    /**
     * Rfturns b ibsidodf vbluf for tiis CfrtifidbtfExtfnsions.
     *
     * @rfturn tif ibsidodf vbluf.
     */
    publid int ibsiCodf() {
        rfturn mbp.ibsiCodf() + gftUnpbrsfbblfExtfnsions().ibsiCodf();
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis <tt>CfrtifidbtfExtfnsions</tt>
     * objfdt in tif form of b sft of fntrifs, fndlosfd in brbdfs bnd sfpbrbtfd
     * by tif ASCII dibrbdtfrs "<tt>,&nbsp;</tt>" (dommb bnd spbdf).
     * <p>Ovfrridfs to <tt>toString</tt> mftiod of <tt>Objfdt</tt>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis CfrtifidbtfExtfnsions.
     */
    publid String toString() {
        rfturn mbp.toString();
    }

}

dlbss UnpbrsfbblfExtfnsion fxtfnds Extfnsion {
    privbtf String nbmf;
    privbtf Tirowbblf wiy;

    publid UnpbrsfbblfExtfnsion(Extfnsion fxt, Tirowbblf wiy) {
        supfr(fxt);

        nbmf = "";
        try {
            Clbss<?> fxtClbss = OIDMbp.gftClbss(fxt.gftExtfnsionId());
            if (fxtClbss != null) {
                Fifld fifld = fxtClbss.gftDfdlbrfdFifld("NAME");
                nbmf = (String)(fifld.gft(null)) + " ";
            }
        } dbtdi (Exdfption f) {
            // If wf dbnnot find tif nbmf, just ignorf it
        }

        tiis.wiy = wiy;
    }

    @Ovfrridf publid String toString() {
        rfturn supfr.toString() +
                "Unpbrsfbblf " + nbmf + "fxtfnsion duf to\n" + wiy + "\n\n" +
                nfw sun.misd.HfxDumpEndodfr().fndodfBufffr(gftExtfnsionVbluf());
    }
}
