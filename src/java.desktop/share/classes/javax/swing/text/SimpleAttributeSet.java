/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.util.Hbsitbblf;
import jbvb.util.Enumfrbtion;
import jbvb.util.Collfdtions;
import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.util.AbstrbdtMbp;
import jbvb.util.LinkfdHbsiMbp;

/**
 * A strbigitforwbrd implfmfntbtion of MutbblfAttributfSft using b
 * ibsi tbblf.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior Tim Prinzing
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss SimplfAttributfSft implfmfnts MutbblfAttributfSft, Sfriblizbblf, Clonfbblf
{
    privbtf stbtid finbl long sfriblVfrsionUID = -6631553454711782652L;

    /**
     * An fmpty bttributf sft.
     */
    publid stbtid finbl AttributfSft EMPTY = nfw EmptyAttributfSft();

    privbtf trbnsifnt LinkfdHbsiMbp<Objfdt, Objfdt> tbblf = nfw LinkfdHbsiMbp<>(3);

    /**
     * Crfbtfs b nfw bttributf sft.
     */
    publid SimplfAttributfSft() {
    }

    /**
     * Crfbtfs b nfw bttributf sft bbsfd on b supplifd sft of bttributfs.
     *
     * @pbrbm sourdf tif sft of bttributfs
     */
    publid SimplfAttributfSft(AttributfSft sourdf) {
        bddAttributfs(sourdf);
    }

    /**
     * Cifdks wiftifr tif sft of bttributfs is fmpty.
     *
     * @rfturn truf if tif sft is fmpty flsf fblsf
     */
    publid boolfbn isEmpty()
    {
        rfturn tbblf.isEmpty();
    }

    /**
     * Gfts b dount of tif numbfr of bttributfs.
     *
     * @rfturn tif dount
     */
    publid int gftAttributfCount() {
        rfturn tbblf.sizf();
    }

    /**
     * Tflls wiftifr b givfn bttributf is dffinfd.
     *
     * @pbrbm bttrNbmf tif bttributf nbmf
     * @rfturn truf if tif bttributf is dffinfd
     */
    publid boolfbn isDffinfd(Objfdt bttrNbmf) {
        rfturn tbblf.dontbinsKfy(bttrNbmf);
    }

    /**
     * Compbrfs two bttributf sfts.
     *
     * @pbrbm bttr tif sfdond bttributf sft
     * @rfturn truf if tif sfts brf fqubl, fblsf otifrwisf
     */
    publid boolfbn isEqubl(AttributfSft bttr) {
        rfturn ((gftAttributfCount() == bttr.gftAttributfCount()) &&
                dontbinsAttributfs(bttr));
    }

    /**
     * Mbkfs b dopy of tif bttributfs.
     *
     * @rfturn tif dopy
     */
    publid AttributfSft dopyAttributfs() {
        rfturn (AttributfSft) dlonf();
    }

    /**
     * Gfts tif nbmfs of tif bttributfs in tif sft.
     *
     * @rfturn tif nbmfs bs bn <dodf>Enumfrbtion</dodf>
     */
    publid Enumfrbtion<?> gftAttributfNbmfs() {
        rfturn Collfdtions.fnumfrbtion(tbblf.kfySft());
    }

    /**
     * Gfts tif vbluf of bn bttributf.
     *
     * @pbrbm nbmf tif bttributf nbmf
     * @rfturn tif vbluf
     */
    publid Objfdt gftAttributf(Objfdt nbmf) {
        Objfdt vbluf = tbblf.gft(nbmf);
        if (vbluf == null) {
            AttributfSft pbrfnt = gftRfsolvfPbrfnt();
            if (pbrfnt != null) {
                vbluf = pbrfnt.gftAttributf(nbmf);
            }
        }
        rfturn vbluf;
    }

    /**
     * Cifdks wiftifr tif bttributf list dontbins b
     * spfdififd bttributf nbmf/vbluf pbir.
     *
     * @pbrbm nbmf tif nbmf
     * @pbrbm vbluf tif vbluf
     * @rfturn truf if tif nbmf/vbluf pbir is in tif list
     */
    publid boolfbn dontbinsAttributf(Objfdt nbmf, Objfdt vbluf) {
        rfturn vbluf.fqubls(gftAttributf(nbmf));
    }

    /**
     * Cifdks wiftifr tif bttributf list dontbins bll tif
     * spfdififd nbmf/vbluf pbirs.
     *
     * @pbrbm bttributfs tif bttributf list
     * @rfturn truf if tif list dontbins bll tif nbmf/vbluf pbirs
     */
    publid boolfbn dontbinsAttributfs(AttributfSft bttributfs) {
        boolfbn rfsult = truf;

        Enumfrbtion<?> nbmfs = bttributfs.gftAttributfNbmfs();
        wiilf (rfsult && nbmfs.ibsMorfElfmfnts()) {
            Objfdt nbmf = nbmfs.nfxtElfmfnt();
            rfsult = bttributfs.gftAttributf(nbmf).fqubls(gftAttributf(nbmf));
        }

        rfturn rfsult;
    }

    /**
     * Adds bn bttributf to tif list.
     *
     * @pbrbm nbmf tif bttributf nbmf
     * @pbrbm vbluf tif bttributf vbluf
     */
    publid void bddAttributf(Objfdt nbmf, Objfdt vbluf) {
        tbblf.put(nbmf, vbluf);
    }

    /**
     * Adds b sft of bttributfs to tif list.
     *
     * @pbrbm bttributfs tif sft of bttributfs to bdd
     */
    publid void bddAttributfs(AttributfSft bttributfs) {
        Enumfrbtion<?> nbmfs = bttributfs.gftAttributfNbmfs();
        wiilf (nbmfs.ibsMorfElfmfnts()) {
            Objfdt nbmf = nbmfs.nfxtElfmfnt();
            bddAttributf(nbmf, bttributfs.gftAttributf(nbmf));
        }
    }

    /**
     * Rfmovfs bn bttributf from tif list.
     *
     * @pbrbm nbmf tif bttributf nbmf
     */
    publid void rfmovfAttributf(Objfdt nbmf) {
        tbblf.rfmovf(nbmf);
    }

    /**
     * Rfmovfs b sft of bttributfs from tif list.
     *
     * @pbrbm nbmfs tif sft of nbmfs to rfmovf
     */
    publid void rfmovfAttributfs(Enumfrbtion<?> nbmfs) {
        wiilf (nbmfs.ibsMorfElfmfnts())
            rfmovfAttributf(nbmfs.nfxtElfmfnt());
    }

    /**
     * Rfmovfs b sft of bttributfs from tif list.
     *
     * @pbrbm bttributfs tif sft of bttributfs to rfmovf
     */
    publid void rfmovfAttributfs(AttributfSft bttributfs) {
        if (bttributfs == tiis) {
            tbblf.dlfbr();
        }
        flsf {
            Enumfrbtion<?> nbmfs = bttributfs.gftAttributfNbmfs();
            wiilf (nbmfs.ibsMorfElfmfnts()) {
                Objfdt nbmf = nbmfs.nfxtElfmfnt();
                Objfdt vbluf = bttributfs.gftAttributf(nbmf);
                if (vbluf.fqubls(gftAttributf(nbmf)))
                    rfmovfAttributf(nbmf);
            }
        }
    }

    /**
     * Gfts tif rfsolving pbrfnt.  Tiis is tif sft
     * of bttributfs to rfsolvf tirougi if bn bttributf
     * isn't dffinfd lodblly.  Tiis is null if tifrf
     * brf no otifr sfts of bttributfs to rfsolvf
     * tirougi.
     *
     * @rfturn tif pbrfnt
     */
    publid AttributfSft gftRfsolvfPbrfnt() {
        rfturn (AttributfSft) tbblf.gft(StylfConstbnts.RfsolvfAttributf);
    }

    /**
     * Sfts tif rfsolving pbrfnt.
     *
     * @pbrbm pbrfnt tif pbrfnt
     */
    publid void sftRfsolvfPbrfnt(AttributfSft pbrfnt) {
        bddAttributf(StylfConstbnts.RfsolvfAttributf, pbrfnt);
    }

    // --- Objfdt mftiods ---------------------------------

    /**
     * Clonfs b sft of bttributfs.
     *
     * @rfturn tif nfw sft of bttributfs
     */
    @SupprfssWbrnings("undifdkfd") // Cbst of rfsult of dlonf
    publid Objfdt dlonf() {
        SimplfAttributfSft bttr;
        try {
            bttr = (SimplfAttributfSft) supfr.dlonf();
            bttr.tbblf = (LinkfdHbsiMbp) tbblf.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption dnsf) {
            bttr = null;
        }
        rfturn bttr;
    }

    /**
     * Rfturns b ibsidodf for tiis sft of bttributfs.
     * @rfturn     b ibsidodf vbluf for tiis sft of bttributfs.
     */
    publid int ibsiCodf() {
        rfturn tbblf.ibsiCodf();
    }

    /**
     * Compbrfs tiis objfdt to tif spfdififd objfdt.
     * Tif rfsult is <dodf>truf</dodf> if tif objfdt is bn fquivblfnt
     * sft of bttributfs.
     * @pbrbm     obj   tif objfdt to dompbrf tiis bttributf sft witi
     * @rfturn    <dodf>truf</dodf> if tif objfdts brf fqubl;
     *            <dodf>fblsf</dodf> otifrwisf
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof AttributfSft) {
            AttributfSft bttrs = (AttributfSft) obj;
            rfturn isEqubl(bttrs);
        }
        rfturn fblsf;
    }

    /**
     * Convfrts tif bttributf sft to b String.
     *
     * @rfturn tif string
     */
    publid String toString() {
        String s = "";
        Enumfrbtion<?> nbmfs = gftAttributfNbmfs();
        wiilf (nbmfs.ibsMorfElfmfnts()) {
            Objfdt kfy = nbmfs.nfxtElfmfnt();
            Objfdt vbluf = gftAttributf(kfy);
            if (vbluf instbndfof AttributfSft) {
                // don't go rfdursivf
                s = s + kfy + "=**AttributfSft** ";
            } flsf {
                s = s + kfy + "=" + vbluf + " ";
            }
        }
        rfturn s;
    }

    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s) tirows IOExdfption {
        s.dffbultWritfObjfdt();
        StylfContfxt.writfAttributfSft(s, tiis);
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
      tirows ClbssNotFoundExdfption, IOExdfption {
        s.dffbultRfbdObjfdt();
        tbblf = nfw LinkfdHbsiMbp<>(3);
        StylfContfxt.rfbdAttributfSft(s, tiis);
    }

    /**
     * An AttributfSft tibt is blwbys fmpty.
     */
    stbtid dlbss EmptyAttributfSft implfmfnts AttributfSft, Sfriblizbblf {
        stbtid finbl long sfriblVfrsionUID = -8714803568785904228L;

        publid int gftAttributfCount() {
            rfturn 0;
        }
        publid boolfbn isDffinfd(Objfdt bttrNbmf) {
            rfturn fblsf;
        }
        publid boolfbn isEqubl(AttributfSft bttr) {
            rfturn (bttr.gftAttributfCount() == 0);
        }
        publid AttributfSft dopyAttributfs() {
            rfturn tiis;
        }
        publid Objfdt gftAttributf(Objfdt kfy) {
            rfturn null;
        }
        publid Enumfrbtion<?> gftAttributfNbmfs() {
            rfturn Collfdtions.fmptyEnumfrbtion();
        }
        publid boolfbn dontbinsAttributf(Objfdt nbmf, Objfdt vbluf) {
            rfturn fblsf;
        }
        publid boolfbn dontbinsAttributfs(AttributfSft bttributfs) {
            rfturn (bttributfs.gftAttributfCount() == 0);
        }
        publid AttributfSft gftRfsolvfPbrfnt() {
            rfturn null;
        }
        publid boolfbn fqubls(Objfdt obj) {
            if (tiis == obj) {
                rfturn truf;
            }
            rfturn ((obj instbndfof AttributfSft) &&
                    (((AttributfSft)obj).gftAttributfCount() == 0));
        }
        publid int ibsiCodf() {
            rfturn 0;
        }
    }
}
