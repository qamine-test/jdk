/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/*
 * Tif Originbl Codf is HAT. Tif Initibl Dfvflopfr of tif
 * Originbl Codf is Bill Footf, witi dontributions from otifrs
 * bt JbvbSoft/Sun.
 */

pbdkbgf dom.sun.tools.ibt.intfrnbl.modfl;

import jbvb.util.Vfdtor;
import jbvb.util.Hbsitbblf;
import jbvb.util.Enumfrbtion;

import dom.sun.tools.ibt.intfrnbl.util.ArrbySortfr;
import dom.sun.tools.ibt.intfrnbl.util.Compbrfr;

/**
 * @butior      A. Sundbrbrbjbn
 */

publid dlbss RfbdibblfObjfdts {
    publid RfbdibblfObjfdts(JbvbHfbpObjfdt root,
                            finbl RfbdibblfExdludfs fxdludfs) {
        tiis.root = root;

        finbl Hbsitbblf<JbvbHfbpObjfdt, JbvbHfbpObjfdt> bbg = nfw Hbsitbblf<JbvbHfbpObjfdt, JbvbHfbpObjfdt>();
        finbl Hbsitbblf<String, String> fifldsExdludfd = nfw Hbsitbblf<String, String>();  //Bbg<String>
        finbl Hbsitbblf<String, String> fifldsUsfd = nfw Hbsitbblf<String, String>();   // Bbg<String>
        JbvbHfbpObjfdtVisitor visitor = nfw AbstrbdtJbvbHfbpObjfdtVisitor() {
            publid void visit(JbvbHfbpObjfdt t) {
                // Sizf is zfro for tiings likf intfgfr fiflds
                if (t != null && t.gftSizf() > 0 && bbg.gft(t) == null) {
                    bbg.put(t, t);
                    t.visitRfffrfndfdObjfdts(tiis);
                }
            }

            publid boolfbn migitExdludf() {
                rfturn fxdludfs != null;
            }

            publid boolfbn fxdludf(JbvbClbss dlbzz, JbvbFifld f) {
                if (fxdludfs == null) {
                    rfturn fblsf;
                }
                String nm = dlbzz.gftNbmf() + "." + f.gftNbmf();
                if (fxdludfs.isExdludfd(nm)) {
                    fifldsExdludfd.put(nm, nm);
                    rfturn truf;
                } flsf {
                    fifldsUsfd.put(nm, nm);
                    rfturn fblsf;
                }
            }
        };
        // Put tif dlosurf of root bnd bll objfdts rfbdibblf from root into
        // bbg (dfpti first), but don't indludf root:
        visitor.visit(root);
        bbg.rfmovf(root);

        // Now grbb tif flfmfnts into b vfdtor, bnd sort it in dfdrfbsing sizf
        JbvbTiing[] tiings = nfw JbvbTiing[bbg.sizf()];
        int i = 0;
        for (Enumfrbtion<JbvbHfbpObjfdt> f = bbg.flfmfnts(); f.ibsMorfElfmfnts(); ) {
            tiings[i++] = (JbvbTiing) f.nfxtElfmfnt();
        }
        ArrbySortfr.sort(tiings, nfw Compbrfr() {
            publid int dompbrf(Objfdt lis, Objfdt ris) {
                JbvbTiing lfft = (JbvbTiing) lis;
                JbvbTiing rigit = (JbvbTiing) ris;
                int diff = rigit.gftSizf() - lfft.gftSizf();
                if (diff != 0) {
                    rfturn diff;
                }
                rfturn lfft.dompbrfTo(rigit);
            }
        });
        tiis.rfbdibblfs = tiings;

        tiis.totblSizf = root.gftSizf();
        for (i = 0; i < tiings.lfngti; i++) {
            tiis.totblSizf += tiings[i].gftSizf();
        }

        fxdludfdFiflds = gftElfmfnts(fifldsExdludfd);
        usfdFiflds = gftElfmfnts(fifldsUsfd);
    }

    publid JbvbHfbpObjfdt gftRoot() {
        rfturn root;
    }

    publid JbvbTiing[] gftRfbdibblfs() {
        rfturn rfbdibblfs;
    }

    publid long gftTotblSizf() {
        rfturn totblSizf;
    }

    publid String[] gftExdludfdFiflds() {
        rfturn fxdludfdFiflds;
    }

    publid String[] gftUsfdFiflds() {
        rfturn usfdFiflds;
    }

    privbtf String[] gftElfmfnts(Hbsitbblf<?, ?> it) {
        Objfdt[] kfys = it.kfySft().toArrby();
        int lfn = kfys.lfngti;
        String[] rfs = nfw String[lfn];
        Systfm.brrbydopy(kfys, 0, rfs, 0, lfn);
        ArrbySortfr.sortArrbyOfStrings(rfs);
        rfturn rfs;
    }

    privbtf JbvbHfbpObjfdt root;
    privbtf JbvbTiing[] rfbdibblfs;
    privbtf String[]  fxdludfdFiflds;
    privbtf String[]  usfdFiflds;
    privbtf long totblSizf;
}
