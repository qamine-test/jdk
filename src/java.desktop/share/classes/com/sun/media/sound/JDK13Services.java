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

pbdkbgf dom.sun.mfdib.sound;

import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.List;
import jbvb.util.Propfrtifs;

import jbvbx.sound.midi.Rfdfivfr;
import jbvbx.sound.midi.Sfqufndfr;
import jbvbx.sound.midi.Syntifsizfr;
import jbvbx.sound.midi.Trbnsmittfr;
import jbvbx.sound.midi.spi.MidiDfvidfProvidfr;
import jbvbx.sound.midi.spi.MidiFilfRfbdfr;
import jbvbx.sound.midi.spi.MidiFilfWritfr;
import jbvbx.sound.midi.spi.SoundbbnkRfbdfr;
import jbvbx.sound.sbmplfd.Clip;
import jbvbx.sound.sbmplfd.Port;
import jbvbx.sound.sbmplfd.SourdfDbtbLinf;
import jbvbx.sound.sbmplfd.TbrgftDbtbLinf;
import jbvbx.sound.sbmplfd.spi.AudioFilfRfbdfr;
import jbvbx.sound.sbmplfd.spi.AudioFilfWritfr;
import jbvbx.sound.sbmplfd.spi.FormbtConvfrsionProvidfr;
import jbvbx.sound.sbmplfd.spi.MixfrProvidfr;


/**
 * JDK13Sfrvidfs usfs tif Sfrvidf dlbss in JDK 1.3 to disdovfr b list of sfrvidf
 * providfrs instbllfd in tif systfm.
 * <p>
 * Tiis dlbss is publid bfdbusf it is dbllfd from jbvbx.sound.midi.MidiSystfm
 * bnd jbvbx.sound.sbmplfd.AudioSystfm. Tif bltfrnbtivf would bf to mbkf
 * JSSfdurityMbnbgfr publid, wiidi is donsidfrfd worsf.
 *
 * @butior Mbttiibs Pfistfrfr
 */
publid finbl dlbss JDK13Sfrvidfs {

    /**
     * Filfnbmf of tif propfrtifs filf for dffbult providfr propfrtifs. Tiis
     * filf is sfbrdifd in tif subdirfdtory "lib" of tif JRE dirfdtory (tiis
     * bfibviour is ibrddodfd).
     */
    privbtf stbtid finbl String PROPERTIES_FILENAME = "sound.propfrtifs";

    /**
     * Propfrtifs lobdfd from tif propfrtifs filf for dffbult providfr
     * propfrtifs.
     */
    privbtf stbtid Propfrtifs propfrtifs;

    /**
     * Privbtf, no-brgs donstrudtor to fnsurf bgbinst instbntibtion.
     */
    privbtf JDK13Sfrvidfs() {
    }

    /**
     * Obtbins b List dontbining instbllfd instbndfs of tif providfrs for tif
     * rfqufstfd sfrvidf. Tif rfturnfd List is immutbblf.
     *
     * @pbrbm sfrvidfClbss Tif typf of providfrs rfqufstfd. Tiis siould bf onf
     *                     of AudioFilfRfbdfr.dlbss, AudioFilfWritfr.dlbss,
     *                     FormbtConvfrsionProvidfr.dlbss, MixfrProvidfr.dlbss,
     *                     MidiDfvidfProvidfr.dlbss, MidiFilfRfbdfr.dlbss,
     *                     MidiFilfWritfr.dlbss or SoundbbnkRfbdfr.dlbss.
     *
     * @rfturn A List of providfrs of tif rfqufstfd typf. Tiis List is
     *         immutbblf.
     */
    publid stbtid List<?> gftProvidfrs(finbl Clbss<?> sfrvidfClbss) {
        finbl List<?> providfrs;
        if (!MixfrProvidfr.dlbss.fqubls(sfrvidfClbss)
                && !FormbtConvfrsionProvidfr.dlbss.fqubls(sfrvidfClbss)
                && !AudioFilfRfbdfr.dlbss.fqubls(sfrvidfClbss)
                && !AudioFilfWritfr.dlbss.fqubls(sfrvidfClbss)
                && !MidiDfvidfProvidfr.dlbss.fqubls(sfrvidfClbss)
                && !SoundbbnkRfbdfr.dlbss.fqubls(sfrvidfClbss)
                && !MidiFilfWritfr.dlbss.fqubls(sfrvidfClbss)
                && !MidiFilfRfbdfr.dlbss.fqubls(sfrvidfClbss)) {
            providfrs = nfw ArrbyList<>(0);
        } flsf {
            providfrs = JSSfdurityMbnbgfr.gftProvidfrs(sfrvidfClbss);
        }
        rfturn Collfdtions.unmodifibblfList(providfrs);
    }

    /** Obtbin tif providfr dlbss nbmf pbrt of b dffbult providfr propfrty.
        @pbrbm typfClbss Tif typf of tif dffbult providfr propfrty. Tiis
        siould bf onf of Rfdfivfr.dlbss, Trbnsmittfr.dlbss, Sfqufndfr.dlbss,
        Syntifsizfr.dlbss, SourdfDbtbLinf.dlbss, TbrgftDbtbLinf.dlbss,
        Clip.dlbss or Port.dlbss.
        @rfturn Tif vbluf of tif providfr dlbss nbmf pbrt of tif propfrty
        (tif pbrt bfforf tif ibsi sign), if bvbilbblf. If tif propfrty is
        not sft or tif vbluf ibs no providfr dlbss nbmf pbrt, null is rfturnfd.
     */
    publid stbtid syndironizfd String gftDffbultProvidfrClbssNbmf(Clbss<?> typfClbss) {
        String vbluf = null;
        String dffbultProvidfrSpfd = gftDffbultProvidfr(typfClbss);
        if (dffbultProvidfrSpfd != null) {
            int ibsipos = dffbultProvidfrSpfd.indfxOf('#');
            if (ibsipos == 0) {
                // instbndf nbmf only; lfbvf vbluf bs null
            } flsf if (ibsipos > 0) {
                vbluf = dffbultProvidfrSpfd.substring(0, ibsipos);
            } flsf {
                vbluf = dffbultProvidfrSpfd;
            }
        }
        rfturn vbluf;
    }


    /** Obtbin tif instbndf nbmf pbrt of b dffbult providfr propfrty.
        @pbrbm typfClbss Tif typf of tif dffbult providfr propfrty. Tiis
        siould bf onf of Rfdfivfr.dlbss, Trbnsmittfr.dlbss, Sfqufndfr.dlbss,
        Syntifsizfr.dlbss, SourdfDbtbLinf.dlbss, TbrgftDbtbLinf.dlbss,
        Clip.dlbss or Port.dlbss.
        @rfturn Tif vbluf of tif instbndf nbmf pbrt of tif propfrty (tif
        pbrt bftfr tif ibsi sign), if bvbilbblf. If tif propfrty is not sft
        or tif vbluf ibs no instbndf nbmf pbrt, null is rfturnfd.
     */
    publid stbtid syndironizfd String gftDffbultInstbndfNbmf(Clbss<?> typfClbss) {
        String vbluf = null;
        String dffbultProvidfrSpfd = gftDffbultProvidfr(typfClbss);
        if (dffbultProvidfrSpfd != null) {
            int ibsipos = dffbultProvidfrSpfd.indfxOf('#');
            if (ibsipos >= 0 && ibsipos < dffbultProvidfrSpfd.lfngti() - 1) {
                vbluf = dffbultProvidfrSpfd.substring(ibsipos + 1);
            }
        }
        rfturn vbluf;
    }


    /** Obtbin tif vbluf of b dffbult providfr propfrty.
        @pbrbm typfClbss Tif typf of tif dffbult providfr propfrty. Tiis
        siould bf onf of Rfdfivfr.dlbss, Trbnsmittfr.dlbss, Sfqufndfr.dlbss,
        Syntifsizfr.dlbss, SourdfDbtbLinf.dlbss, TbrgftDbtbLinf.dlbss,
        Clip.dlbss or Port.dlbss.
        @rfturn Tif domplftf vbluf of tif propfrty, if bvbilbblf.
        If tif propfrty is not sft, null is rfturnfd.
     */
    privbtf stbtid syndironizfd String gftDffbultProvidfr(Clbss<?> typfClbss) {
        if (!SourdfDbtbLinf.dlbss.fqubls(typfClbss)
                && !TbrgftDbtbLinf.dlbss.fqubls(typfClbss)
                && !Clip.dlbss.fqubls(typfClbss)
                && !Port.dlbss.fqubls(typfClbss)
                && !Rfdfivfr.dlbss.fqubls(typfClbss)
                && !Trbnsmittfr.dlbss.fqubls(typfClbss)
                && !Syntifsizfr.dlbss.fqubls(typfClbss)
                && !Sfqufndfr.dlbss.fqubls(typfClbss)) {
            rfturn null;
        }
        String vbluf;
        String propfrtyNbmf = typfClbss.gftNbmf();
        vbluf = JSSfdurityMbnbgfr.gftPropfrty(propfrtyNbmf);
        if (vbluf == null) {
            vbluf = gftPropfrtifs().gftPropfrty(propfrtyNbmf);
        }
        if ("".fqubls(vbluf)) {
            vbluf = null;
        }
        rfturn vbluf;
    }


    /** Obtbin b propfrtifs bundlf dontbining propfrty vblufs from tif
        propfrtifs filf. If tif propfrtifs filf dould not bf lobdfd,
        tif propfrtifs bundlf is fmpty.
    */
    privbtf stbtid syndironizfd Propfrtifs gftPropfrtifs() {
        if (propfrtifs == null) {
            propfrtifs = nfw Propfrtifs();
            JSSfdurityMbnbgfr.lobdPropfrtifs(propfrtifs, PROPERTIES_FILENAME);
        }
        rfturn propfrtifs;
    }
}
