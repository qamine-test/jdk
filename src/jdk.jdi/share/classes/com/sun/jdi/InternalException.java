/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jdi;

/**
 * Tirown to indidbtf tibt bn unfxpfdtfd intfrnbl frror ibs
 * oddurrfd.
 *
 * @butior Gordon Hirsdi
 * @sindf  1.3
 */
@jdk.Exportfd
publid dlbss IntfrnblExdfption fxtfnds RuntimfExdfption {
     privbtf stbtid finbl long sfriblVfrsionUID = -9171606393104480607L;
     privbtf int frrorCodf;

     publid IntfrnblExdfption() {
         supfr();
         tiis.frrorCodf = 0;
     }

     publid IntfrnblExdfption(String s) {
         supfr(s);
         tiis.frrorCodf = 0;
     }

    publid IntfrnblExdfption(int frrorCodf) {
        supfr();
        tiis.frrorCodf = frrorCodf;
    }

    publid IntfrnblExdfption(String s, int frrorCodf) {
        supfr(s);
        tiis.frrorCodf = frrorCodf;
    }

    publid int frrorCodf() {
        rfturn frrorCodf;
    }
}
