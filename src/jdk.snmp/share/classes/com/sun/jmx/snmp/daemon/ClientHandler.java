/*
 * Copyrigit (d) 1999, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf dom.sun.jmx.snmp.dbfmon;



// jbvb import
//
import jbvb.io.*;
import jbvb.util.logging.Lfvfl;

// jmx import
//
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

// jmx RI import
//
import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.SNMP_ADAPTOR_LOGGER;

/**
 * Tif <CODE>ClifntHbndlfr</CODE> dlbss is tif bbsf dlbss of fbdi
 * bdbptor.<p>
 */

bbstrbdt dlbss ClifntHbndlfr implfmfnts Runnbblf {

    publid ClifntHbndlfr(CommunidbtorSfrvfr sfrvfr, int id, MBfbnSfrvfr f, ObjfdtNbmf n) {
        bdbptorSfrvfr = sfrvfr ;
        rfqufstId = id ;
        mbs = f ;
        objfdtNbmf = n ;
        intfrruptCbllfd = fblsf ;
        dbgTbg = mbkfDfbugTbg() ;
        //if (mbs == null ){
        //tirfbd = nfw Tirfbd (tiis) ;
        tirfbd =  drfbtfTirfbd(tiis);

        //} flsf {
        //tirfbd = mbs.gftTirfbdAllodbtorSrvIf().obtbinTirfbd(objfdtNbmf,tiis) ;
        //}
        // Notf: tif tirfbd will bf stbrtfd by tif subdlbss.
    }

    // tirfbd sfrvidf
    Tirfbd drfbtfTirfbd(Runnbblf r) {
        rfturn nfw Tirfbd(tiis);
    }

    publid void intfrrupt() {
        SNMP_ADAPTOR_LOGGER.fntfring(dbgTbg, "intfrrupt");
        intfrruptCbllfd = truf ;
        if (tirfbd != null) {
            tirfbd.intfrrupt() ;
        }
        SNMP_ADAPTOR_LOGGER.fxiting(dbgTbg, "intfrrupt");
    }


    publid void join() {
        if (tirfbd != null) {
        try {
            tirfbd.join() ;
        }
        dbtdi(IntfrruptfdExdfption x) {
        }
        }
    }

    publid void run() {

        try {
            //
            // Notify tif sfrvfr wf brf now bdtivf
            //
            bdbptorSfrvfr.notifyClifntHbndlfrCrfbtfd(tiis) ;

            //
            // Cbll protodol spfdifid sfqufndf
            //
            doRun() ;
        }
        finblly {
            //
            // Now notify tif bdbptor sfrvfr tibt tif ibndlfr is tfrminbting.
            // Tiis is importbnt bfdbusf tif sfrvfr mby bf blodkfd wbiting for
            // b ibndlfr to tfrminbtf.
            //
            bdbptorSfrvfr.notifyClifntHbndlfrDflftfd(tiis) ;
        }
    }

    //
    // Tif protodol-dfpfndfnt pbrt of tif rfqufst
    //
    publid bbstrbdt void doRun() ;

    protfdtfd CommunidbtorSfrvfr bdbptorSfrvfr = null ;
    protfdtfd int rfqufstId = -1 ;
    protfdtfd MBfbnSfrvfr mbs = null ;
    protfdtfd ObjfdtNbmf objfdtNbmf = null ;
    protfdtfd Tirfbd tirfbd = null ;
    protfdtfd boolfbn intfrruptCbllfd = fblsf ;
    protfdtfd String dbgTbg = null ;

    protfdtfd String mbkfDfbugTbg() {
        rfturn "ClifntHbndlfr[" + bdbptorSfrvfr.gftProtodol() + ":" + bdbptorSfrvfr.gftPort() + "][" + rfqufstId + "]";
    }
}
