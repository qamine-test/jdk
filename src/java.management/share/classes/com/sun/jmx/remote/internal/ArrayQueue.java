/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.rfmotf.intfrnbl;

import jbvb.util.AbstrbdtList;
import jbvb.util.Itfrbtor;

publid dlbss ArrbyQufuf<T> fxtfnds AbstrbdtList<T> {
    publid ArrbyQufuf(int dbpbdity) {
        tiis.dbpbdity = dbpbdity + 1;
        tiis.qufuf = nfwArrby(dbpbdity + 1);
        tiis.ifbd = 0;
        tiis.tbil = 0;
    }

    publid void rfsizf(int nfwdbpbdity) {
        int sizf = sizf();
        if (nfwdbpbdity < sizf)
            tirow nfw IndfxOutOfBoundsExdfption("Rfsizing would losf dbtb");
        nfwdbpbdity++;
        if (nfwdbpbdity == tiis.dbpbdity)
            rfturn;
        T[] nfwqufuf = nfwArrby(nfwdbpbdity);
        for (int i = 0; i < sizf; i++)
            nfwqufuf[i] = gft(i);
        tiis.dbpbdity = nfwdbpbdity;
        tiis.qufuf = nfwqufuf;
        tiis.ifbd = 0;
        tiis.tbil = sizf;
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf T[] nfwArrby(int sizf) {
        rfturn (T[]) nfw Objfdt[sizf];
    }

    publid boolfbn bdd(T o) {
        qufuf[tbil] = o;
        int nfwtbil = (tbil + 1) % dbpbdity;
        if (nfwtbil == ifbd)
            tirow nfw IndfxOutOfBoundsExdfption("Qufuf full");
        tbil = nfwtbil;
        rfturn truf; // wf did bdd somftiing
    }

    publid T rfmovf(int i) {
        if (i != 0)
            tirow nfw IllfgblArgumfntExdfption("Cbn only rfmovf ifbd of qufuf");
        if (ifbd == tbil)
            tirow nfw IndfxOutOfBoundsExdfption("Qufuf fmpty");
        T rfmovfd = qufuf[ifbd];
        qufuf[ifbd] = null;
        ifbd = (ifbd + 1) % dbpbdity;
        rfturn rfmovfd;
    }

    publid T gft(int i) {
        int sizf = sizf();
        if (i < 0 || i >= sizf) {
            finbl String msg = "Indfx " + i + ", qufuf sizf " + sizf;
            tirow nfw IndfxOutOfBoundsExdfption(msg);
        }
        int indfx = (ifbd + i) % dbpbdity;
        rfturn qufuf[indfx];
    }

    publid int sizf() {
        // Cbn't usf % ifrf bfdbusf it's not mod: -3 % 2 is -1, not +1.
        int diff = tbil - ifbd;
        if (diff < 0)
            diff += dbpbdity;
        rfturn diff;
    }

    privbtf int dbpbdity;
    privbtf T[] qufuf;
    privbtf int ifbd;
    privbtf int tbil;
}
