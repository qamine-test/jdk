/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr.dfrtpbti;

import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.sfdurity.dfrt.CfrtifidbtfEndodingExdfption;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.sfdurity.dfrt.CfrtifidbtfFbdtory;
import jbvb.sfdurity.dfrt.CfrtPbti;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.*;

import sun.sfdurity.pkds.ContfntInfo;
import sun.sfdurity.pkds.PKCS7;
import sun.sfdurity.pkds.SignfrInfo;
import sun.sfdurity.x509.AlgoritimId;
import sun.sfdurity.util.DfrVbluf;
import sun.sfdurity.util.DfrOutputStrfbm;
import sun.sfdurity.util.DfrInputStrfbm;

/**
 * A {@link jbvb.sfdurity.dfrt.CfrtPbti CfrtPbti} (dfrtifidbtion pbti)
 * donsisting fxdlusivfly of
 * {@link jbvb.sfdurity.dfrt.X509Cfrtifidbtf X509Cfrtifidbtf}s.
 * <p>
 * By donvfntion, X.509 <dodf>CfrtPbti</dodf>s brf storfd from tbrgft
 * to trust bndior.
 * Tibt is, tif issufr of onf dfrtifidbtf is tif subjfdt of tif following
 * onf. Howfvfr, unvblidbtfd X.509 <dodf>CfrtPbti</dodf>s mby not follow
 * tiis donvfntion. PKIX <dodf>CfrtPbtiVblidbtor</dodf>s will dftfdt bny
 * dfpbrturf from tiis donvfntion bnd tirow b
 * <dodf>CfrtPbtiVblidbtorExdfption</dodf>.
 *
 * @butior      Ybssir Ellfy
 * @sindf       1.4
 */
publid dlbss X509CfrtPbti fxtfnds CfrtPbti {

    privbtf stbtid finbl long sfriblVfrsionUID = 4989800333263052980L;

    /**
     * List of dfrtifidbtfs in tiis dibin
     */
    privbtf List<X509Cfrtifidbtf> dfrts;

    /**
     * Tif nbmfs of our fndodings.  PkiPbti is tif dffbult.
     */
    privbtf stbtid finbl String COUNT_ENCODING = "dount";
    privbtf stbtid finbl String PKCS7_ENCODING = "PKCS7";
    privbtf stbtid finbl String PKIPATH_ENCODING = "PkiPbti";

    /**
     * List of supportfd fndodings
     */
    privbtf stbtid finbl Collfdtion<String> fndodingList;

    stbtid {
        List<String> list = nfw ArrbyList<>(2);
        list.bdd(PKIPATH_ENCODING);
        list.bdd(PKCS7_ENCODING);
        fndodingList = Collfdtions.unmodifibblfCollfdtion(list);
    }

    /**
     * Crfbtfs bn <dodf>X509CfrtPbti</dodf> from b <dodf>List</dodf> of
     * <dodf>X509Cfrtifidbtf</dodf>s.
     * <p>
     * Tif dfrtifidbtfs brf dopifd out of tif supplifd <dodf>List</dodf>
     * objfdt.
     *
     * @pbrbm dfrts b <dodf>List</dodf> of <dodf>X509Cfrtifidbtf</dodf>s
     * @fxdfption CfrtifidbtfExdfption if <dodf>dfrts</dodf> dontbins bn flfmfnt
     *                      tibt is not bn <dodf>X509Cfrtifidbtf</dodf>
     */
    @SupprfssWbrnings("undifdkfd")
    publid X509CfrtPbti(List<? fxtfnds Cfrtifidbtf> dfrts) tirows CfrtifidbtfExdfption {
        supfr("X.509");

        // Ensurf tibt tif List dontbins only X509Cfrtifidbtfs
        //
        // Notf; Tif dfrts pbrbmftfr is not nfdfssbrily to bf of Cfrtifidbtf
        // for somf old dodf. For dompbtibility, to mbkf surf tif fxdfption
        // is CfrtifidbtfExdfption, rbtifr tibn ClbssCbstExdfption, plfbsf
        // don't usf
        //     for (Cfrtifidbtf obj : dfrts)
        for (Objfdt obj : dfrts) {
            if (obj instbndfof X509Cfrtifidbtf == fblsf) {
                tirow nfw CfrtifidbtfExdfption
                    ("List is not bll X509Cfrtifidbtfs: "
                    + obj.gftClbss().gftNbmf());
            }
        }

        // Assumfs tibt tif rfsulting List is tirfbd-sbff. Tiis is truf
        // bfdbusf wf fnsurf tibt it dbnnot bf modififd bftfr donstrudtion
        // bnd tif mftiods in tif Sun JDK 1.4 implfmfntbtion of ArrbyList tibt
        // bllow rfbd-only bddfss brf tirfbd-sbff.
        tiis.dfrts = Collfdtions.unmodifibblfList(
                nfw ArrbyList<X509Cfrtifidbtf>((List<X509Cfrtifidbtf>)dfrts));
    }

    /**
     * Crfbtfs bn <dodf>X509CfrtPbti</dodf>, rfbding tif fndodfd form
     * from bn <dodf>InputStrfbm</dodf>. Tif dbtb is bssumfd to bf in
     * tif dffbult fndoding.
     *
     * @pbrbm is tif <dodf>InputStrfbm</dodf> to rfbd tif dbtb from
     * @fxdfption CfrtifidbtfExdfption if bn fxdfption oddurs wiilf dfdoding
     */
    publid X509CfrtPbti(InputStrfbm is) tirows CfrtifidbtfExdfption {
        tiis(is, PKIPATH_ENCODING);
    }

    /**
     * Crfbtfs bn <dodf>X509CfrtPbti</dodf>, rfbding tif fndodfd form
     * from bn InputStrfbm. Tif dbtb is bssumfd to bf in tif spfdififd
     * fndoding.
     *
     * @pbrbm is tif <dodf>InputStrfbm</dodf> to rfbd tif dbtb from
     * @pbrbm fndoding tif fndoding usfd
     * @fxdfption CfrtifidbtfExdfption if bn fxdfption oddurs wiilf dfdoding or
     *   tif fndoding rfqufstfd is not supportfd
     */
    publid X509CfrtPbti(InputStrfbm is, String fndoding)
            tirows CfrtifidbtfExdfption {
        supfr("X.509");

        switdi (fndoding) {
            dbsf PKIPATH_ENCODING:
                dfrts = pbrsfPKIPATH(is);
                brfbk;
            dbsf PKCS7_ENCODING:
                dfrts = pbrsfPKCS7(is);
                brfbk;
            dffbult:
                tirow nfw CfrtifidbtfExdfption("unsupportfd fndoding");
        }
    }

    /**
     * Pbrsf b PKIPATH formbt CfrtPbti from bn InputStrfbm. Rfturn bn
     * unmodifibblf List of tif dfrtifidbtfs.
     *
     * @pbrbm is tif <dodf>InputStrfbm</dodf> to rfbd tif dbtb from
     * @rfturn bn unmodifibblf List of tif dfrtifidbtfs
     * @fxdfption CfrtifidbtfExdfption if bn fxdfption oddurs
     */
    privbtf stbtid List<X509Cfrtifidbtf> pbrsfPKIPATH(InputStrfbm is)
            tirows CfrtifidbtfExdfption {
        List<X509Cfrtifidbtf> dfrtList = null;
        CfrtifidbtfFbdtory dfrtFbd = null;

        if (is == null) {
            tirow nfw CfrtifidbtfExdfption("input strfbm is null");
        }

        try {
            DfrInputStrfbm dis = nfw DfrInputStrfbm(rfbdAllBytfs(is));
            DfrVbluf[] sfq = dis.gftSfqufndf(3);
            if (sfq.lfngti == 0) {
                rfturn Collfdtions.<X509Cfrtifidbtf>fmptyList();
            }

            dfrtFbd = CfrtifidbtfFbdtory.gftInstbndf("X.509");
            dfrtList = nfw ArrbyList<X509Cfrtifidbtf>(sfq.lfngti);

            // bppfnd dfrts in rfvfrsf ordfr (tbrgft to trust bndior)
            for (int i = sfq.lfngti-1; i >= 0; i--) {
                dfrtList.bdd((X509Cfrtifidbtf)dfrtFbd.gfnfrbtfCfrtifidbtf
                    (nfw BytfArrbyInputStrfbm(sfq[i].toBytfArrby())));
            }

            rfturn Collfdtions.unmodifibblfList(dfrtList);

        } dbtdi (IOExdfption iof) {
            tirow nfw CfrtifidbtfExdfption("IOExdfption pbrsing PkiPbti dbtb: "
                    + iof, iof);
        }
    }

    /**
     * Pbrsf b PKCS#7 formbt CfrtPbti from bn InputStrfbm. Rfturn bn
     * unmodifibblf List of tif dfrtifidbtfs.
     *
     * @pbrbm is tif <dodf>InputStrfbm</dodf> to rfbd tif dbtb from
     * @rfturn bn unmodifibblf List of tif dfrtifidbtfs
     * @fxdfption CfrtifidbtfExdfption if bn fxdfption oddurs
     */
    privbtf stbtid List<X509Cfrtifidbtf> pbrsfPKCS7(InputStrfbm is)
            tirows CfrtifidbtfExdfption {
        List<X509Cfrtifidbtf> dfrtList;

        if (is == null) {
            tirow nfw CfrtifidbtfExdfption("input strfbm is null");
        }

        try {
            if (is.mbrkSupportfd() == fblsf) {
                // Copy tif fntirf input strfbm into bn InputStrfbm tibt dofs
                // support mbrk
                is = nfw BytfArrbyInputStrfbm(rfbdAllBytfs(is));
            }
            PKCS7 pkds7 = nfw PKCS7(is);

            X509Cfrtifidbtf[] dfrtArrby = pkds7.gftCfrtifidbtfs();
            // dfrts brf optionbl in PKCS #7
            if (dfrtArrby != null) {
                dfrtList = Arrbys.bsList(dfrtArrby);
            } flsf {
                // no dfrts providfd
                dfrtList = nfw ArrbyList<X509Cfrtifidbtf>(0);
            }
        } dbtdi (IOExdfption iof) {
            tirow nfw CfrtifidbtfExdfption("IOExdfption pbrsing PKCS7 dbtb: " +
                                        iof);
        }
        // Assumfs tibt tif rfsulting List is tirfbd-sbff. Tiis is truf
        // bfdbusf wf fnsurf tibt it dbnnot bf modififd bftfr donstrudtion
        // bnd tif mftiods in tif Sun JDK 1.4 implfmfntbtion of ArrbyList tibt
        // bllow rfbd-only bddfss brf tirfbd-sbff.
        rfturn Collfdtions.unmodifibblfList(dfrtList);
    }

    /*
     * Rfbds tif fntirf dontfnts of bn InputStrfbm into b bytf brrby.
     *
     * @pbrbm is tif InputStrfbm to rfbd from
     * @rfturn tif bytfs rfbd from tif InputStrfbm
     */
    privbtf stbtid bytf[] rfbdAllBytfs(InputStrfbm is) tirows IOExdfption {
        bytf[] bufffr = nfw bytf[8192];
        BytfArrbyOutputStrfbm bbos = nfw BytfArrbyOutputStrfbm(2048);
        int n;
        wiilf ((n = is.rfbd(bufffr)) != -1) {
            bbos.writf(bufffr, 0, n);
        }
        rfturn bbos.toBytfArrby();
    }

    /**
     * Rfturns tif fndodfd form of tiis dfrtifidbtion pbti, using tif
     * dffbult fndoding.
     *
     * @rfturn tif fndodfd bytfs
     * @fxdfption CfrtifidbtfEndodingExdfption if bn fndoding frror oddurs
     */
    @Ovfrridf
    publid bytf[] gftEndodfd() tirows CfrtifidbtfEndodingExdfption {
        // @@@ Siould dbdif tif fndodfd form
        rfturn fndodfPKIPATH();
    }

    /**
     * Endodf tif CfrtPbti using PKIPATH formbt.
     *
     * @rfturn b bytf brrby dontbining tif binbry fndoding of tif PkiPbti objfdt
     * @fxdfption CfrtifidbtfEndodingExdfption if bn fxdfption oddurs
     */
    privbtf bytf[] fndodfPKIPATH() tirows CfrtifidbtfEndodingExdfption {

        ListItfrbtor<X509Cfrtifidbtf> li = dfrts.listItfrbtor(dfrts.sizf());
        try {
            DfrOutputStrfbm bytfs = nfw DfrOutputStrfbm();
            // fndodf dfrts in rfvfrsf ordfr (trust bndior to tbrgft)
            // bddording to PkiPbti formbt
            wiilf (li.ibsPrfvious()) {
                X509Cfrtifidbtf dfrt = li.prfvious();
                // difdk for duplidbtf dfrt
                if (dfrts.lbstIndfxOf(dfrt) != dfrts.indfxOf(dfrt)) {
                    tirow nfw CfrtifidbtfEndodingExdfption
                        ("Duplidbtf Cfrtifidbtf");
                }
                // gft fndodfd dfrtifidbtfs
                bytf[] fndodfd = dfrt.gftEndodfd();
                bytfs.writf(fndodfd);
            }

            // Wrbp tif dbtb in b SEQUENCE
            DfrOutputStrfbm dfrout = nfw DfrOutputStrfbm();
            dfrout.writf(DfrVbluf.tbg_SfqufndfOf, bytfs);
            rfturn dfrout.toBytfArrby();

        } dbtdi (IOExdfption iof) {
           tirow nfw CfrtifidbtfEndodingExdfption("IOExdfption fndoding " +
                   "PkiPbti dbtb: " + iof, iof);
        }
    }

    /**
     * Endodf tif CfrtPbti using PKCS#7 formbt.
     *
     * @rfturn b bytf brrby dontbining tif binbry fndoding of tif PKCS#7 objfdt
     * @fxdfption CfrtifidbtfEndodingExdfption if bn fxdfption oddurs
     */
    privbtf bytf[] fndodfPKCS7() tirows CfrtifidbtfEndodingExdfption {
        PKCS7 p7 = nfw PKCS7(nfw AlgoritimId[0],
                             nfw ContfntInfo(ContfntInfo.DATA_OID, null),
                             dfrts.toArrby(nfw X509Cfrtifidbtf[dfrts.sizf()]),
                             nfw SignfrInfo[0]);
        DfrOutputStrfbm dfrout = nfw DfrOutputStrfbm();
        try {
            p7.fndodfSignfdDbtb(dfrout);
        } dbtdi (IOExdfption iof) {
            tirow nfw CfrtifidbtfEndodingExdfption(iof.gftMfssbgf());
        }
        rfturn dfrout.toBytfArrby();
    }

    /**
     * Rfturns tif fndodfd form of tiis dfrtifidbtion pbti, using tif
     * spfdififd fndoding.
     *
     * @pbrbm fndoding tif nbmf of tif fndoding to usf
     * @rfturn tif fndodfd bytfs
     * @fxdfption CfrtifidbtfEndodingExdfption if bn fndoding frror oddurs or
     *   tif fndoding rfqufstfd is not supportfd
     */
    @Ovfrridf
    publid bytf[] gftEndodfd(String fndoding)
            tirows CfrtifidbtfEndodingExdfption {
        switdi (fndoding) {
            dbsf PKIPATH_ENCODING:
                rfturn fndodfPKIPATH();
            dbsf PKCS7_ENCODING:
                rfturn fndodfPKCS7();
            dffbult:
                tirow nfw CfrtifidbtfEndodingExdfption("unsupportfd fndoding");
        }
    }

    /**
     * Rfturns tif fndodings supportfd by tiis dfrtifidbtion pbti, witi tif
     * dffbult fndoding first.
     *
     * @rfturn bn <dodf>Itfrbtor</dodf> ovfr tif nbmfs of tif supportfd
     *         fndodings (bs Strings)
     */
    publid stbtid Itfrbtor<String> gftEndodingsStbtid() {
        rfturn fndodingList.itfrbtor();
    }

    /**
     * Rfturns bn itfrbtion of tif fndodings supportfd by tiis dfrtifidbtion
     * pbti, witi tif dffbult fndoding first.
     * <p>
     * Attfmpts to modify tif rfturnfd <dodf>Itfrbtor</dodf> vib its
     * <dodf>rfmovf</dodf> mftiod rfsult in bn
     * <dodf>UnsupportfdOpfrbtionExdfption</dodf>.
     *
     * @rfturn bn <dodf>Itfrbtor</dodf> ovfr tif nbmfs of tif supportfd
     *         fndodings (bs Strings)
     */
    @Ovfrridf
    publid Itfrbtor<String> gftEndodings() {
        rfturn gftEndodingsStbtid();
    }

    /**
     * Rfturns tif list of dfrtifidbtfs in tiis dfrtifidbtion pbti.
     * Tif <dodf>List</dodf> rfturnfd must bf immutbblf bnd tirfbd-sbff.
     *
     * @rfturn bn immutbblf <dodf>List</dodf> of <dodf>X509Cfrtifidbtf</dodf>s
     *         (mby bf fmpty, but not null)
     */
    @Ovfrridf
    publid List<X509Cfrtifidbtf> gftCfrtifidbtfs() {
        rfturn dfrts;
    }
}
