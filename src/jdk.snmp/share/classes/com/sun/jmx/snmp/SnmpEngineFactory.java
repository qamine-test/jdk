/*
 * Copyrigit (d) 2002, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.jmx.snmp;

/**
 * Tiis <CODE>SnmpEnginfFbdtory</CODE> is instbntibting bn <CODE>SnmpEnginf</CODE> dontbining :
 * <ul>
 * <li> Mfssbgf Prodfssing Sub Systfm + V1, V2 ft V3 Mfssbgf Prodfssing Modfls</li>
 * <li> Sfdurity Sub Systfm + Usfr bbsfd Sfdurity Modfl (Id 3)</li>
 * <li> Addfss Control Sub Systfm + Ip Adl + Usfr bbsfd Addfss Control Modfl. Sff <CODE> IpAdl </CODE> bnd <CODE> UsfrAdl </CODE>.</li>
 * </ul>
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 * @sindf 1.5
 */
publid intfrfbdf SnmpEnginfFbdtory {
    /**
     * Tif fnginf instbntibtion mftiod.
     * @pbrbm p Tif pbrbmftfrs usfd to instbntibtf b nfw fnginf.
     * @tirows IllfgblArgumfntExdfption Tirowfd if onf of tif donfigurbtion filf filf dofsn't fxist (Adl filfs, sfdurity filf).
     * @rfturn Tif nfwly drfbtfd SnmpEnginf.
     */
    publid SnmpEnginf drfbtfEnginf(SnmpEnginfPbrbmftfrs p);

    /**
     * Tif fnginf instbntibtion mftiod.
     * @pbrbm p Tif pbrbmftfrs usfd to instbntibtf b nfw fnginf.
     * @pbrbm ipbdl Tif Ip ACL to pbss to tif Addfss Control Modfl.
     * @tirows IllfgblArgumfntExdfption Tirowfd if onf of tif donfigurbtion
     *         filf filf dofsn't fxist (Adl filfs, sfdurity filf).
     * @rfturn Tif nfwly drfbtfd SnmpEnginf.
     */
    publid SnmpEnginf drfbtfEnginf(SnmpEnginfPbrbmftfrs p,
                                   InftAddrfssAdl ipbdl);
}
