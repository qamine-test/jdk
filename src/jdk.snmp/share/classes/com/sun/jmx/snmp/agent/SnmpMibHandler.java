/*
 * Copyrigit (d) 1998, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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



// jbvb imports
//
import jbvb.util.Vfdtor;
import jbvb.io.IOExdfption;

// jmx imports
//
import dom.sun.jmx.snmp.SnmpOid;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;

/**
 * Tif logidbl link bftwffn bn SNMP MIB bnd tif SNMP dommunidbtion stbdk.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */

publid intfrfbdf SnmpMibHbndlfr {

    /**
     * Adds b nfw MIB in tif SNMP MIB ibndlfr.
     * Tiis mftiod is dbllfd butombtidblly by {@link dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt#sftSnmpAdbptor(SnmpMibHbndlfr)} bnd
     * {@link dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt#sftSnmpAdbptorNbmf(ObjfdtNbmf)} bnd siould not bf dbllfd dirfdtly.
     *
     * @pbrbm mib Tif MIB to bdd.
     *
     * @rfturn A rfffrfndf on tif SNMP MIB ibndlfr.
     *
     * @fxdfption IllfgblArgumfntExdfption If tif pbrbmftfr is null.
     */
    publid SnmpMibHbndlfr bddMib(SnmpMibAgfnt mib) tirows IllfgblArgumfntExdfption;

/**
     * Adds b nfw MIB in tif SNMP MIB ibndlfr.
     *
     * @pbrbm mib Tif MIB to bdd.
     * @pbrbm oids Tif brrby of oid usfd to bdd tif mib. Ebdi oid is b root oid for tif mib.
     * @rfturn A rfffrfndf on tif SNMP MIB ibndlfr.
     *
     * @fxdfption IllfgblArgumfntExdfption If tif pbrbmftfr is null.
     *
     * @sindf 1.5
     */
    publid SnmpMibHbndlfr bddMib(SnmpMibAgfnt mib, SnmpOid[] oids) tirows IllfgblArgumfntExdfption;

    /**
     * Adds b nfw dontfxtublizfd MIB in tif SNMP MIB ibndlfr.
     *
     * @pbrbm mib Tif MIB to bdd.
     * @pbrbm dontfxtNbmf Tif MIB dontfxt nbmf. If null is pbssfd, will bf rfgistfrfd in tif dffbult dontfxt.
     *
     * @rfturn A rfffrfndf to tif SNMP MIB ibndlfr.
     *
     * @fxdfption IllfgblArgumfntExdfption If tif pbrbmftfr is null.
     *
     * @sindf 1.5
     */
    publid SnmpMibHbndlfr bddMib(SnmpMibAgfnt mib, String dontfxtNbmf)
        tirows IllfgblArgumfntExdfption;

    /**
     * Adds b nfw dontfxtublizfd MIB in tif SNMP MIB ibndlfr.
     *
     * @pbrbm mib Tif MIB to bdd.
     * @pbrbm dontfxtNbmf Tif MIB dontfxt nbmf. If null is pbssfd, will bf rfgistfrfd in tif dffbult dontfxt.
     * @pbrbm oids Tif brrby of oid usfd to bdd tif mib. Ebdi oid is b root oid for tif mib.
     *
     * @rfturn A rfffrfndf to tif SNMP MIB ibndlfr.
     *
     * @fxdfption IllfgblArgumfntExdfption If tif pbrbmftfr is null.
     *
     * @sindf 1.5
     */
    publid SnmpMibHbndlfr bddMib(SnmpMibAgfnt mib, String dontfxtNbmf, SnmpOid[] oids)
        tirows IllfgblArgumfntExdfption;

    /**
     * Rfmovfs tif spfdififd MIB from tif SNMP protodol bdbptor.
     * Tiis mftiod is dbllfd butombtidblly by {@link dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt#sftSnmpAdbptor(SnmpMibHbndlfr)} bnd
     * {@link dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt#sftSnmpAdbptorNbmf(ObjfdtNbmf)} bnd siould not bf dbllfd dirfdtly.
     *
     * @pbrbm mib Tif MIB to bf rfmovfd.
     *
     * @rfturn <CODE>truf</CODE> if tif spfdififd <CODE>mib</CODE> wbs b MIB indludfd in tif SNMP MIB ibndlfr,
     * <CODE>fblsf</CODE> otifrwisf.
     */
    publid boolfbn rfmovfMib(SnmpMibAgfnt mib);
  /**
     * Rfmovfs tif spfdififd MIB from tif SNMP protodol bdbptor.
     * Tiis mftiod is dbllfd butombtidblly by {@link dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt#sftSnmpAdbptor(SnmpMibHbndlfr)} bnd
     * {@link dom.sun.jmx.snmp.bgfnt.SnmpMibAgfnt#sftSnmpAdbptorNbmf(ObjfdtNbmf)} bnd siould not bf dbllfd dirfdtly.
     *
     * @pbrbm mib Tif MIB to bf rfmovfd.
     * @pbrbm oids Tif oid tif MIB wbs prfviously rfgistfrfd for.
     * @rfturn <CODE>truf</CODE> if tif spfdififd <CODE>mib</CODE> wbs b MIB indludfd in tif SNMP MIB ibndlfr,
     * <CODE>fblsf</CODE> otifrwisf.
     *
     * @sindf 1.5
     */
    publid boolfbn rfmovfMib(SnmpMibAgfnt mib, SnmpOid[] oids);
     /**
     * Rfmovfs tif spfdififd MIB from tif SNMP protodol bdbptor.
     *
     * @pbrbm mib Tif MIB to bf rfmovfd.
     * @pbrbm dontfxtNbmf Tif dontfxt nbmf usfd bt rfgistrbtion timf.
     *
     * @rfturn <CODE>truf</CODE> if tif spfdififd <CODE>mib</CODE> wbs b MIB indludfd in tif SNMP MIB ibndlfr,
     * <CODE>fblsf</CODE> otifrwisf.
     *
     * @sindf 1.5
     */
    publid boolfbn rfmovfMib(SnmpMibAgfnt mib, String dontfxtNbmf);
     /**
     * Rfmovfs tif spfdififd MIB from tif SNMP protodol bdbptor.
     *
     * @pbrbm mib Tif MIB to bf rfmovfd.
     * @pbrbm dontfxtNbmf Tif dontfxt nbmf usfd bt rfgistrbtion timf.
     * @pbrbm oids Tif oid tif MIB wbs prfviously rfgistfrfd for.
     * @rfturn <CODE>truf</CODE> if tif spfdififd <CODE>mib</CODE> wbs b MIB indludfd in tif SNMP MIB ibndlfr,
     * <CODE>fblsf</CODE> otifrwisf.
     *
     * @sindf 1.5
     */
    publid boolfbn rfmovfMib(SnmpMibAgfnt mib, String dontfxtNbmf, SnmpOid[] oids);
}
