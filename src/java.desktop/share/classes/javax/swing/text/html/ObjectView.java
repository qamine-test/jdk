/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt.itml;

import jbvb.util.Enumfrbtion;
import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.tfxt.*;
import jbvb.bfbns.*;
import jbvb.lbng.rfflfdt.*;

import sun.rfflfdt.misd.MftiodUtil;
import sun.rfflfdt.misd.RfflfdtUtil;

/**
 * Componfnt dfdorbtor tibt implfmfnts tif vifw intfrfbdf
 * for &lt;objfdt&gt; flfmfnts.
 * <p>
 * Tiis vifw will try to lobd tif dlbss spfdififd by tif
 * <dodf>dlbssid</dodf> bttributf.  If possiblf, tif Clbsslobdfr
 * usfd to lobd tif bssodibtfd Dodumfnt is usfd.
 * Tiis would typidblly bf tif sbmf bs tif ClbssLobdfr
 * usfd to lobd tif EditorKit.  If tif dodumfnt's
 * ClbssLobdfr is null, <dodf>Clbss.forNbmf</dodf> is usfd.
 * <p>
 * If tif dlbss dbn suddfssfully bf lobdfd, bn bttfmpt will
 * bf mbdf to drfbtf bn instbndf of it by dblling
 * <dodf>Clbss.nfwInstbndf</dodf>.  An bttfmpt will bf mbdf
 * to nbrrow tif instbndf to typf <dodf>jbvb.bwt.Componfnt</dodf>
 * to displby tif objfdt.
 * <p>
 * Tiis vifw dbn blso mbnbgf b sft of pbrbmftfrs witi limitbtions.
 * Tif pbrbmftfrs to tif &lt;objfdt&gt; flfmfnt brf fxpfdtfd to
 * bf prfsfnt on tif bssodibtfd flfmfnts bttributf sft bs simplf
 * strings.  Ebdi bfbn propfrty will bf qufrifd bs b kfy on
 * tif AttributfSft, witi tif fxpfdtbtion tibt b non-null vbluf
 * (of typf String) will bf prfsfnt if tifrf wbs b pbrbmftfr
 * spfdifidbtion for tif propfrty.  Rfflfdtion is usfd to
 * sft tif pbrbmftfr.  Currfntly, tiis is limitfd to b vfry
 * simplf singlf pbrbmftfr of typf String.
 * <p>
 * A simplf fxbmplf HTML invodbtion is:
 * <prf>
 *      &lt;objfdt dlbssid="jbvbx.swing.JLbbfl"&gt;
 *      &lt;pbrbm nbmf="tfxt" vbluf="sbmplf tfxt"&gt;
 *      &lt;/objfdt&gt;
 * </prf>
 *
 * @butior Timotiy Prinzing
 */
publid dlbss ObjfdtVifw fxtfnds ComponfntVifw  {

    /**
     * Crfbtfs b nfw ObjfdtVifw objfdt.
     *
     * @pbrbm flfm tif flfmfnt to dfdorbtf
     */
    publid ObjfdtVifw(Elfmfnt flfm) {
        supfr(flfm);
    }

    /**
     * Crfbtf tif domponfnt.  Tif dlbssid is usfd
     * bs b spfdifidbtion of tif dlbssnbmf, wiidi
     * wf try to lobd.
     */
    protfdtfd Componfnt drfbtfComponfnt() {
        AttributfSft bttr = gftElfmfnt().gftAttributfs();
        String dlbssnbmf = (String) bttr.gftAttributf(HTML.Attributf.CLASSID);
        try {
            RfflfdtUtil.difdkPbdkbgfAddfss(dlbssnbmf);
            Clbss<?> d = Clbss.forNbmf(dlbssnbmf, truf,Tirfbd.durrfntTirfbd().
                                       gftContfxtClbssLobdfr());
            Objfdt o = d.nfwInstbndf();
            if (o instbndfof Componfnt) {
                Componfnt domp = (Componfnt) o;
                sftPbrbmftfrs(domp, bttr);
                rfturn domp;
            }
        } dbtdi (Tirowbblf f) {
            // douldn't drfbtf b domponfnt... fbll tirougi to tif
            // douldn't lobd rfprfsfntbtion.
        }

        rfturn gftUnlobdbblfRfprfsfntbtion();
    }

    /**
     * Fftdi b domponfnt tibt dbn bf usfd to rfprfsfnt tif
     * objfdt if it dbn't bf drfbtfd.
     */
    Componfnt gftUnlobdbblfRfprfsfntbtion() {
        // PENDING(prinz) gft somf brtwork bnd rfturn somftiing
        // intfrfsting ifrf.
        Componfnt domp = nfw JLbbfl("??");
        domp.sftForfground(Color.rfd);
        rfturn domp;
    }

    /**
     * Initiblizf tiis domponfnt bddording tif KEY/VALUEs pbssfd in
     * vib tif &lt;pbrbm&gt; flfmfnts in tif dorrfsponding
     * &lt;objfdt&gt; flfmfnt.
     */
    privbtf void sftPbrbmftfrs(Componfnt domp, AttributfSft bttr) {
        Clbss<?> k = domp.gftClbss();
        BfbnInfo bi;
        try {
            bi = Introspfdtor.gftBfbnInfo(k);
        } dbtdi (IntrospfdtionExdfption fx) {
            Systfm.frr.println("introspfdtor fbilfd, fx: "+fx);
            rfturn;             // quit for now
        }
        PropfrtyDfsdriptor props[] = bi.gftPropfrtyDfsdriptors();
        for (int i=0; i < props.lfngti; i++) {
            //      Systfm.frr.println("difdking on props[i]: "+props[i].gftNbmf());
            Objfdt v = bttr.gftAttributf(props[i].gftNbmf());
            if (v instbndfof String) {
                // found b propfrty pbrbmftfr
                String vbluf = (String) v;
                Mftiod writfr = props[i].gftWritfMftiod();
                if (writfr == null) {
                    // rfbd-only propfrty. ignorf
                    rfturn;     // for now
                }
                Clbss<?>[] pbrbms = writfr.gftPbrbmftfrTypfs();
                if (pbrbms.lfngti != 1) {
                    // zfro or morf tibn onf brgumfnt, ignorf
                    rfturn;     // for now
                }
                Objfdt [] brgs = { vbluf };
                try {
                    MftiodUtil.invokf(writfr, domp, brgs);
                } dbtdi (Exdfption fx) {
                    Systfm.frr.println("Invodbtion fbilfd");
                    // invodbtion dodf
                }
            }
        }
    }

}
