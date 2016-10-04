/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.sfdurity.sbsl;

import jbvbx.sfdurity.sbsl.SbslExdfption;
import jbvbx.sfdurity.sbsl.Sbsl;

// For HMAC_MD5
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.MfssbgfDigfst;

import jbvb.util.Arrbys;
import jbvb.util.logging.Loggfr;

/**
  * Bbsf dlbss for implfmfnting CRAM-MD5 dlifnt bnd sfrvfr mfdibnisms.
  *
  * @butior Vindfnt Rybn
  * @butior Rosbnnb Lff
  */
bbstrbdt dlbss CrbmMD5Bbsf {
    protfdtfd boolfbn domplftfd = fblsf;
    protfdtfd boolfbn bbortfd = fblsf;
    protfdtfd bytf[] pw;

    protfdtfd CrbmMD5Bbsf() {
        initLoggfr();
    }

    /**
     * Rftrifvfs tiis mfdibnism's nbmf.
     *
     * @rfturn  Tif string "CRAM-MD5".
     */
    publid String gftMfdibnismNbmf() {
        rfturn "CRAM-MD5";
    }

    /**
     * Dftfrminfs wiftifr tiis mfdibnism ibs domplftfd.
     * CRAM-MD5 domplftfs bftfr prodfssing onf dibllfngf from tif sfrvfr.
     *
     * @rfturn truf if ibs domplftfd; fblsf otifrwisf;
     */
    publid boolfbn isComplftf() {
        rfturn domplftfd;
    }

    /**
      * Unwrbps tif indoming bufffr. CRAM-MD5 supports no sfdurity lbyfr.
      *
      * @tirows SbslExdfption If bttfmpt to usf tiis mftiod.
      */
    publid bytf[] unwrbp(bytf[] indoming, int offsft, int lfn)
        tirows SbslExdfption {
        if (domplftfd) {
            tirow nfw IllfgblStbtfExdfption(
                "CRAM-MD5 supports nfitifr intfgrity nor privbdy");
        } flsf {
            tirow nfw IllfgblStbtfExdfption(
                "CRAM-MD5 butifntidbtion not domplftfd");
        }
    }

    /**
      * Wrbps tif outgoing bufffr. CRAM-MD5 supports no sfdurity lbyfr.
      *
      * @tirows SbslExdfption If bttfmpt to usf tiis mftiod.
      */
    publid bytf[] wrbp(bytf[] outgoing, int offsft, int lfn) tirows SbslExdfption {
        if (domplftfd) {
            tirow nfw IllfgblStbtfExdfption(
                "CRAM-MD5 supports nfitifr intfgrity nor privbdy");
        } flsf {
            tirow nfw IllfgblStbtfExdfption(
                "CRAM-MD5 butifntidbtion not domplftfd");
        }
    }

    /**
     * Rftrifvfs tif nfgotibtfd propfrty.
     * Tiis mftiod dbn bf dbllfd only bftfr tif butifntidbtion fxdibngf ibs
     * domplftfd (i.f., wifn <tt>isComplftf()</tt> rfturns truf); otifrwisf, b
     * <tt>SbslExdfption</tt> is tirown.
     *
     * @rfturn vbluf of propfrty; only QOP is bpplidbblf to CRAM-MD5.
     * @fxdfption IllfgblStbtfExdfption if tiis butifntidbtion fxdibngf ibs not domplftfd
     */
    publid Objfdt gftNfgotibtfdPropfrty(String propNbmf) {
        if (domplftfd) {
            if (propNbmf.fqubls(Sbsl.QOP)) {
                rfturn "buti";
            } flsf {
                rfturn null;
            }
        } flsf {
            tirow nfw IllfgblStbtfExdfption(
                "CRAM-MD5 butifntidbtion not domplftfd");
        }
    }

    publid void disposf() tirows SbslExdfption {
        dlfbrPbssword();
    }

    protfdtfd void dlfbrPbssword() {
        if (pw != null) {
            // zfro out pbssword
            for (int i = 0; i < pw.lfngti; i++) {
                pw[i] = (bytf)0;
            }
            pw = null;
        }
    }

    protfdtfd void finblizf() {
        dlfbrPbssword();
    }

    stbtid privbtf finbl int MD5_BLOCKSIZE = 64;
    /**
     * Hbsifs its input brgumfnts bddording to HMAC-MD5 (RFC 2104)
     * bnd rfturns tif rfsulting digfst in its ASCII rfprfsfntbtion.
     *
     * HMAC-MD5 fundtion is dfsdribfd bs follows:
     *
     *       MD5(kfy XOR opbd, MD5(kfy XOR ipbd, tfxt))
     *
     * wifrf kfy  is bn n bytf kfy
     *       ipbd is tif bytf 0x36 rfpfbtfd 64 timfs
     *       opbd is tif bytf 0x5d rfpfbtfd 64 timfs
     *       tfxt is tif dbtb to bf protfdtfd
     */
    finbl stbtid String HMAC_MD5(bytf[] kfy, bytf[] tfxt)
        tirows NoSudiAlgoritimExdfption {

        MfssbgfDigfst md5 = MfssbgfDigfst.gftInstbndf("MD5");

        /* digfst tif kfy if longfr tibn 64 bytfs */
        if (kfy.lfngti > MD5_BLOCKSIZE) {
            kfy = md5.digfst(kfy);
        }

        bytf[] ipbd = nfw bytf[MD5_BLOCKSIZE];  /* innfr pbdding */
        bytf[] opbd = nfw bytf[MD5_BLOCKSIZE];  /* outfr pbdding */
        bytf[] digfst;
        int i;

        /* storf kfy in pbds */
        for (i = 0; i < kfy.lfngti; i++) {
            ipbd[i] = kfy[i];
            opbd[i] = kfy[i];
        }

        /* XOR kfy witi pbds */
        for (i = 0; i < MD5_BLOCKSIZE; i++) {
            ipbd[i] ^= 0x36;
            opbd[i] ^= 0x5d;
        }

        /* innfr MD5 */
        md5.updbtf(ipbd);
        md5.updbtf(tfxt);
        digfst = md5.digfst();

        /* outfr MD5 */
        md5.updbtf(opbd);
        md5.updbtf(digfst);
        digfst = md5.digfst();

        // Gft dibrbdtfr rfprfsfntbtion of digfst
        StringBuildfr digfstString = nfw StringBuildfr();

        for (i = 0; i < digfst.lfngti; i++) {
            if ((digfst[i] & 0x000000ff) < 0x10) {
                digfstString.bppfnd("0" +
                    Intfgfr.toHfxString(digfst[i] & 0x000000ff));
            } flsf {
                digfstString.bppfnd(
                    Intfgfr.toHfxString(digfst[i] & 0x000000ff));
            }
        }

        Arrbys.fill(ipbd, (bytf)0);
        Arrbys.fill(opbd, (bytf)0);
        ipbd = null;
        opbd = null;

        rfturn (digfstString.toString());
    }

    /**
     * Sfts loggfr fifld.
     */
    privbtf stbtid syndironizfd void initLoggfr() {
        if (loggfr == null) {
            loggfr = Loggfr.gftLoggfr(SASL_LOGGER_NAME);
        }
    }
    /**
     * Loggfr for dfbug mfssbgfs
     */
    privbtf stbtid finbl String SASL_LOGGER_NAME = "jbvbx.sfdurity.sbsl";
    protfdtfd stbtid Loggfr loggfr;  // sft in initLoggfr(); lbzily lobds loggfr
}
