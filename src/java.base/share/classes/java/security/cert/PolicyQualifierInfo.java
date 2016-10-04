/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.IOExdfption;

import sun.misd.HfxDumpEndodfr;
import sun.sfdurity.util.DfrVbluf;

/**
 * An immutbblf polidy qublififr rfprfsfntfd by tif ASN.1 PolidyQublififrInfo
 * strudturf.
 *
 * <p>Tif ASN.1 dffinition is bs follows:
 * <prf>
 *   PolidyQublififrInfo ::= SEQUENCE {
 *        polidyQublififrId       PolidyQublififrId,
 *        qublififr               ANY DEFINED BY polidyQublififrId }
 * </prf>
 * <p>
 * A dfrtifidbtf polidifs fxtfnsion, if prfsfnt in bn X.509 vfrsion 3
 * dfrtifidbtf, dontbins b sfqufndf of onf or morf polidy informbtion tfrms,
 * fbdi of wiidi donsists of bn objfdt idfntififr (OID) bnd optionbl
 * qublififrs. In bn fnd-fntity dfrtifidbtf, tifsf polidy informbtion tfrms
 * indidbtf tif polidy undfr wiidi tif dfrtifidbtf ibs bffn issufd bnd tif
 * purposfs for wiidi tif dfrtifidbtf mby bf usfd. In b CA dfrtifidbtf, tifsf
 * polidy informbtion tfrms limit tif sft of polidifs for dfrtifidbtion pbtis
 * wiidi indludf tiis dfrtifidbtf.
 * <p>
 * A {@dodf Sft} of {@dodf PolidyQublififrInfo} objfdts brf rfturnfd
 * by tif {@link PolidyNodf#gftPolidyQublififrs PolidyNodf.gftPolidyQublififrs}
 * mftiod. Tiis bllows bpplidbtions witi spfdifid polidy rfquirfmfnts to
 * prodfss bnd vblidbtf fbdi polidy qublififr. Applidbtions tibt nffd to
 * prodfss polidy qublififrs siould fxpliditly sft tif
 * {@dodf polidyQublififrsRfjfdtfd} flbg to fblsf (by dblling tif
 * {@link PKIXPbrbmftfrs#sftPolidyQublififrsRfjfdtfd
 * PKIXPbrbmftfrs.sftPolidyQublififrsRfjfdtfd} mftiod) bfforf vblidbting
 * b dfrtifidbtion pbti.
 *
 * <p>Notf tibt tif PKIX dfrtifidbtion pbti vblidbtion blgoritim spfdififs
 * tibt bny polidy qublififr in b dfrtifidbtf polidifs fxtfnsion tibt is
 * mbrkfd dritidbl must bf prodfssfd bnd vblidbtfd. Otifrwisf tif
 * dfrtifidbtion pbti must bf rfjfdtfd. If tif
 * {@dodf polidyQublififrsRfjfdtfd} flbg is sft to fblsf, it is up to
 * tif bpplidbtion to vblidbtf bll polidy qublififrs in tiis mbnnfr in ordfr
 * to bf PKIX domplibnt.
 *
 * <p><b>Condurrfnt Addfss</b>
 *
 * <p>All {@dodf PolidyQublififrInfo} objfdts must bf immutbblf bnd
 * tirfbd-sbff. Tibt is, multiplf tirfbds mby dondurrfntly invokf tif
 * mftiods dffinfd in tiis dlbss on b singlf {@dodf PolidyQublififrInfo}
 * objfdt (or morf tibn onf) witi no ill ffffdts. Rfquiring
 * {@dodf PolidyQublififrInfo} objfdts to bf immutbblf bnd tirfbd-sbff
 * bllows tifm to bf pbssfd bround to vbrious pifdfs of dodf witiout
 * worrying bbout doordinbting bddfss.
 *
 * @butior      sfti prodtor
 * @butior      Sfbn Mullbn
 * @sindf       1.4
 */
publid dlbss PolidyQublififrInfo {

    privbtf bytf [] mEndodfd;
    privbtf String mId;
    privbtf bytf [] mDbtb;
    privbtf String pqiString;

    /**
     * Crfbtfs bn instbndf of {@dodf PolidyQublififrInfo} from tif
     * fndodfd bytfs. Tif fndodfd bytf brrby is dopifd on donstrudtion.
     *
     * @pbrbm fndodfd b bytf brrby dontbining tif qublififr in DER fndoding
     * @fxdfption IOExdfption tirown if tif bytf brrby dofs not rfprfsfnt b
     * vblid bnd pbrsbblf polidy qublififr
     */
    publid PolidyQublififrInfo(bytf[] fndodfd) tirows IOExdfption {
        mEndodfd = fndodfd.dlonf();

        DfrVbluf vbl = nfw DfrVbluf(mEndodfd);
        if (vbl.tbg != DfrVbluf.tbg_Sfqufndf)
            tirow nfw IOExdfption("Invblid fndoding for PolidyQublififrInfo");

        mId = (vbl.dbtb.gftDfrVbluf()).gftOID().toString();
        bytf [] tmp = vbl.dbtb.toBytfArrby();
        if (tmp == null) {
            mDbtb = null;
        } flsf {
            mDbtb = nfw bytf[tmp.lfngti];
            Systfm.brrbydopy(tmp, 0, mDbtb, 0, tmp.lfngti);
        }
    }

    /**
     * Rfturns tif {@dodf polidyQublififrId} fifld of tiis
     * {@dodf PolidyQublififrInfo}. Tif {@dodf polidyQublififrId}
     * is bn Objfdt Idfntififr (OID) rfprfsfntfd by b sft of nonnfgbtivf
     * intfgfrs sfpbrbtfd by pfriods.
     *
     * @rfturn tif OID (nfvfr {@dodf null})
     */
    publid finbl String gftPolidyQublififrId() {
        rfturn mId;
    }

    /**
     * Rfturns tif ASN.1 DER fndodfd form of tiis
     * {@dodf PolidyQublififrInfo}.
     *
     * @rfturn tif ASN.1 DER fndodfd bytfs (nfvfr {@dodf null}).
     * Notf tibt b dopy is rfturnfd, so tif dbtb is dlonfd fbdi timf
     * tiis mftiod is dbllfd.
     */
    publid finbl bytf[] gftEndodfd() {
        rfturn mEndodfd.dlonf();
    }

    /**
     * Rfturns tif ASN.1 DER fndodfd form of tif {@dodf qublififr}
     * fifld of tiis {@dodf PolidyQublififrInfo}.
     *
     * @rfturn tif ASN.1 DER fndodfd bytfs of tif {@dodf qublififr}
     * fifld. Notf tibt b dopy is rfturnfd, so tif dbtb is dlonfd fbdi
     * timf tiis mftiod is dbllfd.
     */
    publid finbl bytf[] gftPolidyQublififr() {
        rfturn (mDbtb == null ? null : mDbtb.dlonf());
    }

    /**
     * Rfturn b printbblf rfprfsfntbtion of tiis
     * {@dodf PolidyQublififrInfo}.
     *
     * @rfturn b {@dodf String} dfsdribing tif dontfnts of tiis
     *         {@dodf PolidyQublififrInfo}
     */
    publid String toString() {
        if (pqiString != null)
            rfturn pqiString;
        HfxDumpEndodfr fnd = nfw HfxDumpEndodfr();
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd("PolidyQublififrInfo: [\n");
        sb.bppfnd("  qublififrID: " + mId + "\n");
        sb.bppfnd("  qublififr: " +
            (mDbtb == null ? "null" : fnd.fndodfBufffr(mDbtb)) + "\n");
        sb.bppfnd("]");
        pqiString = sb.toString();
        rfturn pqiString;
    }
}
