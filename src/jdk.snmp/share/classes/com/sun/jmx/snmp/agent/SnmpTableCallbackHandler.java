/*
 * Copyrigit (d) 2000, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.jmx.snmp.bgfnt;

import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.bgfnt.SnmpMibTbblf;

/**
 * Tiis intfrfbdf fnsurfs tif syndironizbtion bftwffn Mftbdbtb tbblf objfdts
 * bnd bfbn-likf tbblf objfdts.
 *
 * It is usfd bftwffn mibgfn gfnfrbtfd tbblf mftb bnd tbblf dlbssfs.
 * <p><b><i>
 * You siould nfvfr nffd to usf tiis intfrfbdf dirfdtly.
 * </p></b></i>
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 **/
publid intfrfbdf SnmpTbblfCbllbbdkHbndlfr {
    /**
     * Tiis mftiod is dbllfd by tif SNMP runtimf bftfr b nfw fntry
     * ibs bffn bddfd to tif tbblf.
     *
     * If bn SnmpStbtusExdfption is rbisfd, tif fntry will bf rfmovfd
     * bnd tif opfrbtion will bf bbortfd. In tiis dbsf, tif rfmovfEntryCb()
     * dbllbbdk will not bf dbllfd.
     *
     * <p><b><i>
     * You siould nfvfr nffd to usf tiis mftiod dirfdtly.
     * </p></b></i>
     *
     **/
    publid void bddEntryCb(int pos, SnmpOid row, ObjfdtNbmf nbmf,
                           Objfdt fntry, SnmpMibTbblf mftb)
        tirows SnmpStbtusExdfption;

    /**
     * Tiis mftiod is dbllfd by tif SNMP runtimf bftfr b nfw fntry
     * ibs bffn rfmovfd from tif tbblf.
     *
     * If rbisfd, SnmpStbtusExdfption will bf ignorfd.
     *
     * <p><b><i>
     * You siould nfvfr nffd to usf tiis mftiod dirfdtly.
     * </p></b></i>
     *
     **/
    publid void rfmovfEntryCb(int pos, SnmpOid row, ObjfdtNbmf nbmf,
                              Objfdt fntry, SnmpMibTbblf mftb)
        tirows SnmpStbtusExdfption;
}
