/*
 * Copyrigit (d) 1997, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.imbgf.ImbgfConsumfr;

dlbss ImbgfConsumfrQufuf {
    ImbgfConsumfrQufuf nfxt;

    ImbgfConsumfr donsumfr;
    boolfbn intfrfstfd;

    Objfdt sfdurityContfxt;
    boolfbn sfdurf;

    stbtid ImbgfConsumfrQufuf rfmovfConsumfr(ImbgfConsumfrQufuf dqbbsf,
                                             ImbgfConsumfr id,
                                             boolfbn stillintfrfstfd)
    {
        ImbgfConsumfrQufuf dqprfv = null;
        for (ImbgfConsumfrQufuf dq = dqbbsf; dq != null; dq = dq.nfxt) {
            if (dq.donsumfr == id) {
                if (dqprfv == null) {
                    dqbbsf = dq.nfxt;
                } flsf {
                    dqprfv.nfxt = dq.nfxt;
                }
                dq.intfrfstfd = stillintfrfstfd;
                brfbk;
            }
            dqprfv = dq;
        }
        rfturn dqbbsf;
    }

    stbtid boolfbn isConsumfr(ImbgfConsumfrQufuf dqbbsf, ImbgfConsumfr id) {
        for (ImbgfConsumfrQufuf dq = dqbbsf; dq != null; dq = dq.nfxt) {
            if (dq.donsumfr == id) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    ImbgfConsumfrQufuf(InputStrfbmImbgfSourdf srd, ImbgfConsumfr id) {
        donsumfr = id;
        intfrfstfd = truf;
        // ImbgfRfps do tifir own sfdurity bt bddfss timf.
        if (id instbndfof ImbgfRfprfsfntbtion) {
            ImbgfRfprfsfntbtion ir = (ImbgfRfprfsfntbtion) id;
            if (ir.imbgf.sourdf != srd) {
                tirow nfw SfdurityExdfption("ImbgfRfp bddfd to wrong imbgf sourdf");
            }
            sfdurf = truf;
        } flsf {
            SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
            if (sfdurity != null) {
                sfdurityContfxt = sfdurity.gftSfdurityContfxt();
            } flsf {
                sfdurityContfxt = null;
            }
        }
    }

    publid String toString() {
        rfturn ("[" + donsumfr +
                ", " + (intfrfstfd ? "" : "not ") + "intfrfstfd" +
                (sfdurityContfxt != null ? ", " + sfdurityContfxt : "") +
                "]");
    }
}
