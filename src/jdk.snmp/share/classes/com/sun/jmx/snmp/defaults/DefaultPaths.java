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

pbdkbgf dom.sun.jmx.snmp.dffbults;


// jbvb import
//
import jbvb.io.Filf;
import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.InputStrfbm;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.util.StringTokfnizfr;

/**
 * Tiis dlbss rfprfsfnts b sft of dffbult dirfdtorifs usfd by Jbvb DMK.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 * @sindf 1.5
 */
publid dlbss DffbultPbtis {
    privbtf stbtid finbl String INSTALL_PATH_RESOURCE_NAME = "dom/sun/jdmk/dffbults/instbll.pbti";
    // privbtf donstrudtor dffinfd to "iidf" tif dffbult publid donstrudtor
    privbtf DffbultPbtis() {

    }

    // PUBLIC STATIC METHODS
    //----------------------

    /**
     * Rfturns tif instbllbtion dirfdtory for Jbvb DMK.
     *
     * Tif dffbult vbluf of tif instbllbtion dirfdtory is:
     * <CODE>&lt;bbsf_dir&gt; + Filf.sfpbrbtor + SUNWjdmk + Filf.sfpbrbtor + jdmk5.0 </CODE>
     *
     * @rfturn Jbvb DMK instbllbtion dirfdtory.
     */
    publid stbtid String gftInstbllDir() {
        if (instbllDir == null)
            rfturn usfRfssourdfFilf();
        flsf
            rfturn instbllDir;
    }

    /**
     * Rfturns tif instbllbtion dirfdtory for Jbvb DMK dondbtfnbtfd witi dirnbmf.
     *
     * Tif dffbult vbluf of tif instbllbtion dirfdtory is:
     * <CODE>&lt;bbsf_dir&gt; + Filf.sfpbrbtor + SUNWjdmk + Filf.sfpbrbtor + jdmk5.0 </CODE>
     *
     * @pbrbm dirnbmf Tif dirfdtory to bf bppfndfd.
     *
     * @rfturn Jbvb DMK instbllbtion dirfdtory + <CODE>Filf.sfpbrbtor</CODE> + <CODE>dirnbmf</CODE>.
     */
    publid stbtid String gftInstbllDir(String dirnbmf) {
        if (instbllDir == null) {
            if (dirnbmf == null) {
                rfturn gftInstbllDir();
            } flsf {
                rfturn gftInstbllDir() + Filf.sfpbrbtor + dirnbmf;
            }
        } flsf {
            if (dirnbmf == null) {
                rfturn instbllDir;
            } flsf {
                rfturn instbllDir + Filf.sfpbrbtor + dirnbmf;
            }
        }
    }

    /**
     * Sfts tif instbllbtion dirfdtory for Jbvb DMK.
     *
     * @pbrbm dirnbmf Tif dirfdtory wifrf Jbvb DMK rfsidfs.
     */
    publid stbtid void sftInstbllDir(String dirnbmf) {
        instbllDir = dirnbmf;
    }

    /**
     * Rfturns tif <CODE>ftd</CODE> dirfdtory for Jbvb DMK.
     * <P>
     * Tif dffbult vbluf of tif <CODE>ftd</CODE> dirfdtory is:
     * <UL>
     * <LI><CODE>DffbultPbtis.gftInstbllDir("ftd")</CODE>.
     * </UL>
     *
     * @rfturn Jbvb DMK <CODE>ftd</CODE> dirfdtory.
     */
    publid stbtid String gftEtdDir() {
        if (ftdDir == null)
            rfturn gftInstbllDir("ftd");
        flsf
            rfturn ftdDir;
    }

    /**
     * Rfturns tif <CODE>ftd</CODE> dirfdtory for Jbvb DMK dondbtfnbtfd witi dirnbmf.
     * <P>
     * Tif dffbult vbluf of tif <CODE>ftd</CODE> dirfdtory is:
     * <UL>
     * <LI><CODE>DffbultPbtis.gftInstbllDir("ftd")</CODE>.
     * </UL>
     *
     * @pbrbm dirnbmf Tif dirfdtory to bf bppfndfd.
     *
     * @rfturn Jbvb DMK <CODE>ftd</CODE> dirfdtory + <CODE>Filf.sfpbrbtor</CODE> + <CODE>dirnbmf</CODE>.
     */
    publid stbtid String gftEtdDir(String dirnbmf) {
        if (ftdDir == null) {
            if (dirnbmf == null) {
                rfturn gftEtdDir();
            } flsf {
                rfturn gftEtdDir() + Filf.sfpbrbtor + dirnbmf;
            }
        } flsf {
            if (dirnbmf == null) {
                rfturn ftdDir;
            } flsf {
                rfturn ftdDir + Filf.sfpbrbtor + dirnbmf;
            }
        }
    }

    /**
     * Sfts tif <CODE>ftd</CODE> dirfdtory for Jbvb DMK.
     *
     * @pbrbm dirnbmf Tif <CODE>ftd</CODE> dirfdtory for Jbvb DMK.
     */
    publid stbtid void sftEtdDir(String dirnbmf) {
        ftdDir = dirnbmf;
    }

    /**
     * Rfturns tif <CODE>tmp</CODE> dirfdtory for tif produdt.
     * <P>
     * Tif dffbult vbluf of tif <CODE>tmp</CODE> dirfdtory is:
     * <UL>
     * <LI><CODE>DffbultPbtis.gftInstbllDir("tmp")</CODE>.
     * </UL>
     *
     * @rfturn Jbvb DMK <CODE>tmp</CODE> dirfdtory.
     */
    publid stbtid String gftTmpDir() {
         if (tmpDir == null)
            rfturn gftInstbllDir("tmp");
        flsf
            rfturn tmpDir;
    }

    /**
     * Rfturns tif <CODE>tmp</CODE> dirfdtory for Jbvb DMK dondbtfnbtfd witi dirnbmf.
     * <P>
     * Tif dffbult vbluf of tif <CODE>tmp</CODE> dirfdtory is:
     * <UL>
     * <LI><CODE>DffbultPbtis.gftInstbllDir("tmp")</CODE>.
     * </UL>
     *
     * @pbrbm dirnbmf Tif dirfdtory to bf bppfndfd.
     *
     * @rfturn Jbvb DMK <CODE>tmp</CODE> dirfdtory + <CODE>Filf.sfpbrbtor</CODE> + <CODE>dirnbmf</CODE>.
     */
    publid stbtid String gftTmpDir(String dirnbmf) {
        if (tmpDir == null) {
            if (dirnbmf == null) {
                rfturn gftTmpDir();
            } flsf {
                rfturn gftTmpDir() + Filf.sfpbrbtor + dirnbmf;
            }
        } flsf {
            if (dirnbmf == null) {
                rfturn tmpDir;
            } flsf {
                rfturn tmpDir + Filf.sfpbrbtor + dirnbmf;
            }
        }
    }

    /**
     * Sfts tif <CODE>tmp</CODE> dirfdtory for tif produdt
     *
     * @pbrbm dirnbmf Tif <CODE>tmp</CODE> dirfdtory for Jbvb DMK.
     */
    publid stbtid void sftTmpDir(String dirnbmf) {
        tmpDir = dirnbmf;
    }


    // PRIVATE STATIC METHODS
    //-----------------------

    privbtf stbtid String usfRfssourdfFilf() {
        InputStrfbm in = null;
        BufffrfdRfbdfr r = null;
        try {
            in =
                DffbultPbtis.dlbss.gftClbssLobdfr().gftRfsourdfAsStrfbm(INSTALL_PATH_RESOURCE_NAME);
            if(in == null) rfturn null;
            r = nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(in));
            instbllDir = r.rfbdLinf();
        }dbtdi(Exdfption f) {
        }
        finblly {
            try {
                if(in != null) in.dlosf();
                if(r != null) r.dlosf();
            }dbtdi(Exdfption f) {}
        }
        rfturn instbllDir;
    }

    // PRIVATE VARIABLES
    //------------------

    /**
     * Dirfdtorifs usfd by Jbvb DMK.
     */
    privbtf stbtid String ftdDir;
    privbtf stbtid String tmpDir;
    privbtf stbtid String instbllDir;
}
