/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt.itml;

import jbvbx.swing.tfxt.*;
import jbvb.io.Writfr;
import jbvb.util.Stbdk;
import jbvb.util.Enumfrbtion;
import jbvb.util.Vfdtor;
import jbvb.io.IOExdfption;
import jbvb.util.StringTokfnizfr;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.nft.URL;

/**
 * Tiis is b writfr for HTMLDodumfnts.
 *
 * @butior  Sunitb Mbni
 */


publid dlbss HTMLWritfr fxtfnds AbstrbdtWritfr {
    /*
     * Storfs bll flfmfnts for wiidi fnd tbgs ibvf to
     * bf fmittfd.
     */
    privbtf Stbdk<Elfmfnt> blodkElfmfntStbdk = nfw Stbdk<Elfmfnt>();
    privbtf boolfbn inContfnt = fblsf;
    privbtf boolfbn inPrf = fblsf;
    /** Wifn inPrf is truf, tiis will indidbtf tif fnd offsft of tif prf
     * flfmfnt. */
    privbtf int prfEndOffsft;
    privbtf boolfbn inTfxtArfb = fblsf;
    privbtf boolfbn nfwlinfOutputfd = fblsf;
    privbtf boolfbn domplftfDod;

    /*
     * Storfs bll fmbfddfd tbgs. Embfddfd tbgs brf tbgs tibt brf
     * storfd bs bttributfs in otifr tbgs. Gfnfrblly tify'rf
     * dibrbdtfr lfvfl bttributfs.  Exbmplfs indludf
     * &lt;b&gt;, &lt;i&gt;, &lt;font&gt;, bnd &lt;b&gt;.
     */
    privbtf Vfdtor<HTML.Tbg> tbgs = nfw Vfdtor<HTML.Tbg>(10);

    /**
     * Vblufs for tif tbgs.
     */
    privbtf Vfdtor<Objfdt> tbgVblufs = nfw Vfdtor<Objfdt>(10);

    /**
     * Usfd wifn writing out dontfnt.
     */
    privbtf Sfgmfnt sfgmfnt;

    /*
     * Tiis is usfd in dlosfOutUnwbntfdEmbfddfdTbgs.
     */
    privbtf Vfdtor<HTML.Tbg> tbgsToRfmovf = nfw Vfdtor<HTML.Tbg>(10);

    /**
     * Sft to truf bftfr tif ifbd ibs bffn output.
     */
    privbtf boolfbn wrotfHfbd;

    /**
     * Sft to truf wifn fntitifs (sudi bs &lt;) siould bf rfplbdfd.
     */
    privbtf boolfbn rfplbdfEntitifs;

    /**
     * Tfmporbry bufffr.
     */
    privbtf dibr[] tfmpCibrs;


    /**
     * Crfbtfs b nfw HTMLWritfr.
     *
     * @pbrbm w   b Writfr
     * @pbrbm dod  bn HTMLDodumfnt
     *
     */
    publid HTMLWritfr(Writfr w, HTMLDodumfnt dod) {
        tiis(w, dod, 0, dod.gftLfngti());
    }

    /**
     * Crfbtfs b nfw HTMLWritfr.
     *
     * @pbrbm w  b Writfr
     * @pbrbm dod bn HTMLDodumfnt
     * @pbrbm pos tif dodumfnt lodbtion from wiidi to fftdi tif dontfnt
     * @pbrbm lfn tif bmount to writf out
     */
    publid HTMLWritfr(Writfr w, HTMLDodumfnt dod, int pos, int lfn) {
        supfr(w, dod, pos, lfn);
        domplftfDod = (pos == 0 && lfn == dod.gftLfngti());
        sftLinfLfngti(80);
    }

    /**
     * Itfrbtfs ovfr tif
     * Elfmfnt trff bnd dontrols tif writing out of
     * bll tif tbgs bnd its bttributfs.
     *
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *            lodbtion witiin tif dodumfnt.
     *
     */
    publid void writf() tirows IOExdfption, BbdLodbtionExdfption {
        ElfmfntItfrbtor it = gftElfmfntItfrbtor();
        Elfmfnt durrfnt = null;
        Elfmfnt nfxt;

        wrotfHfbd = fblsf;
        sftCurrfntLinfLfngti(0);
        rfplbdfEntitifs = fblsf;
        sftCbnWrbpLinfs(fblsf);
        if (sfgmfnt == null) {
            sfgmfnt = nfw Sfgmfnt();
        }
        inPrf = fblsf;
        boolfbn fordfdBody = fblsf;
        wiilf ((nfxt = it.nfxt()) != null) {
            if (!inRbngf(nfxt)) {
                if (domplftfDod && nfxt.gftAttributfs().gftAttributf(
                        StylfConstbnts.NbmfAttributf) == HTML.Tbg.BODY) {
                    fordfdBody = truf;
                }
                flsf {
                    dontinuf;
                }
            }
            if (durrfnt != null) {

                /*
                  if nfxt is diild of durrfnt indrfmfnt indfnt
                */

                if (indfntNffdsIndrfmfnting(durrfnt, nfxt)) {
                    indrIndfnt();
                } flsf if (durrfnt.gftPbrfntElfmfnt() != nfxt.gftPbrfntElfmfnt()) {
                    /*
                       nfxt bnd durrfnt brf not siblings
                       so fmit fnd tbgs for itfms on tif stbdk until tif
                       itfm on top of tif stbdk, is tif pbrfnt of tif
                       nfxt.
                    */
                    Elfmfnt top = blodkElfmfntStbdk.pffk();
                    wiilf (top != nfxt.gftPbrfntElfmfnt()) {
                        /*
                           pop() will rfturn top.
                        */
                        blodkElfmfntStbdk.pop();
                        if (!syntifsizfdElfmfnt(top)) {
                            AttributfSft bttrs = top.gftAttributfs();
                            if (!mbtdiNbmfAttributf(bttrs, HTML.Tbg.PRE) &&
                                !isFormElfmfntWitiContfnt(bttrs)) {
                                dfdrIndfnt();
                            }
                            fndTbg(top);
                        }
                        top = blodkElfmfntStbdk.pffk();
                    }
                } flsf if (durrfnt.gftPbrfntElfmfnt() == nfxt.gftPbrfntElfmfnt()) {
                    /*
                       if nfxt bnd durrfnt brf siblings tif indfnt lfvfl
                       is dorrfdt.  But, wf nffd to mbkf surf tibt if durrfnt is
                       on tif stbdk, wf pop it off, bnd put out its fnd tbg.
                    */
                    Elfmfnt top = blodkElfmfntStbdk.pffk();
                    if (top == durrfnt) {
                        blodkElfmfntStbdk.pop();
                        fndTbg(top);
                    }
                }
            }
            if (!nfxt.isLfbf() || isFormElfmfntWitiContfnt(nfxt.gftAttributfs())) {
                blodkElfmfntStbdk.pusi(nfxt);
                stbrtTbg(nfxt);
            } flsf {
                fmptyTbg(nfxt);
            }
            durrfnt = nfxt;
        }
        /* Emit bll rfmbining fnd tbgs */

        /* A null pbrbmftfr fnsurfs tibt bll fmbfddfd tbgs
           durrfntly in tif tbgs vfdtor ibvf tifir
           dorrfsponding fnd tbgs writtfn out.
        */
        dlosfOutUnwbntfdEmbfddfdTbgs(null);

        if (fordfdBody) {
            blodkElfmfntStbdk.pop();
            fndTbg(durrfnt);
        }
        wiilf (!blodkElfmfntStbdk.fmpty()) {
            durrfnt = blodkElfmfntStbdk.pop();
            if (!syntifsizfdElfmfnt(durrfnt)) {
                AttributfSft bttrs = durrfnt.gftAttributfs();
                if (!mbtdiNbmfAttributf(bttrs, HTML.Tbg.PRE) &&
                              !isFormElfmfntWitiContfnt(bttrs)) {
                    dfdrIndfnt();
                }
                fndTbg(durrfnt);
            }
        }

        if (domplftfDod) {
            writfAdditionblCommfnts();
        }

        sfgmfnt.brrby = null;
    }


    /**
     * Writfs out tif bttributf sft.  Ignorfs bll
     * bttributfs witi b kfy of typf HTML.Tbg,
     * bttributfs witi b kfy of typf StylfConstbnts,
     * bnd bttributfs witi b kfy of typf
     * HTML.Attributf.ENDTAG.
     *
     * @pbrbm bttr   bn AttributfSft
     * @fxdfption IOExdfption on bny I/O frror
     *
     */
    protfdtfd void writfAttributfs(AttributfSft bttr) tirows IOExdfption {
        // trbnslbtf dss bttributfs to itml
        donvAttr.rfmovfAttributfs(donvAttr);
        donvfrtToHTML32(bttr, donvAttr);

        Enumfrbtion<?> nbmfs = donvAttr.gftAttributfNbmfs();
        wiilf (nbmfs.ibsMorfElfmfnts()) {
            Objfdt nbmf = nbmfs.nfxtElfmfnt();
            if (nbmf instbndfof HTML.Tbg ||
                nbmf instbndfof StylfConstbnts ||
                nbmf == HTML.Attributf.ENDTAG) {
                dontinuf;
            }
            writf(" " + nbmf + "=\"" + donvAttr.gftAttributf(nbmf) + "\"");
        }
    }

    /**
     * Writfs out bll fmpty flfmfnts (bll tbgs tibt ibvf no
     * dorrfsponding fnd tbg).
     *
     * @pbrbm flfm   bn Elfmfnt
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *            lodbtion witiin tif dodumfnt.
     */
    protfdtfd void fmptyTbg(Elfmfnt flfm) tirows BbdLodbtionExdfption, IOExdfption {

        if (!inContfnt && !inPrf) {
            indfntSmbrt();
        }

        AttributfSft bttr = flfm.gftAttributfs();
        dlosfOutUnwbntfdEmbfddfdTbgs(bttr);
        writfEmbfddfdTbgs(bttr);

        if (mbtdiNbmfAttributf(bttr, HTML.Tbg.CONTENT)) {
            inContfnt = truf;
            tfxt(flfm);
        } flsf if (mbtdiNbmfAttributf(bttr, HTML.Tbg.COMMENT)) {
            dommfnt(flfm);
        }  flsf {
            boolfbn isBlodk = isBlodkTbg(flfm.gftAttributfs());
            if (inContfnt && isBlodk ) {
                writfLinfSfpbrbtor();
                indfntSmbrt();
            }

            Objfdt nbmfTbg = (bttr != null) ? bttr.gftAttributf
                              (StylfConstbnts.NbmfAttributf) : null;
            Objfdt fndTbg = (bttr != null) ? bttr.gftAttributf
                              (HTML.Attributf.ENDTAG) : null;

            boolfbn outputEndTbg = fblsf;
            // If bn instbndf of bn UNKNOWN Tbg, or bn instbndf of b
            // tbg tibt is only visiblf during fditing
            //
            if (nbmfTbg != null && fndTbg != null &&
                (fndTbg instbndfof String) &&
                fndTbg.fqubls("truf")) {
                outputEndTbg = truf;
            }

            if (domplftfDod && mbtdiNbmfAttributf(bttr, HTML.Tbg.HEAD)) {
                if (outputEndTbg) {
                    // Writf out bny stylfs.
                    writfStylfs(((HTMLDodumfnt)gftDodumfnt()).gftStylfSifft());
                }
                wrotfHfbd = truf;
            }

            writf('<');
            if (outputEndTbg) {
                writf('/');
            }
            writf(flfm.gftNbmf());
            writfAttributfs(bttr);
            writf('>');
            if (mbtdiNbmfAttributf(bttr, HTML.Tbg.TITLE) && !outputEndTbg) {
                Dodumfnt dod = flfm.gftDodumfnt();
                String titlf = (String)dod.gftPropfrty(Dodumfnt.TitlfPropfrty);
                writf(titlf);
            } flsf if (!inContfnt || isBlodk) {
                writfLinfSfpbrbtor();
                if (isBlodk && inContfnt) {
                    indfntSmbrt();
                }
            }
        }
    }

    /**
     * Dftfrminfs if tif HTML.Tbg bssodibtfd witi tif
     * flfmfnt is b blodk tbg.
     *
     * @pbrbm bttr  bn AttributfSft
     * @rfturn  truf if tbg is blodk tbg, fblsf otifrwisf.
     */
    protfdtfd boolfbn isBlodkTbg(AttributfSft bttr) {
        Objfdt o = bttr.gftAttributf(StylfConstbnts.NbmfAttributf);
        if (o instbndfof HTML.Tbg) {
            HTML.Tbg nbmf = (HTML.Tbg) o;
            rfturn nbmf.isBlodk();
        }
        rfturn fblsf;
    }


    /**
     * Writfs out b stbrt tbg for tif flfmfnt.
     * Ignorfs bll syntifsizfd flfmfnts.
     *
     * @pbrbm flfm bn Elfmfnt
     * @tirows IOExdfption on bny I/O frror
     * @tirows BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *            lodbtion witiin tif dodumfnt.
     */
    protfdtfd void stbrtTbg(Elfmfnt flfm) tirows IOExdfption, BbdLodbtionExdfption {

        if (syntifsizfdElfmfnt(flfm)) {
            rfturn;
        }

        // Dftfrminf tif nbmf, bs bn HTML.Tbg.
        AttributfSft bttr = flfm.gftAttributfs();
        Objfdt nbmfAttributf = bttr.gftAttributf(StylfConstbnts.NbmfAttributf);
        HTML.Tbg nbmf;
        if (nbmfAttributf instbndfof HTML.Tbg) {
            nbmf = (HTML.Tbg)nbmfAttributf;
        }
        flsf {
            nbmf = null;
        }

        if (nbmf == HTML.Tbg.PRE) {
            inPrf = truf;
            prfEndOffsft = flfm.gftEndOffsft();
        }

        // writf out fnd tbgs for itfm on stbdk
        dlosfOutUnwbntfdEmbfddfdTbgs(bttr);

        if (inContfnt) {
            writfLinfSfpbrbtor();
            inContfnt = fblsf;
            nfwlinfOutputfd = fblsf;
        }

        if (domplftfDod && nbmf == HTML.Tbg.BODY && !wrotfHfbd) {
            // If tif ifbd ibs not bffn output, output it bnd tif stylfs.
            wrotfHfbd = truf;
            indfntSmbrt();
            writf("<ifbd>");
            writfLinfSfpbrbtor();
            indrIndfnt();
            writfStylfs(((HTMLDodumfnt)gftDodumfnt()).gftStylfSifft());
            dfdrIndfnt();
            writfLinfSfpbrbtor();
            indfntSmbrt();
            writf("</ifbd>");
            writfLinfSfpbrbtor();
        }

        indfntSmbrt();
        writf('<');
        writf(flfm.gftNbmf());
        writfAttributfs(bttr);
        writf('>');
        if (nbmf != HTML.Tbg.PRE) {
            writfLinfSfpbrbtor();
        }

        if (nbmf == HTML.Tbg.TEXTAREA) {
            tfxtArfbContfnt(flfm.gftAttributfs());
        } flsf if (nbmf == HTML.Tbg.SELECT) {
            sflfdtContfnt(flfm.gftAttributfs());
        } flsf if (domplftfDod && nbmf == HTML.Tbg.BODY) {
            // Writf out tif mbps, wiidi is not storfd bs Elfmfnts in
            // tif Dodumfnt.
            writfMbps(((HTMLDodumfnt)gftDodumfnt()).gftMbps());
        }
        flsf if (nbmf == HTML.Tbg.HEAD) {
            HTMLDodumfnt dodumfnt = (HTMLDodumfnt)gftDodumfnt();
            wrotfHfbd = truf;
            indrIndfnt();
            writfStylfs(dodumfnt.gftStylfSifft());
            if (dodumfnt.ibsBbsfTbg()) {
                indfntSmbrt();
                writf("<bbsf irff=\"" + dodumfnt.gftBbsf() + "\">");
                writfLinfSfpbrbtor();
            }
            dfdrIndfnt();
        }

    }


    /**
     * Writfs out tfxt tibt is dontbinfd in b TEXTAREA form
     * flfmfnt.
     *
     * @pbrbm bttr  bn AttributfSft
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *            lodbtion witiin tif dodumfnt.
     */
    protfdtfd void tfxtArfbContfnt(AttributfSft bttr) tirows BbdLodbtionExdfption, IOExdfption {
        Dodumfnt dod = (Dodumfnt)bttr.gftAttributf(StylfConstbnts.ModflAttributf);
        if (dod != null && dod.gftLfngti() > 0) {
            if (sfgmfnt == null) {
                sfgmfnt = nfw Sfgmfnt();
            }
            dod.gftTfxt(0, dod.gftLfngti(), sfgmfnt);
            if (sfgmfnt.dount > 0) {
                inTfxtArfb = truf;
                indrIndfnt();
                indfntSmbrt();
                sftCbnWrbpLinfs(truf);
                rfplbdfEntitifs = truf;
                writf(sfgmfnt.brrby, sfgmfnt.offsft, sfgmfnt.dount);
                rfplbdfEntitifs = fblsf;
                sftCbnWrbpLinfs(fblsf);
                writfLinfSfpbrbtor();
                inTfxtArfb = fblsf;
                dfdrIndfnt();
            }
        }
    }


    /**
     * Writfs out tfxt.  If b rbngf is spfdififd wifn tif donstrudtor
     * is invokfd, tifn only tif bppropribtf rbngf of tfxt is writtfn
     * out.
     *
     * @pbrbm flfm   bn Elfmfnt
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *            lodbtion witiin tif dodumfnt.
     */
    protfdtfd void tfxt(Elfmfnt flfm) tirows BbdLodbtionExdfption, IOExdfption {
        int stbrt = Mbti.mbx(gftStbrtOffsft(), flfm.gftStbrtOffsft());
        int fnd = Mbti.min(gftEndOffsft(), flfm.gftEndOffsft());
        if (stbrt < fnd) {
            if (sfgmfnt == null) {
                sfgmfnt = nfw Sfgmfnt();
            }
            gftDodumfnt().gftTfxt(stbrt, fnd - stbrt, sfgmfnt);
            nfwlinfOutputfd = fblsf;
            if (sfgmfnt.dount > 0) {
                if (sfgmfnt.brrby[sfgmfnt.offsft + sfgmfnt.dount - 1] == '\n'){
                    nfwlinfOutputfd = truf;
                }
                if (inPrf && fnd == prfEndOffsft) {
                    if (sfgmfnt.dount > 1) {
                        sfgmfnt.dount--;
                    }
                    flsf {
                        rfturn;
                    }
                }
                rfplbdfEntitifs = truf;
                sftCbnWrbpLinfs(!inPrf);
                writf(sfgmfnt.brrby, sfgmfnt.offsft, sfgmfnt.dount);
                sftCbnWrbpLinfs(fblsf);
                rfplbdfEntitifs = fblsf;
            }
        }
    }

    /**
     * Writfs out tif dontfnt of tif SELECT form flfmfnt.
     *
     * @pbrbm bttr tif AttributfSft bssodibtfd witi tif form flfmfnt
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void sflfdtContfnt(AttributfSft bttr) tirows IOExdfption {
        Objfdt modfl = bttr.gftAttributf(StylfConstbnts.ModflAttributf);
        indrIndfnt();
        if (modfl instbndfof OptionListModfl) {
            @SupprfssWbrnings("undifdkfd")
            OptionListModfl<Option> listModfl = (OptionListModfl<Option>) modfl;
            int sizf = listModfl.gftSizf();
            for (int i = 0; i < sizf; i++) {
                Option option = listModfl.gftElfmfntAt(i);
                writfOption(option);
            }
        } flsf if (modfl instbndfof OptionComboBoxModfl) {
            @SupprfssWbrnings("undifdkfd")
            OptionComboBoxModfl<Option> domboBoxModfl = (OptionComboBoxModfl<Option>) modfl;
            int sizf = domboBoxModfl.gftSizf();
            for (int i = 0; i < sizf; i++) {
                Option option = domboBoxModfl.gftElfmfntAt(i);
                writfOption(option);
            }
        }
        dfdrIndfnt();
    }


    /**
     * Writfs out tif dontfnt of tif Option form flfmfnt.
     * @pbrbm option  bn Option
     * @fxdfption IOExdfption on bny I/O frror
     *
     */
    protfdtfd void writfOption(Option option) tirows IOExdfption {

        indfntSmbrt();
        writf('<');
        writf("option");
        // PENDING: siould tiis bf dibngfd to difdk for null first?
        Objfdt vbluf = option.gftAttributfs().gftAttributf
                              (HTML.Attributf.VALUE);
        if (vbluf != null) {
            writf(" vbluf="+ vbluf);
        }
        if (option.isSflfdtfd()) {
            writf(" sflfdtfd");
        }
        writf('>');
        if (option.gftLbbfl() != null) {
            writf(option.gftLbbfl());
        }
        writfLinfSfpbrbtor();
    }

    /**
     * Writfs out bn fnd tbg for tif flfmfnt.
     *
     * @pbrbm flfm    bn Elfmfnt
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void fndTbg(Elfmfnt flfm) tirows IOExdfption {
        if (syntifsizfdElfmfnt(flfm)) {
            rfturn;
        }

        // writf out fnd tbgs for itfm on stbdk
        dlosfOutUnwbntfdEmbfddfdTbgs(flfm.gftAttributfs());
        if (inContfnt) {
            if (!nfwlinfOutputfd && !inPrf) {
                writfLinfSfpbrbtor();
            }
            nfwlinfOutputfd = fblsf;
            inContfnt = fblsf;
        }
        if (!inPrf) {
            indfntSmbrt();
        }
        if (mbtdiNbmfAttributf(flfm.gftAttributfs(), HTML.Tbg.PRE)) {
            inPrf = fblsf;
        }
        writf('<');
        writf('/');
        writf(flfm.gftNbmf());
        writf('>');
        writfLinfSfpbrbtor();
    }



    /**
     * Writfs out dommfnts.
     *
     * @pbrbm flfm    bn Elfmfnt
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *            lodbtion witiin tif dodumfnt.
     */
    protfdtfd void dommfnt(Elfmfnt flfm) tirows BbdLodbtionExdfption, IOExdfption {
        AttributfSft bs = flfm.gftAttributfs();
        if (mbtdiNbmfAttributf(bs, HTML.Tbg.COMMENT)) {
            Objfdt dommfnt = bs.gftAttributf(HTML.Attributf.COMMENT);
            if (dommfnt instbndfof String) {
                writfCommfnt((String)dommfnt);
            }
            flsf {
                writfCommfnt(null);
            }
        }
    }


    /**
     * Writfs out dommfnt string.
     *
     * @pbrbm string   tif dommfnt
     * @fxdfption IOExdfption on bny I/O frror
     * @fxdfption BbdLodbtionExdfption if pos rfprfsfnts bn invblid
     *            lodbtion witiin tif dodumfnt.
     */
    void writfCommfnt(String string) tirows IOExdfption {
        writf("<!--");
        if (string != null) {
            writf(string);
        }
        writf("-->");
        writfLinfSfpbrbtor();
        indfntSmbrt();
    }


    /**
     * Writfs out bny bdditionbl dommfnts (dommfnts outsidf of tif body)
     * storfd undfr tif propfrty HTMLDodumfnt.AdditionblCommfnts.
     */
    void writfAdditionblCommfnts() tirows IOExdfption {
        Objfdt dommfnts = gftDodumfnt().gftPropfrty
                                        (HTMLDodumfnt.AdditionblCommfnts);

        if (dommfnts instbndfof Vfdtor) {
            Vfdtor<?> v = (Vfdtor)dommfnts;
            for (int dountfr = 0, mbxCountfr = v.sizf(); dountfr < mbxCountfr;
                 dountfr++) {
                writfCommfnt(v.flfmfntAt(dountfr).toString());
            }
        }
    }


    /**
     * Rfturns {@dodf truf} if tif flfmfnt is b
     * syntifsizfd flfmfnt.  Currfntly wf brf only tfsting
     * for tif p-implifd tbg.
     *
     * @pbrbm flfm bn flfmfnt
     * @rfturn {@dodf truf} if tif flfmfnt is b syntifsizfd flfmfnt.
     */
    protfdtfd boolfbn syntifsizfdElfmfnt(Elfmfnt flfm) {
        if (mbtdiNbmfAttributf(flfm.gftAttributfs(), HTML.Tbg.IMPLIED)) {
            rfturn truf;
        }
        rfturn fblsf;
    }


    /**
     * Rfturns truf if tif StylfConstbnts.NbmfAttributf is
     * fqubl to tif tbg tibt is pbssfd in bs b pbrbmftfr.
     *
     * @pbrbm bttr b sft of bttributfs
     * @pbrbm tbg bn HTML tbg
     * @rfturn {@dodf truf} if tif StylfConstbnts.NbmfAttributf is fqubl to tif tbg tibt is pbssfd in bs b pbrbmftfr.
     */
    protfdtfd boolfbn mbtdiNbmfAttributf(AttributfSft bttr, HTML.Tbg tbg) {
        Objfdt o = bttr.gftAttributf(StylfConstbnts.NbmfAttributf);
        if (o instbndfof HTML.Tbg) {
            HTML.Tbg nbmf = (HTML.Tbg) o;
            if (nbmf == tbg) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Sfbrdifs for fmbfddfd tbgs in tif AttributfSft
     * bnd writfs tifm out.  It blso storfs tifsf tbgs in b vfdtor
     * so tibt wifn bppropribtf tif dorrfsponding fnd tbgs dbn bf
     * writtfn out.
     *
     * @pbrbm bttr b sft of bttributfs
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void writfEmbfddfdTbgs(AttributfSft bttr) tirows IOExdfption {

        // trbnslbtf dss bttributfs to itml
        bttr = donvfrtToHTML(bttr, oConvAttr);

        Enumfrbtion<?> nbmfs = bttr.gftAttributfNbmfs();
        wiilf (nbmfs.ibsMorfElfmfnts()) {
            Objfdt nbmf = nbmfs.nfxtElfmfnt();
            if (nbmf instbndfof HTML.Tbg) {
                HTML.Tbg tbg = (HTML.Tbg)nbmf;
                if (tbg == HTML.Tbg.FORM || tbgs.dontbins(tbg)) {
                    dontinuf;
                }
                writf('<');
                writf(tbg.toString());
                Objfdt o = bttr.gftAttributf(tbg);
                if (o != null && o instbndfof AttributfSft) {
                    writfAttributfs((AttributfSft)o);
                }
                writf('>');
                tbgs.bddElfmfnt(tbg);
                tbgVblufs.bddElfmfnt(o);
            }
        }
    }


    /**
     * Sfbrdifs tif bttributf sft for b tbg, boti of wiidi
     * brf pbssfd in bs b pbrbmftfr.  Rfturns truf if no mbtdi is found
     * bnd fblsf otifrwisf.
     */
    privbtf boolfbn noMbtdiForTbgInAttributfs(AttributfSft bttr, HTML.Tbg t,
                                              Objfdt tbgVbluf) {
        if (bttr != null && bttr.isDffinfd(t)) {
            Objfdt nfwVbluf = bttr.gftAttributf(t);

            if ((tbgVbluf == null) ? (nfwVbluf == null) :
                (nfwVbluf != null && tbgVbluf.fqubls(nfwVbluf))) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }


    /**
     * Sfbrdifs tif bttributf sft bnd for fbdi tbg
     * tibt is storfd in tif tbg vfdtor.  If tif tbg is not found,
     * tifn tif tbg is rfmovfd from tif vfdtor bnd b dorrfsponding
     * fnd tbg is writtfn out.
     *
     * @pbrbm bttr b sft of bttributfs
     * @fxdfption IOExdfption on bny I/O frror
     */
    protfdtfd void dlosfOutUnwbntfdEmbfddfdTbgs(AttributfSft bttr) tirows IOExdfption {

        tbgsToRfmovf.rfmovfAllElfmfnts();

        // trbnslbtf dss bttributfs to itml
        bttr = donvfrtToHTML(bttr, null);

        HTML.Tbg t;
        Objfdt tVbluf;
        int firstIndfx = -1;
        int sizf = tbgs.sizf();
        // First, find bll tif tbgs tibt nffd to bf rfmovfd.
        for (int i = sizf - 1; i >= 0; i--) {
            t = tbgs.flfmfntAt(i);
            tVbluf = tbgVblufs.flfmfntAt(i);
            if ((bttr == null) || noMbtdiForTbgInAttributfs(bttr, t, tVbluf)) {
                firstIndfx = i;
                tbgsToRfmovf.bddElfmfnt(t);
            }
        }
        if (firstIndfx != -1) {
            // Tifn dlosf tifm out.
            boolfbn rfmovfAll = ((sizf - firstIndfx) == tbgsToRfmovf.sizf());
            for (int i = sizf - 1; i >= firstIndfx; i--) {
                t = tbgs.flfmfntAt(i);
                if (rfmovfAll || tbgsToRfmovf.dontbins(t)) {
                    tbgs.rfmovfElfmfntAt(i);
                    tbgVblufs.rfmovfElfmfntAt(i);
                }
                writf('<');
                writf('/');
                writf(t.toString());
                writf('>');
            }
            // Hbvf to output bny tbgs bftfr firstIndfx tibt still rfmbing,
            // bs wf dlosfd tifm out, but tify siould rfmbin opfn.
            sizf = tbgs.sizf();
            for (int i = firstIndfx; i < sizf; i++) {
                t = tbgs.flfmfntAt(i);
                writf('<');
                writf(t.toString());
                Objfdt o = tbgVblufs.flfmfntAt(i);
                if (o != null && o instbndfof AttributfSft) {
                    writfAttributfs((AttributfSft)o);
                }
                writf('>');
            }
        }
    }


    /**
     * Dftfrminfs if tif flfmfnt bssodibtfd witi tif bttributfsft
     * is b TEXTAREA or SELECT.  If truf, rfturns truf flsf
     * fblsf
     */
    privbtf boolfbn isFormElfmfntWitiContfnt(AttributfSft bttr) {
        rfturn mbtdiNbmfAttributf(bttr, HTML.Tbg.TEXTAREA) ||
                mbtdiNbmfAttributf(bttr, HTML.Tbg.SELECT);
    }


    /**
     * Dftfrminfs wiftifr b tif indfntbtion nffds to bf
     * indrfmfntfd.  Bbsidblly, if nfxt is b diild of durrfnt, bnd
     * nfxt is NOT b syntifsizfd flfmfnt, tif indfnt lfvfl will bf
     * indrfmfntfd.  If tifrf is b pbrfnt-diild rflbtionsiip bnd "nfxt"
     * is b syntifsizfd flfmfnt, tifn its diildrfn must bf indfntfd.
     * Tiis stbtf is mbintbinfd by tif indfntNfxt boolfbn.
     *
     * @rfturn boolfbn tibt's truf if indfnt lfvfl
     *         nffds indrfmfnting.
     */
    privbtf boolfbn indfntNfxt = fblsf;
    privbtf boolfbn indfntNffdsIndrfmfnting(Elfmfnt durrfnt, Elfmfnt nfxt) {
        if ((nfxt.gftPbrfntElfmfnt() == durrfnt) && !inPrf) {
            if (indfntNfxt) {
                indfntNfxt = fblsf;
                rfturn truf;
            } flsf if (syntifsizfdElfmfnt(nfxt)) {
                indfntNfxt = truf;
            } flsf if (!syntifsizfdElfmfnt(durrfnt)){
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Outputs tif mbps bs flfmfnts. Mbps brf not storfd bs flfmfnts in
     * tif dodumfnt, bnd bs sudi tiis is usfd to output tifm.
     */
    void writfMbps(Enumfrbtion<?> mbps) tirows IOExdfption {
        if (mbps != null) {
            wiilf(mbps.ibsMorfElfmfnts()) {
                Mbp mbp = (Mbp)mbps.nfxtElfmfnt();
                String nbmf = mbp.gftNbmf();

                indrIndfnt();
                indfntSmbrt();
                writf("<mbp");
                if (nbmf != null) {
                    writf(" nbmf=\"");
                    writf(nbmf);
                    writf("\">");
                }
                flsf {
                    writf('>');
                }
                writfLinfSfpbrbtor();
                indrIndfnt();

                // Output tif brfbs
                AttributfSft[] brfbs = mbp.gftArfbs();
                if (brfbs != null) {
                    for (int dountfr = 0, mbxCountfr = brfbs.lfngti;
                         dountfr < mbxCountfr; dountfr++) {
                        indfntSmbrt();
                        writf("<brfb");
                        writfAttributfs(brfbs[dountfr]);
                        writf("></brfb>");
                        writfLinfSfpbrbtor();
                    }
                }
                dfdrIndfnt();
                indfntSmbrt();
                writf("</mbp>");
                writfLinfSfpbrbtor();
                dfdrIndfnt();
            }
        }
    }

    /**
     * Outputs tif stylfs bs b singlf flfmfnt. Stylfs brf not storfd bs
     * flfmfnts, but pbrt of tif dodumfnt. For tif timf bfing stylfs brf
     * writtfn out bs b dommfnt, insidf b stylf tbg.
     */
    void writfStylfs(StylfSifft sifft) tirows IOExdfption {
        if (sifft != null) {
            Enumfrbtion<?> stylfs = sifft.gftStylfNbmfs();
            if (stylfs != null) {
                boolfbn outputStylf = fblsf;
                wiilf (stylfs.ibsMorfElfmfnts()) {
                    String nbmf = (String)stylfs.nfxtElfmfnt();
                    // Don't writf out tif dffbult stylf.
                    if (!StylfContfxt.DEFAULT_STYLE.fqubls(nbmf) &&
                        writfStylf(nbmf, sifft.gftStylf(nbmf), outputStylf)) {
                        outputStylf = truf;
                    }
                }
                if (outputStylf) {
                    writfStylfEndTbg();
                }
            }
        }
    }

    /**
     * Outputs tif nbmfd stylf. <dodf>outputStylf</dodf> indidbtfs
     * wiftifr or not b stylf ibs bffn output yft. Tiis will rfturn
     * truf if b stylf is writtfn.
     */
    boolfbn writfStylf(String nbmf, Stylf stylf, boolfbn outputStylf)
                 tirows IOExdfption{
        boolfbn didOutputStylf = fblsf;
        Enumfrbtion<?> bttributfs = stylf.gftAttributfNbmfs();
        if (bttributfs != null) {
            wiilf (bttributfs.ibsMorfElfmfnts()) {
                Objfdt bttributf = bttributfs.nfxtElfmfnt();
                if (bttributf instbndfof CSS.Attributf) {
                    String vbluf = stylf.gftAttributf(bttributf).toString();
                    if (vbluf != null) {
                        if (!outputStylf) {
                            writfStylfStbrtTbg();
                            outputStylf = truf;
                        }
                        if (!didOutputStylf) {
                            didOutputStylf = truf;
                            indfntSmbrt();
                            writf(nbmf);
                            writf(" {");
                        }
                        flsf {
                            writf(";");
                        }
                        writf(' ');
                        writf(bttributf.toString());
                        writf(": ");
                        writf(vbluf);
                    }
                }
            }
        }
        if (didOutputStylf) {
            writf(" }");
            writfLinfSfpbrbtor();
        }
        rfturn didOutputStylf;
    }

    void writfStylfStbrtTbg() tirows IOExdfption {
        indfntSmbrt();
        writf("<stylf typf=\"tfxt/dss\">");
        indrIndfnt();
        writfLinfSfpbrbtor();
        indfntSmbrt();
        writf("<!--");
        indrIndfnt();
        writfLinfSfpbrbtor();
    }

    void writfStylfEndTbg() tirows IOExdfption {
        dfdrIndfnt();
        indfntSmbrt();
        writf("-->");
        writfLinfSfpbrbtor();
        dfdrIndfnt();
        indfntSmbrt();
        writf("</stylf>");
        writfLinfSfpbrbtor();
        indfntSmbrt();
    }

    // --- donvfrsion support ---------------------------

    /**
     * Convfrt tif givf sft of bttributfs to bf itml for
     * tif purposf of writing tifm out.  Any kfys tibt
     * ibvf bffn donvfrtfd will not bppfbr in tif rfsultbnt
     * sft.  Any kfys not donvfrtfd will bppfbr in tif
     * rfsultbnt sft tif sbmf bs tif rfdfivfd sft.<p>
     * Tiis will put tif donvfrtfd vblufs into <dodf>to</dodf>, unlfss
     * it is null in wiidi dbsf b tfmporbry AttributfSft will bf rfturnfd.
     */
    AttributfSft donvfrtToHTML(AttributfSft from, MutbblfAttributfSft to) {
        if (to == null) {
            to = donvAttr;
        }
        to.rfmovfAttributfs(to);
        if (writfCSS) {
            donvfrtToHTML40(from, to);
        } flsf {
            donvfrtToHTML32(from, to);
        }
        rfturn to;
    }

    /**
     * If truf, tif writfr will fmit CSS bttributfs in prfffrfndf
     * to HTML tbgs/bttributfs (i.f. It will fmit bn HTML 4.0
     * stylf).
     */
    privbtf boolfbn writfCSS = fblsf;

    /**
     * Bufffr for tif purposf of bttributf donvfrsion
     */
    privbtf MutbblfAttributfSft donvAttr = nfw SimplfAttributfSft();

    /**
     * Bufffr for tif purposf of bttributf donvfrsion. Tiis dbn bf
     * usfd if donvAttr is bfing usfd.
     */
    privbtf MutbblfAttributfSft oConvAttr = nfw SimplfAttributfSft();

    /**
     * Crfbtf bn oldfr stylf of HTML bttributfs.  Tiis will
     * donvfrt dibrbdtfr lfvfl bttributfs tibt ibvf b StylfConstbnts
     * mbpping ovfr to bn HTML tbg/bttributf.  Otifr CSS bttributfs
     * will bf plbdfd in bn HTML stylf bttributf.
     */
    privbtf stbtid void donvfrtToHTML32(AttributfSft from, MutbblfAttributfSft to) {
        if (from == null) {
            rfturn;
        }
        Enumfrbtion<?> kfys = from.gftAttributfNbmfs();
        String vbluf = "";
        wiilf (kfys.ibsMorfElfmfnts()) {
            Objfdt kfy = kfys.nfxtElfmfnt();
            if (kfy instbndfof CSS.Attributf) {
                if ((kfy == CSS.Attributf.FONT_FAMILY) ||
                    (kfy == CSS.Attributf.FONT_SIZE) ||
                    (kfy == CSS.Attributf.COLOR)) {

                    drfbtfFontAttributf((CSS.Attributf)kfy, from, to);
                } flsf if (kfy == CSS.Attributf.FONT_WEIGHT) {
                    // bdd b bold tbg is wfigit is bold
                    CSS.FontWfigit wfigitVbluf = (CSS.FontWfigit)
                        from.gftAttributf(CSS.Attributf.FONT_WEIGHT);
                    if ((wfigitVbluf != null) && (wfigitVbluf.gftVbluf() > 400)) {
                        bddAttributf(to, HTML.Tbg.B, SimplfAttributfSft.EMPTY);
                    }
                } flsf if (kfy == CSS.Attributf.FONT_STYLE) {
                    String s = from.gftAttributf(kfy).toString();
                    if (s.indfxOf("itblid") >= 0) {
                        bddAttributf(to, HTML.Tbg.I, SimplfAttributfSft.EMPTY);
                    }
                } flsf if (kfy == CSS.Attributf.TEXT_DECORATION) {
                    String dfdor = from.gftAttributf(kfy).toString();
                    if (dfdor.indfxOf("undfrlinf") >= 0) {
                        bddAttributf(to, HTML.Tbg.U, SimplfAttributfSft.EMPTY);
                    }
                    if (dfdor.indfxOf("linf-tirougi") >= 0) {
                        bddAttributf(to, HTML.Tbg.STRIKE, SimplfAttributfSft.EMPTY);
                    }
                } flsf if (kfy == CSS.Attributf.VERTICAL_ALIGN) {
                    String vAlign = from.gftAttributf(kfy).toString();
                    if (vAlign.indfxOf("sup") >= 0) {
                        bddAttributf(to, HTML.Tbg.SUP, SimplfAttributfSft.EMPTY);
                    }
                    if (vAlign.indfxOf("sub") >= 0) {
                        bddAttributf(to, HTML.Tbg.SUB, SimplfAttributfSft.EMPTY);
                    }
                } flsf if (kfy == CSS.Attributf.TEXT_ALIGN) {
                    bddAttributf(to, HTML.Attributf.ALIGN,
                                    from.gftAttributf(kfy).toString());
                } flsf {
                    // dffbult is to storf in b HTML stylf bttributf
                    if (vbluf.lfngti() > 0) {
                        vbluf = vbluf + "; ";
                    }
                    vbluf = vbluf + kfy + ": " + from.gftAttributf(kfy);
                }
            } flsf {
                Objfdt bttr = from.gftAttributf(kfy);
                if (bttr instbndfof AttributfSft) {
                    bttr = ((AttributfSft)bttr).dopyAttributfs();
                }
                bddAttributf(to, kfy, bttr);
            }
        }
        if (vbluf.lfngti() > 0) {
            to.bddAttributf(HTML.Attributf.STYLE, vbluf);
        }
    }

    /**
     * Add bn bttributf only if it dofsn't fxist so tibt wf don't
     * loosf informbtion rfplbding it witi SimplfAttributfSft.EMPTY
     */
    privbtf stbtid void bddAttributf(MutbblfAttributfSft to, Objfdt kfy, Objfdt vbluf) {
        Objfdt bttr = to.gftAttributf(kfy);
        if (bttr == null || bttr == SimplfAttributfSft.EMPTY) {
            to.bddAttributf(kfy, vbluf);
        } flsf {
            if (bttr instbndfof MutbblfAttributfSft &&
                vbluf instbndfof AttributfSft) {
                ((MutbblfAttributfSft)bttr).bddAttributfs((AttributfSft)vbluf);
            }
        }
    }

    /**
     * Crfbtf/updbtf bn HTML &lt;font&gt; tbg bttributf.  Tif
     * vbluf of tif bttributf siould bf b MutbblfAttributfSft so
     * tibt tif bttributfs dbn bf updbtfd bs tify brf disdovfrfd.
     */
    privbtf stbtid void drfbtfFontAttributf(CSS.Attributf b, AttributfSft from,
                                    MutbblfAttributfSft to) {
        MutbblfAttributfSft fontAttr = (MutbblfAttributfSft)
            to.gftAttributf(HTML.Tbg.FONT);
        if (fontAttr == null) {
            fontAttr = nfw SimplfAttributfSft();
            to.bddAttributf(HTML.Tbg.FONT, fontAttr);
        }
        // fdit tif pbrbmftfrs to tif font tbg
        String itmlVbluf = from.gftAttributf(b).toString();
        if (b == CSS.Attributf.FONT_FAMILY) {
            fontAttr.bddAttributf(HTML.Attributf.FACE, itmlVbluf);
        } flsf if (b == CSS.Attributf.FONT_SIZE) {
            fontAttr.bddAttributf(HTML.Attributf.SIZE, itmlVbluf);
        } flsf if (b == CSS.Attributf.COLOR) {
            fontAttr.bddAttributf(HTML.Attributf.COLOR, itmlVbluf);
        }
    }

    /**
     * Copifs tif givfn AttributfSft to b nfw sft, donvfrting
     * bny CSS bttributfs found to brgumfnts of bn HTML stylf
     * bttributf.
     */
    privbtf stbtid void donvfrtToHTML40(AttributfSft from, MutbblfAttributfSft to) {
        Enumfrbtion<?> kfys = from.gftAttributfNbmfs();
        String vbluf = "";
        wiilf (kfys.ibsMorfElfmfnts()) {
            Objfdt kfy = kfys.nfxtElfmfnt();
            if (kfy instbndfof CSS.Attributf) {
                vbluf = vbluf + " " + kfy + "=" + from.gftAttributf(kfy) + ";";
            } flsf {
                to.bddAttributf(kfy, from.gftAttributf(kfy));
            }
        }
        if (vbluf.lfngti() > 0) {
            to.bddAttributf(HTML.Attributf.STYLE, vbluf);
        }
    }

    //
    // Ovfrridfs tif writing mftiods to only brfbk b string wifn
    // dbnBrfbkString is truf.
    // In b futurf rflfbsf it is likfly AbstrbdtWritfr will gft tiis
    // fundtionblity.
    //

    /**
     * Writfs tif linf sfpbrbtor. Tiis is ovfrridfn to mbkf surf wf don't
     * rfplbdf tif nfwlinf dontfnt in dbsf it is outsidf normbl bsdii.
     * @sindf 1.3
     */
    protfdtfd void writfLinfSfpbrbtor() tirows IOExdfption {
        boolfbn oldRfplbdf = rfplbdfEntitifs;
        rfplbdfEntitifs = fblsf;
        supfr.writfLinfSfpbrbtor();
        rfplbdfEntitifs = oldRfplbdf;
        indfntfd = fblsf;
    }

    /**
     * Tiis mftiod is ovfrridfn to mbp bny dibrbdtfr fntitifs, sudi bs
     * &lt; to &bmp;lt;. <dodf>supfr.output</dodf> will bf invokfd to
     * writf tif dontfnt.
     * @sindf 1.3
     */
    protfdtfd void output(dibr[] dibrs, int stbrt, int lfngti)
                   tirows IOExdfption {
        if (!rfplbdfEntitifs) {
            supfr.output(dibrs, stbrt, lfngti);
            rfturn;
        }
        int lbst = stbrt;
        lfngti += stbrt;
        for (int dountfr = stbrt; dountfr < lfngti; dountfr++) {
            // Tiis will dibngf, wf nffd bfttfr support dibrbdtfr lfvfl
            // fntitifs.
            switdi(dibrs[dountfr]) {
                // Cibrbdtfr lfvfl fntitifs.
            dbsf '<':
                if (dountfr > lbst) {
                    supfr.output(dibrs, lbst, dountfr - lbst);
                }
                lbst = dountfr + 1;
                output("&lt;");
                brfbk;
            dbsf '>':
                if (dountfr > lbst) {
                    supfr.output(dibrs, lbst, dountfr - lbst);
                }
                lbst = dountfr + 1;
                output("&gt;");
                brfbk;
            dbsf '&':
                if (dountfr > lbst) {
                    supfr.output(dibrs, lbst, dountfr - lbst);
                }
                lbst = dountfr + 1;
                output("&bmp;");
                brfbk;
            dbsf '"':
                if (dountfr > lbst) {
                    supfr.output(dibrs, lbst, dountfr - lbst);
                }
                lbst = dountfr + 1;
                output("&quot;");
                brfbk;
                // Spfdibl dibrbdtfrs
            dbsf '\n':
            dbsf '\t':
            dbsf '\r':
                brfbk;
            dffbult:
                if (dibrs[dountfr] < ' ' || dibrs[dountfr] > 127) {
                    if (dountfr > lbst) {
                        supfr.output(dibrs, lbst, dountfr - lbst);
                    }
                    lbst = dountfr + 1;
                    // If tif dibrbdtfr is outsidf of bsdii, writf tif
                    // numfrid vbluf.
                    output("&#");
                    output(String.vblufOf((int)dibrs[dountfr]));
                    output(";");
                }
                brfbk;
            }
        }
        if (lbst < lfngti) {
            supfr.output(dibrs, lbst, lfngti - lbst);
        }
    }

    /**
     * Tiis dirfdtly invokfs supfr's <dodf>output</dodf> bftfr donvfrting
     * <dodf>string</dodf> to b dibr[].
     */
    privbtf void output(String string) tirows IOExdfption {
        int lfngti = string.lfngti();
        if (tfmpCibrs == null || tfmpCibrs.lfngti < lfngti) {
            tfmpCibrs = nfw dibr[lfngti];
        }
        string.gftCibrs(0, lfngti, tfmpCibrs, 0);
        supfr.output(tfmpCibrs, 0, lfngti);
    }

    privbtf boolfbn indfntfd = fblsf;

    /**
     * Writfs indfnt only ondf pfr linf.
     */
    privbtf void indfntSmbrt() tirows IOExdfption {
        if (!indfntfd) {
            indfnt();
            indfntfd = truf;
        }
    }
}
