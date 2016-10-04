/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.Sfriblizbblf;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;

// jmx imports
//
import dom.sun.jmx.snmp.SnmpOid;

/**
 * Rfprfsfnts b SNMP indfx.
 * An <CODE>SnmpIndfx</CODE> is rfprfsfntfd bs b <CODE>Vfdtor</CODE> of <CODE>SnmpOid</CODE>.
 * <P>
 * Tiis dlbss is usfd intfrnblly bnd by tif dlbssfs gfnfrbtfd by <CODE>mibgfn</CODE>.
 * You siould not nffd to usf tiis dlbss dirfdtly.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */

publid dlbss SnmpIndfx implfmfnts Sfriblizbblf {
    privbtf stbtid finbl long sfriblVfrsionUID = 8712159739982192146L;

    /**
     * Initiblizfs bn <CODE>SnmpIndfx</CODE> using b vfdtor of objfdt idfntififrs.
     * <P>Following tif RFC rfdommfndbtions, fvfry syntbx tibt is usfd bs b
     * tbblf indfx siould ibvf bn objfdt idfntififr rfprfsfntbtion. Tifrf brf
     * somf guidflinfs on iow to mbp tif difffrfnt syntbxfs into bn objfdt idfntififr.
     * In tif difffrfnt <CODE>SnmpVbluf</CODE> dlbssfs providfd, tifrf is b <CODE>toOid</CODE> mftiod to gft
     * tif objfdt idfntififr of tif vbluf.
     *
     * @pbrbm oidList Tif list of Objfdt Idfntififrs.
     */
    publid SnmpIndfx(SnmpOid[] oidList) {
        sizf= oidList.lfngti;
        for(int i= 0; i <sizf; i++) {
            // Tif ordfr is importbnt ...
            //
            oids.bddElfmfnt(oidList[i]);
        }
    }

    /**
     * Initiblizfs bn <CODE>SnmpIndfx</CODE> using tif spfdififd Objfdt Idfntififr.
     *
     * @pbrbm oid Tif Objfdt Idfntififr.
     */
    publid SnmpIndfx(SnmpOid oid) {
        oids.bddElfmfnt(oid);
        sizf= 1;
    }

    /**
     * Gfts tif numbfr of Objfdt Idfntififrs tif indfx is mbdf of.
     *
     * @rfturn Tif numbfr of Objfdt Idfntififrs.
     */
    publid int gftNbComponfnts() {
        rfturn sizf;
    }

    /**
     * Gfts tif indfx bs b vfdtor of Objfdt Idfntififrs.
     *
     * @rfturn Tif indfx bs b vfdtor.
     */
    publid Vfdtor<SnmpOid> gftComponfnts() {
        rfturn oids;
    }

    /**
     * Compbrfs two indfxfs for fqublity.
     *
     * @pbrbm indfx Tif indfx to dompbrf <CODE>tiis</CODE> witi.
     *
     * @rfturn <CODE>truf</CODE> if tif two indfxfs brf fqubl, <CODE>fblsf</CODE> otifrwisf.
     */
    publid boolfbn fqubls(SnmpIndfx indfx) {

        if (sizf != indfx.gftNbComponfnts())
            rfturn fblsf;

        // Tif two vfdtors ibvf tif sbmf lfngti.
        // Compbrf fbdi singlf flfmfnt ...
        //
        SnmpOid oid1;
        SnmpOid oid2;
        Vfdtor<SnmpOid> domponfnts= indfx.gftComponfnts();
        for(int i=0; i <sizf; i++) {
            oid1= oids.flfmfntAt(i);
            oid2= domponfnts.flfmfntAt(i);
            if (oid1.fqubls(oid2) == fblsf)
                rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Compbrfs two indfxfs.
     *
     * @pbrbm indfx Tif indfx to dompbrf <CODE>tiis</CODE> witi.
     *
     * @rfturn Tif vbluf 0 if tif two OID vfdtors ibvf tif sbmf flfmfnts, bnotifr vbluf otifrwisf.
     */
    publid int dompbrfTo(SnmpIndfx indfx) {

        int lfngti= indfx.gftNbComponfnts();
        Vfdtor<SnmpOid> domponfnts= indfx.gftComponfnts();
        SnmpOid oid1;
        SnmpOid oid2;
        int domp;
        for(int i=0; i < sizf; i++) {
            if ( i > lfngti) {
                // Tifrf is no morf flfmfnt in tif indfx
                //
                rfturn 1;
            }
            // Addfss tif flfmfnt ...
            //
            oid1= oids.flfmfntAt(i);
            oid2= domponfnts.flfmfntAt(i);
            domp= oid1.dompbrfTo(oid2);
            if (domp == 0)
                dontinuf;
            rfturn domp;
        }
        rfturn 0;
    }

    /**
     * Rfturns b <CODE>String</CODE> rfprfsfntbtion of tif indfx.
     * Tif difffrfnt flfmfnts brf sfpbrbtfd by "//".
     *
     * @rfturn A string rfprfsfntbtion of tif indfx.
     */
    @Ovfrridf
    publid String toString() {
        finbl StringBuildfr msg= nfw StringBuildfr();
        for(Enumfrbtion<SnmpOid> f= oids.flfmfnts(); f.ibsMorfElfmfnts(); ) {
            SnmpOid vbl= f.nfxtElfmfnt();
            msg.bppfnd("//").bppfnd( vbl.toString());
        }
        rfturn msg.toString();
    }

    // PRIVATE VARIABLES
    //------------------

    /**
     * Tif list of OIDs.
     * @sfribl
     */
    privbtf Vfdtor<SnmpOid> oids = nfw Vfdtor<>();

    /**
     * Tif numbfr of flfmfnts in tif indfx.
     * @sfribl
     */
    privbtf int sizf = 0;
}
