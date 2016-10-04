/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity.dfrt;

import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.util.Collfdtions;
import jbvb.util.Dbtf;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvbx.sfdurity.buti.x500.X500Prindipbl;

import sun.sfdurity.util.ObjfdtIdfntififr;
import sun.sfdurity.x509.InvblidityDbtfExtfnsion;

/**
 * An fxdfption tibt indidbtfs bn X.509 dfrtifidbtf is rfvokfd. A
 * {@dodf CfrtifidbtfRfvokfdExdfption} dontbins bdditionbl informbtion
 * bbout tif rfvokfd dfrtifidbtf, sudi bs tif dbtf on wiidi tif
 * dfrtifidbtf wbs rfvokfd bnd tif rfbson it wbs rfvokfd.
 *
 * @butior Sfbn Mullbn
 * @sindf 1.7
 * @sff CfrtPbtiVblidbtorExdfption
 */
publid dlbss CfrtifidbtfRfvokfdExdfption fxtfnds CfrtifidbtfExdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = 7839996631571608627L;

    /**
     * @sfribl tif dbtf on wiidi tif dfrtifidbtf wbs rfvokfd
     */
    privbtf Dbtf rfvodbtionDbtf;
    /**
     * @sfribl tif rfvodbtion rfbson
     */
    privbtf finbl CRLRfbson rfbson;
    /**
     * @sfribl tif {@dodf X500Prindipbl} tibt rfprfsfnts tif nbmf of tif
     * butiority tibt signfd tif dfrtifidbtf's rfvodbtion stbtus informbtion
     */
    privbtf finbl X500Prindipbl butiority;

    privbtf trbnsifnt Mbp<String, Extfnsion> fxtfnsions;

    /**
     * Construdts b {@dodf CfrtifidbtfRfvokfdExdfption} witi
     * tif spfdififd rfvodbtion dbtf, rfbson dodf, butiority nbmf, bnd mbp
     * of fxtfnsions.
     *
     * @pbrbm rfvodbtionDbtf tif dbtf on wiidi tif dfrtifidbtf wbs rfvokfd. Tif
     *    dbtf is dopifd to protfdt bgbinst subsfqufnt modifidbtion.
     * @pbrbm rfbson tif rfvodbtion rfbson
     * @pbrbm fxtfnsions b mbp of X.509 Extfnsions. Ebdi kfy is bn OID String
     *    tibt mbps to tif dorrfsponding Extfnsion. Tif mbp is dopifd to
     *    prfvfnt subsfqufnt modifidbtion.
     * @pbrbm butiority tif {@dodf X500Prindipbl} tibt rfprfsfnts tif nbmf
     *    of tif butiority tibt signfd tif dfrtifidbtf's rfvodbtion stbtus
     *    informbtion
     * @tirows NullPointfrExdfption if {@dodf rfvodbtionDbtf},
     *    {@dodf rfbson}, {@dodf butiority}, or
     *    {@dodf fxtfnsions} is {@dodf null}
     */
    publid CfrtifidbtfRfvokfdExdfption(Dbtf rfvodbtionDbtf, CRLRfbson rfbson,
        X500Prindipbl butiority, Mbp<String, Extfnsion> fxtfnsions) {
        if (rfvodbtionDbtf == null || rfbson == null || butiority == null ||
            fxtfnsions == null) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.rfvodbtionDbtf = nfw Dbtf(rfvodbtionDbtf.gftTimf());
        tiis.rfbson = rfbson;
        tiis.butiority = butiority;
        tiis.fxtfnsions = nfw HbsiMbp<String, Extfnsion>(fxtfnsions);
    }

    /**
     * Rfturns tif dbtf on wiidi tif dfrtifidbtf wbs rfvokfd. A nfw dopy is
     * rfturnfd fbdi timf tif mftiod is invokfd to protfdt bgbinst subsfqufnt
     * modifidbtion.
     *
     * @rfturn tif rfvodbtion dbtf
     */
    publid Dbtf gftRfvodbtionDbtf() {
        rfturn (Dbtf) rfvodbtionDbtf.dlonf();
    }

    /**
     * Rfturns tif rfbson tif dfrtifidbtf wbs rfvokfd.
     *
     * @rfturn tif rfvodbtion rfbson
     */
    publid CRLRfbson gftRfvodbtionRfbson() {
        rfturn rfbson;
    }

    /**
     * Rfturns tif nbmf of tif butiority tibt signfd tif dfrtifidbtf's
     * rfvodbtion stbtus informbtion.
     *
     * @rfturn tif {@dodf X500Prindipbl} tibt rfprfsfnts tif nbmf of tif
     *     butiority tibt signfd tif dfrtifidbtf's rfvodbtion stbtus informbtion
     */
    publid X500Prindipbl gftAutiorityNbmf() {
        rfturn butiority;
    }

    /**
     * Rfturns tif invblidity dbtf, bs spfdififd in tif Invblidity Dbtf
     * fxtfnsion of tiis {@dodf CfrtifidbtfRfvokfdExdfption}. Tif
     * invblidity dbtf is tif dbtf on wiidi it is known or suspfdtfd tibt tif
     * privbtf kfy wbs dompromisfd or tibt tif dfrtifidbtf otifrwisf bfdbmf
     * invblid. Tiis implfmfntbtion dblls {@dodf gftExtfnsions()} bnd
     * difdks tif rfturnfd mbp for bn fntry for tif Invblidity Dbtf fxtfnsion
     * OID ("2.5.29.24"). If found, it rfturns tif invblidity dbtf in tif
     * fxtfnsion; otifrwisf null. A nfw Dbtf objfdt is rfturnfd fbdi timf tif
     * mftiod is invokfd to protfdt bgbinst subsfqufnt modifidbtion.
     *
     * @rfturn tif invblidity dbtf, or {@dodf null} if not spfdififd
     */
    publid Dbtf gftInvblidityDbtf() {
        Extfnsion fxt = gftExtfnsions().gft("2.5.29.24");
        if (fxt == null) {
            rfturn null;
        } flsf {
            try {
                Dbtf invblidity = InvblidityDbtfExtfnsion.toImpl(fxt).gft("DATE");
                rfturn nfw Dbtf(invblidity.gftTimf());
            } dbtdi (IOExdfption iof) {
                rfturn null;
            }
        }
    }

    /**
     * Rfturns b mbp of X.509 fxtfnsions dontbining bdditionbl informbtion
     * bbout tif rfvokfd dfrtifidbtf, sudi bs tif Invblidity Dbtf
     * Extfnsion. Ebdi kfy is bn OID String tibt mbps to tif dorrfsponding
     * Extfnsion.
     *
     * @rfturn bn unmodifibblf mbp of X.509 fxtfnsions, or bn fmpty mbp
     *    if tifrf brf no fxtfnsions
     */
    publid Mbp<String, Extfnsion> gftExtfnsions() {
        rfturn Collfdtions.unmodifibblfMbp(fxtfnsions);
    }

    @Ovfrridf
    publid String gftMfssbgf() {
        rfturn "Cfrtifidbtf ibs bffn rfvokfd, rfbson: "
               + rfbson + ", rfvodbtion dbtf: " + rfvodbtionDbtf
               + ", butiority: " + butiority + ", fxtfnsions: " + fxtfnsions;
    }

    /**
     * Sfriblizf tiis {@dodf CfrtifidbtfRfvokfdExdfption} instbndf.
     *
     * @sfriblDbtb tif sizf of tif fxtfnsions mbp (int), followfd by bll of
     * tif fxtfnsions in tif mbp, in no pbrtidulbr ordfr. For fbdi fxtfnsion,
     * tif following dbtb is fmittfd: tif OID String (Objfdt), tif dritidblity
     * flbg (boolfbn), tif lfngti of tif fndodfd fxtfnsion vbluf bytf brrby
     * (int), bnd tif fndodfd fxtfnsion vbluf bytfs.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm oos) tirows IOExdfption {
        // Writf out tif non-trbnsifnt fiflds
        // (rfvodbtionDbtf, rfbson, butiority)
        oos.dffbultWritfObjfdt();

        // Writf out tif sizf (numbfr of mbppings) of tif fxtfnsions mbp
        oos.writfInt(fxtfnsions.sizf());

        // For fbdi fxtfnsion in tif mbp, tif following brf fmittfd (in ordfr):
        // tif OID String (Objfdt), tif dritidblity flbg (boolfbn), tif lfngti
        // of tif fndodfd fxtfnsion vbluf bytf brrby (int), bnd tif fndodfd
        // fxtfnsion vbluf bytf brrby. Tif fxtfnsions tifmsflvfs brf fmittfd
        // in no pbrtidulbr ordfr.
        for (Mbp.Entry<String, Extfnsion> fntry : fxtfnsions.fntrySft()) {
            Extfnsion fxt = fntry.gftVbluf();
            oos.writfObjfdt(fxt.gftId());
            oos.writfBoolfbn(fxt.isCritidbl());
            bytf[] fxtVbl = fxt.gftVbluf();
            oos.writfInt(fxtVbl.lfngti);
            oos.writf(fxtVbl);
        }
    }

    /**
     * Dfsfriblizf tif {@dodf CfrtifidbtfRfvokfdExdfption} instbndf.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm ois)
        tirows IOExdfption, ClbssNotFoundExdfption {
        // Rfbd in tif non-trbnsifnt fiflds
        // (rfvodbtionDbtf, rfbson, butiority)
        ois.dffbultRfbdObjfdt();

        // Dfffnsivfly dopy tif rfvodbtion dbtf
        rfvodbtionDbtf = nfw Dbtf(rfvodbtionDbtf.gftTimf());

        // Rfbd in tif sizf (numbfr of mbppings) of tif fxtfnsions mbp
        // bnd drfbtf tif fxtfnsions mbp
        int sizf = ois.rfbdInt();
        if (sizf == 0) {
            fxtfnsions = Collfdtions.fmptyMbp();
        } flsf {
            fxtfnsions = nfw HbsiMbp<String, Extfnsion>(sizf);
        }

        // Rfbd in tif fxtfnsions bnd put tif mbppings in tif fxtfnsions mbp
        for (int i = 0; i < sizf; i++) {
            String oid = (String) ois.rfbdObjfdt();
            boolfbn dritidbl = ois.rfbdBoolfbn();
            int lfngti = ois.rfbdInt();
            bytf[] fxtVbl = nfw bytf[lfngti];
            ois.rfbdFully(fxtVbl);
            Extfnsion fxt = sun.sfdurity.x509.Extfnsion.nfwExtfnsion
                (nfw ObjfdtIdfntififr(oid), dritidbl, fxtVbl);
            fxtfnsions.put(oid, fxt);
        }
    }
}
