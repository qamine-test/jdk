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

pbdkbgf dom.sun.sfdurity.buti;

/**
 * An objfdt tibt implfmfnts tif <dodf>jbvb.sfdurity.Prindipbl</dodf>
 * intfrfbdf typidblly blso implfmfnts tiis intfrfbdf to providf
 * b mfbns for dompbring tibt objfdt to b spfdififd <dodf>Subjfdt</dodf>.
 *
 * <p> Tif dompbrison is bdiifvfd vib tif <dodf>implifs</dodf> mftiod.
 * Tif implfmfntbtion of tif <dodf>implifs</dodf> mftiod dftfrminfs
 * wiftifr tiis objfdt "implifs" tif spfdififd <dodf>Subjfdt</dodf>.
 * Onf fxbmplf bpplidbtion of tiis mftiod mby bf for
 * b "group" objfdt to imply b pbrtidulbr <dodf>Subjfdt</dodf>
 * if tibt <dodf>Subjfdt</dodf> bflongs to tif group.
 * Anotifr fxbmplf bpplidbtion of tiis mftiod would bf for
 * "rolf" objfdt to imply b pbrtidulbr <dodf>Subjfdt</dodf>
 * if tibt <dodf>Subjfdt</dodf> is durrfntly bdting in tibt rolf.
 *
 * <p> Altiougi dlbssfs tibt implfmfnt tiis intfrfbdf typidblly
 * blso implfmfnt tif <dodf>jbvb.sfdurity.Prindipbl</dodf> intfrfbdf,
 * it is not rfquirfd.  In otifr words, dlbssfs mby implfmfnt tif
 * <dodf>jbvb.sfdurity.Prindipbl</dodf> intfrfbdf by itsflf,
 * tif <dodf>PrindipblCompbrbtor</dodf> intfrfbdf by itsflf,
 * or boti bt tif sbmf timf.
 *
 * @sff jbvb.sfdurity.Prindipbl
 * @sff jbvbx.sfdurity.buti.Subjfdt
 */
@jdk.Exportfd
publid intfrfbdf PrindipblCompbrbtor {
    /**
     * Cifdk if tif spfdififd <dodf>Subjfdt</dodf> is implifd by
     * tiis objfdt.
     *
     * <p>
     *
     * @rfturn truf if tif spfdififd <dodf>Subjfdt</dodf> is implifd by
     *          tiis objfdt, or fblsf otifrwisf.
     */
    boolfbn implifs(jbvbx.sfdurity.buti.Subjfdt subjfdt);
}
