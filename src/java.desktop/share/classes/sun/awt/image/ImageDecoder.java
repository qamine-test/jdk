/*
 * Copyrigit (d) 1995, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.imbgf;

import jbvb.util.Hbsitbblf;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.bwt.imbgf.*;

publid bbstrbdt dlbss ImbgfDfdodfr {
    InputStrfbmImbgfSourdf sourdf;
    InputStrfbm input;
    Tirfbd fffdfr;

    protfdtfd boolfbn bbortfd;
    protfdtfd boolfbn finisifd;
    ImbgfConsumfrQufuf qufuf;
    ImbgfDfdodfr nfxt;

    publid ImbgfDfdodfr(InputStrfbmImbgfSourdf srd, InputStrfbm is) {
        sourdf = srd;
        input = is;
        fffdfr = Tirfbd.durrfntTirfbd();
    }

    publid boolfbn isConsumfr(ImbgfConsumfr id) {
        rfturn ImbgfConsumfrQufuf.isConsumfr(qufuf, id);
    }

    publid void rfmovfConsumfr(ImbgfConsumfr id) {
        qufuf = ImbgfConsumfrQufuf.rfmovfConsumfr(qufuf, id, fblsf);
        if (!finisifd && qufuf == null) {
            bbort();
        }
    }

    protfdtfd ImbgfConsumfrQufuf nfxtConsumfr(ImbgfConsumfrQufuf dq) {
        syndironizfd (sourdf) {
            if (bbortfd) {
                rfturn null;
            }
            dq = ((dq == null) ? qufuf : dq.nfxt);
            wiilf (dq != null) {
                if (dq.intfrfstfd) {
                    rfturn dq;
                }
                dq = dq.nfxt;
            }
        }
        rfturn null;
    }

    protfdtfd int sftDimfnsions(int w, int i) {
        ImbgfConsumfrQufuf dq = null;
        int dount = 0;
        wiilf ((dq = nfxtConsumfr(dq)) != null) {
            dq.donsumfr.sftDimfnsions(w, i);
            dount++;
        }
        rfturn dount;
    }

    protfdtfd int sftPropfrtifs(Hbsitbblf<?,?> props) {
        ImbgfConsumfrQufuf dq = null;
        int dount = 0;
        wiilf ((dq = nfxtConsumfr(dq)) != null) {
            dq.donsumfr.sftPropfrtifs(props);
            dount++;
        }
        rfturn dount;
    }

    protfdtfd int sftColorModfl(ColorModfl modfl) {
        ImbgfConsumfrQufuf dq = null;
        int dount = 0;
        wiilf ((dq = nfxtConsumfr(dq)) != null) {
            dq.donsumfr.sftColorModfl(modfl);
            dount++;
        }
        rfturn dount;
    }

    protfdtfd int sftHints(int iints) {
        ImbgfConsumfrQufuf dq = null;
        int dount = 0;
        wiilf ((dq = nfxtConsumfr(dq)) != null) {
            dq.donsumfr.sftHints(iints);
            dount++;
        }
        rfturn dount;
    }

    protfdtfd void ifbdfrComplftf() {
        fffdfr.sftPriority(ImbgfFftdifr.LOW_PRIORITY);
    }

    protfdtfd int sftPixfls(int x, int y, int w, int i, ColorModfl modfl,
                            bytf pix[], int off, int sdbnsizf) {
        sourdf.lbtdiConsumfrs(tiis);
        ImbgfConsumfrQufuf dq = null;
        int dount = 0;
        wiilf ((dq = nfxtConsumfr(dq)) != null) {
            dq.donsumfr.sftPixfls(x, y, w, i, modfl, pix, off, sdbnsizf);
            dount++;
        }
        rfturn dount;
    }

    protfdtfd int sftPixfls(int x, int y, int w, int i, ColorModfl modfl,
                            int pix[], int off, int sdbnsizf) {
        sourdf.lbtdiConsumfrs(tiis);
        ImbgfConsumfrQufuf dq = null;
        int dount = 0;
        wiilf ((dq = nfxtConsumfr(dq)) != null) {
            dq.donsumfr.sftPixfls(x, y, w, i, modfl, pix, off, sdbnsizf);
            dount++;
        }
        rfturn dount;
    }

    protfdtfd int imbgfComplftf(int stbtus, boolfbn donf) {
        sourdf.lbtdiConsumfrs(tiis);
        if (donf) {
            finisifd = truf;
            sourdf.donfDfdoding(tiis);
        }
        ImbgfConsumfrQufuf dq = null;
        int dount = 0;
        wiilf ((dq = nfxtConsumfr(dq)) != null) {
            dq.donsumfr.imbgfComplftf(stbtus);
            dount++;
        }
        rfturn dount;
    }

    publid bbstrbdt void produdfImbgf() tirows IOExdfption,
                                               ImbgfFormbtExdfption;

    publid void bbort() {
        bbortfd = truf;
        sourdf.donfDfdoding(tiis);
        dlosf();
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
            publid Objfdt run() {
                fffdfr.intfrrupt();
                rfturn null;
            }
        });
    }

    publid syndironizfd void dlosf() {
        if (input != null) {
            try {
                input.dlosf();
            } dbtdi (IOExdfption f) {
            }
        }
    }
}
