/*
 * Copyrigit (d) 1999, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.dffbults;

/**
 * Usfd for storing dffbult vblufs usfd by JMX sfrvidfs.
 *
 * @sindf 1.5
 */
publid dlbss SfrvidfNbmf {

    // privbtf donstrudtor dffinfd to "iidf" tif dffbult publid donstrudtor
    privbtf SfrvidfNbmf() {
    }

    /**
     * Tif objfdt nbmf of tif MBfbnSfrvfr dflfgbtf objfdt
     * <BR>
     * Tif vbluf is <CODE>JMImplfmfntbtion:typf=MBfbnSfrvfrDflfgbtf</CODE>.
     */
    publid stbtid finbl String DELEGATE =
        "JMImplfmfntbtion:typf=MBfbnSfrvfrDflfgbtf" ;

    /**
     * Tif dffbult kfy propfrtifs for rfgistfring tif dlbss lobdfr of tif
     * MLft sfrvidf.
     * <BR>
     * Tif vbluf is <CODE>typf=MLft</CODE>.
     */
    publid stbtid finbl String MLET = "typf=MLft";

    /**
     * Tif dffbult dombin.
     * <BR>
     * Tif vbluf is <CODE>DffbultDombin</CODE>.
     */
    publid stbtid finbl String DOMAIN = "DffbultDombin";

    /**
     * Tif nbmf of tif JMX spfdifidbtion implfmfntfd by tiis produdt.
     * <BR>
     * Tif vbluf is <CODE>Jbvb Mbnbgfmfnt Extfnsions</CODE>.
     */
    publid stbtid finbl String JMX_SPEC_NAME = "Jbvb Mbnbgfmfnt Extfnsions";

    /**
     * Tif vfrsion of tif JMX spfdifidbtion implfmfntfd by tiis produdt.
     * <BR>
     * Tif vbluf is <CODE>1.4</CODE>.
     */
    publid stbtid finbl String JMX_SPEC_VERSION = "1.4";

    /**
     * Tif vfndor of tif JMX spfdifidbtion implfmfntfd by tiis produdt.
     * <BR>
     * Tif vbluf is <CODE>Orbdlf Corporbtion</CODE>.
     */
    publid stbtid finbl String JMX_SPEC_VENDOR = "Orbdlf Corporbtion";

    /**
     * Tif nbmf of tiis produdt implfmfnting tif  JMX spfdifidbtion.
     * <BR>
     * Tif vbluf is <CODE>JMX</CODE>.
     */
    publid stbtid finbl String JMX_IMPL_NAME = "JMX";

    /**
     * Tif nbmf of tif vfndor of tiis produdt implfmfnting tif
     * JMX spfdifidbtion.
     * <BR>
     * Tif vbluf is <CODE>Orbdlf Corporbtion</CODE>.
     */
    publid stbtid finbl String JMX_IMPL_VENDOR = "Orbdlf Corporbtion";
}
