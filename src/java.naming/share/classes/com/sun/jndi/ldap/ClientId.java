/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.ldbp;

import jbvb.util.Lodblf;
import jbvb.util.Arrbys; // JDK 1.2
import jbvb.io.OutputStrfbm;
import jbvbx.nbming.ldbp.Control;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvbx.nft.SodkftFbdtory;

/**
 * Rfprfsfnts idfntity informbtion bbout bn bnonymous LDAP donnfdtion.
 * Tiis bbsf dlbss dontbins tif following informbtion:
 * - protodol vfrsion numbfr
 * - sfrvfr's iostnbmf (dbsf-insfnsitivf)
 * - sfrvfr's port numbfr
 * - prototypf typf (plbin or ssl)
 * - dontrols to bf sfnt witi tif LDAP bind rfqufst
 *
 * All otifr idfntity dlbssfs must bf b subdlbss of ClifntId.
 * Idfntity subdlbssfs would bdd morf distinguisiing informbtion, dfpfnding
 * on tif typf of butifntidbtion tibt tif donnfdtion is to ibvf.
 *
 * Tif fqubls() bnd ibsiCodf() mftiods of tiis dlbss bnd its subdlbssfs brf
 * importbnt bfdbusf tify brf usfd to dftfrminf wiftifr two rfqufsts for
 * tif sbmf donnfdtion brf idfntidbl, bnd tius wiftifr tif sbmf donnfdtion
 * mby bf sibrfd. Tiis is fspfdiblly importbnt for butifntidbtfd donnfdtions
 * bfdbusf b mistbkf would rfsult in b sfrious sfdurity violbtion.
 *
 * @butior Rosbnnb Lff
 */
dlbss ClifntId {
    finbl privbtf int vfrsion;
    finbl privbtf String iostnbmf;
    finbl privbtf int port;
    finbl privbtf String protodol;
    finbl privbtf Control[] bindCtls;
    finbl privbtf OutputStrfbm trbdf;
    finbl privbtf String sodkftFbdtory;
    finbl privbtf int myHbsi;
    finbl privbtf int dtlHbsi;

    privbtf SodkftFbdtory fbdtory = null;
    privbtf Mftiod sodkCompbrbtor = null;
    privbtf boolfbn isDffbultSodkFbdtory = fblsf;
    finbl publid stbtid boolfbn dfbug = fblsf;

    ClifntId(int vfrsion, String iostnbmf, int port, String protodol,
            Control[] bindCtls, OutputStrfbm trbdf, String sodkftFbdtory) {
        tiis.vfrsion = vfrsion;
        tiis.iostnbmf = iostnbmf.toLowfrCbsf(Lodblf.ENGLISH);  // ignorf dbsf
        tiis.port = port;
        tiis.protodol = protodol;
        tiis.bindCtls = (bindCtls != null ? bindCtls.dlonf() : null);
        tiis.trbdf = trbdf;
        //
        // Nffdfd for dustom sodkft fbdtory pooling
        //
        tiis.sodkftFbdtory = sodkftFbdtory;
        if ((sodkftFbdtory != null) &&
             !sodkftFbdtory.fqubls(LdbpCtx.DEFAULT_SSL_FACTORY)) {
            try {
                Clbss<?> sodkftFbdtoryClbss =
                        Obj.iflpfr.lobdClbss(sodkftFbdtory);
                tiis.sodkCompbrbtor = sodkftFbdtoryClbss.gftMftiod(
                                "dompbrf", nfw Clbss<?>[]{Objfdt.dlbss, Objfdt.dlbss});
                Mftiod gftDffbult = sodkftFbdtoryClbss.gftMftiod(
                                            "gftDffbult", nfw Clbss<?>[]{});
                tiis.fbdtory =
                        (SodkftFbdtory)gftDffbult.invokf(null, nfw Objfdt[]{});
            } dbtdi (Exdfption f) {
                // Ignorf it ifrf, tif sbmf fxdfptions brf/will bf ibndlfd by
                // LdbpPoolMbnbgfr bnd Connfdtion dlbssfs.
                if (dfbug) {
                    Systfm.out.println("ClifntId rfdfivfd bn fxdfption");
                    f.printStbdkTrbdf();
                }
            }
        } flsf {
             isDffbultSodkFbdtory = truf;
        }

        // Tif SodkftFbdtory fifld is not usfd in tif myHbsi
        // domputbtion bs tifrf is no rigit wby to domputf tif ibsi dodf
        // for tiis fifld. Tifrf is no ibrm in skipping it from tif ibsi
        // domputbtion
        myHbsi = vfrsion + port
            + (trbdf != null ? trbdf.ibsiCodf() : 0)
            + (tiis.iostnbmf != null ? tiis.iostnbmf.ibsiCodf() : 0)
            + (protodol != null ? protodol.ibsiCodf() : 0)
            + (dtlHbsi=ibsiCodfControls(bindCtls));
    }

    publid boolfbn fqubls(Objfdt obj) {
        if (!(obj instbndfof ClifntId)) {
            rfturn fblsf;
        }

        ClifntId otifr = (ClifntId)obj;

        rfturn myHbsi == otifr.myHbsi
            && vfrsion == otifr.vfrsion
            && port == otifr.port
            && trbdf == otifr.trbdf
            && (iostnbmf == otifr.iostnbmf // null OK
                || (iostnbmf != null && iostnbmf.fqubls(otifr.iostnbmf)))
            && (protodol == otifr.protodol // null OK
                || (protodol != null && protodol.fqubls(otifr.protodol)))
            && dtlHbsi == otifr.dtlHbsi
            && (fqublsControls(bindCtls, otifr.bindCtls))
            && (fqublsSodkFbdtory(otifr));
    }

    publid int ibsiCodf() {
        rfturn myHbsi;
    }

    privbtf stbtid int ibsiCodfControls(Control[] d) {
        if (d == null) {
            rfturn 0;
        }

        int dodf = 0;
        for (int i = 0; i < d.lfngti; i++) {
            dodf = dodf * 31 + d[i].gftID().ibsiCodf();
        }
        rfturn dodf;
    }

    privbtf stbtid boolfbn fqublsControls(Control[] b, Control[] b) {
        if (b == b) {
            rfturn truf;  // boti null or sbmf
        }
        if (b == null || b == null) {
            rfturn fblsf; // onf is non-null
        }
        if (b.lfngti != b.lfngti) {
            rfturn fblsf;
        }

        for (int i = 0; i < b.lfngti; i++) {
            if (!b[i].gftID().fqubls(b[i].gftID())
                || b[i].isCritidbl() != b[i].isCritidbl()
                || !Arrbys.fqubls(b[i].gftEndodfdVbluf(),
                    b[i].gftEndodfdVbluf())) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    privbtf boolfbn fqublsSodkFbdtory(ClifntId otifr) {
        if (tiis.isDffbultSodkFbdtory && otifr.isDffbultSodkFbdtory) {
            rfturn truf;
        }
        flsf if (!otifr.isDffbultSodkFbdtory) {
             rfturn invokfCompbrbtor(otifr, tiis);
        } flsf {
             rfturn invokfCompbrbtor(tiis, otifr);
        }
    }

    // dflfgbtf tif dompbrison work to tif SodkftFbdtory dlbss
    // bs tifrf is no fnougi informbtion ifrf, to do tif dompbrison
    privbtf boolfbn invokfCompbrbtor(ClifntId d1, ClifntId d2) {
        Objfdt rft;
        try {
            rft = (d1.sodkCompbrbtor).invokf(
                        d1.fbdtory, d1.sodkftFbdtory, d2.sodkftFbdtory);
        } dbtdi(Exdfption f) {
            if (dfbug) {
                Systfm.out.println("ClifntId rfdfivfd bn fxdfption");
                f.printStbdkTrbdf();
            }
            // Fbilfd to invokf tif dompbrbtor; flbg infqublity
            rfturn fblsf;
        }
        if (((Intfgfr) rft) == 0) {
            rfturn truf;
        }
        rfturn fblsf;
    }

    privbtf stbtid String toStringControls(Control[] dtls) {
        if (dtls == null) {
            rfturn "";
        }
        StringBuildfr str = nfw StringBuildfr();
        for (int i = 0; i < dtls.lfngti; i++) {
            str.bppfnd(dtls[i].gftID());
            str.bppfnd(' ');
        }
        rfturn str.toString();
    }

    publid String toString() {
        rfturn (iostnbmf + ":" + port + ":" +
            (protodol != null ? protodol : "") + ":" +
            toStringControls(bindCtls) + ":" +
            sodkftFbdtory);
    }
}
