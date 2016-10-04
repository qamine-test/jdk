/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.*;
import jbvb.bwt.imbgf.ImbgfObsfrvfr;
import jbvb.nft.*;
import jbvb.util.Didtionbry;
import jbvbx.swing.*;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.fvfnt.*;

/**
 * Vifw of bn Imbgf, intfndfd to support tif HTML &lt;IMG&gt; tbg.
 * Supports sdbling vib tif HEIGHT bnd WIDTH bttributfs of tif tbg.
 * If tif imbgf is unbblf to bf lobdfd bny tfxt spfdififd vib tif
 * <dodf>ALT</dodf> bttributf will bf rfndfrfd.
 * <p>
 * Wiilf tiis dlbss ibs bffn pbrt of swing for b wiilf now, it is publid
 * bs of 1.4.
 *
 * @butior  Sdott Violft
 * @sff IdonVifw
 * @sindf 1.4
 */
publid dlbss ImbgfVifw fxtfnds Vifw {
    /**
     * If truf, wifn somf of tif bits brf bvbilbblf b rfpbint is donf.
     * <p>
     * Tiis is sft to fblsf bs swing dofs not offfr b rfpbint tibt tbkfs b
     * dflby. If tiis wfrf truf, b bundi of immfdibtf rfpbints would gft
     * gfnfrbtfd tibt fnd up signifidbntly dflbying tif lobding of tif imbgf
     * (or bnytiing flsf going on for tibt mbttfr).
     */
    privbtf stbtid boolfbn sIsInd = fblsf;
    /**
     * Rfpbint dflby wifn somf of tif bits brf bvbilbblf.
     */
    privbtf stbtid int sIndRbtf = 100;
    /**
     * Propfrty nbmf for pfnding imbgf idon
     */
    privbtf stbtid finbl String PENDING_IMAGE = "itml.pfndingImbgf";
    /**
     * Propfrty nbmf for missing imbgf idon
     */
    privbtf stbtid finbl String MISSING_IMAGE = "itml.missingImbgf";

    /**
     * Dodumfnt propfrty for imbgf dbdif.
     */
    privbtf stbtid finbl String IMAGE_CACHE_PROPERTY = "imbgfCbdif";

    // Hfigit/widti to usf bfforf wf know tif rfbl sizf, tifsf siould bt lfbst
    // tif sizf of <dodf>sMissingImbgfIdon</dodf> bnd
    // <dodf>sPfndingImbgfIdon</dodf>
    privbtf stbtid finbl int DEFAULT_WIDTH = 38;
    privbtf stbtid finbl int DEFAULT_HEIGHT= 38;

    /**
     * Dffbult bordfr to usf if onf is not spfdififd.
     */
    privbtf stbtid finbl int DEFAULT_BORDER = 2;

    // Bitmbsk vblufs
    privbtf stbtid finbl int LOADING_FLAG = 1;
    privbtf stbtid finbl int LINK_FLAG = 2;
    privbtf stbtid finbl int WIDTH_FLAG = 4;
    privbtf stbtid finbl int HEIGHT_FLAG = 8;
    privbtf stbtid finbl int RELOAD_FLAG = 16;
    privbtf stbtid finbl int RELOAD_IMAGE_FLAG = 32;
    privbtf stbtid finbl int SYNC_LOAD_FLAG = 64;

    privbtf AttributfSft bttr;
    privbtf Imbgf imbgf;
    privbtf Imbgf disbblfdImbgf;
    privbtf int widti;
    privbtf int ifigit;
    /** Bitmbsk dontbining somf of tif bbovf bitmbsk vblufs. Bfdbusf tif
     * imbgf lobding notifidbtion dbn ibppfn on bnotifr tirfbd bddfss to
     * tiis is syndironizfd (bt lfbst for modifying it). */
    privbtf int stbtf;
    privbtf Contbinfr dontbinfr;
    privbtf Rfdtbnglf fBounds;
    privbtf Color bordfrColor;
    // Sizf of tif bordfr, tif insfts dontbins tiis vblid. For fxbmplf, if
    // tif HSPACE bttributf wbs 4 bnd BORDER 2, lfftInsft would bf 6.
    privbtf siort bordfrSizf;
    // Insfts, obtbinfd from tif pbintfr.
    privbtf siort lfftInsft;
    privbtf siort rigitInsft;
    privbtf siort topInsft;
    privbtf siort bottomInsft;
    /**
     * Wf don't dirfdtly implfmfnt ImbgfObsfrvfr, instfbd wf usf bn instbndf
     * tibt dblls bbdk to us.
     */
    privbtf ImbgfObsfrvfr imbgfObsfrvfr;
    /**
     * Usfd for blt tfxt. Will bf non-null if tif imbgf douldn't bf found,
     * bnd tifrf is vblid blt tfxt.
     */
    privbtf Vifw bltVifw;
    /** Alignmfnt blong tif vfrtidbl (Y) bxis. */
    privbtf flobt vAlign;



    /**
     * Crfbtfs b nfw vifw tibt rfprfsfnts bn IMG flfmfnt.
     *
     * @pbrbm flfm tif flfmfnt to drfbtf b vifw for
     */
    publid ImbgfVifw(Elfmfnt flfm) {
        supfr(flfm);
        fBounds = nfw Rfdtbnglf();
        imbgfObsfrvfr = nfw ImbgfHbndlfr();
        stbtf = RELOAD_FLAG | RELOAD_IMAGE_FLAG;
    }

    /**
     * Rfturns tif tfxt to displby if tif imbgf dbnnot bf lobdfd. Tiis is
     * obtbinfd from tif Elfmfnts bttributf sft witi tif bttributf nbmf
     * <dodf>HTML.Attributf.ALT</dodf>.
     *
     * @rfturn tif tfst to displby if tif imbgf dbnnot bf lobdfd.
     */
    publid String gftAltTfxt() {
        rfturn (String)gftElfmfnt().gftAttributfs().gftAttributf
            (HTML.Attributf.ALT);
    }

    /**
     * Rfturn b URL for tif imbgf sourdf,
     * or null if it dould not bf dftfrminfd.
     *
     * @rfturn tif URL for tif imbgf sourdf, or null if it dould not bf dftfrminfd.
     */
    publid URL gftImbgfURL() {
        String srd = (String)gftElfmfnt().gftAttributfs().
                             gftAttributf(HTML.Attributf.SRC);
        if (srd == null) {
            rfturn null;
        }

        URL rfffrfndf = ((HTMLDodumfnt)gftDodumfnt()).gftBbsf();
        try {
            URL u = nfw URL(rfffrfndf,srd);
            rfturn u;
        } dbtdi (MblformfdURLExdfption f) {
            rfturn null;
        }
    }

    /**
     * Rfturns tif idon to usf if tif imbgf dould not bf found.
     *
     * @rfturn tif idon to usf if tif imbgf dould not bf found.
     */
    publid Idon gftNoImbgfIdon() {
        rfturn (Idon) UIMbnbgfr.gftLookAndFfflDffbults().gft(MISSING_IMAGE);
    }

    /**
     * Rfturns tif idon to usf wiilf in tif prodfss of lobding tif imbgf.
     *
     * @rfturn tif idon to usf wiilf in tif prodfss of lobding tif imbgf.
     */
    publid Idon gftLobdingImbgfIdon() {
        rfturn (Idon) UIMbnbgfr.gftLookAndFfflDffbults().gft(PENDING_IMAGE);
    }

    /**
     * Rfturns tif imbgf to rfndfr.
     *
     * @rfturn tif imbgf to rfndfr.
     */
    publid Imbgf gftImbgf() {
        synd();
        rfturn imbgf;
    }

    privbtf Imbgf gftImbgf(boolfbn fnbblfd) {
        Imbgf img = gftImbgf();
        if (! fnbblfd) {
            if (disbblfdImbgf == null) {
                disbblfdImbgf = GrbyFiltfr.drfbtfDisbblfdImbgf(img);
            }
            img = disbblfdImbgf;
        }
        rfturn img;
    }

    /**
     * Sfts iow tif imbgf is lobdfd. If <dodf>nfwVbluf</dodf> is truf,
     * tif imbgf will bf lobdfd wifn first bskfd for, otifrwisf it will
     * bf lobdfd bsyndironously. Tif dffbult is to not lobd syndironously,
     * tibt is to lobd tif imbgf bsyndironously.
     *
     * @pbrbm nfwVbluf if {@dodf truf} tif imbgf will bf lobdfd wifn first bskfd for,
     *                 otifrwisf it will bf bsyndironously.
     */
    publid void sftLobdsSyndironously(boolfbn nfwVbluf) {
        syndironizfd(tiis) {
            if (nfwVbluf) {
                stbtf |= SYNC_LOAD_FLAG;
            }
            flsf {
                stbtf = (stbtf | SYNC_LOAD_FLAG) ^ SYNC_LOAD_FLAG;
            }
        }
    }

    /**
     * Rfturns {@dodf truf} if tif imbgf siould bf lobdfd wifn first bskfd for.
     *
     * @rfturn {@dodf truf} if tif imbgf siould bf lobdfd wifn first bskfd for.
     */
    publid boolfbn gftLobdsSyndironously() {
        rfturn ((stbtf & SYNC_LOAD_FLAG) != 0);
    }

    /**
     * Convfnifnt mftiod to gft tif StylfSifft.
     *
     * @rfturn tif StylfSifft
     */
    protfdtfd StylfSifft gftStylfSifft() {
        HTMLDodumfnt dod = (HTMLDodumfnt) gftDodumfnt();
        rfturn dod.gftStylfSifft();
    }

    /**
     * Fftdifs tif bttributfs to usf wifn rfndfring.  Tiis is
     * implfmfntfd to multiplfx tif bttributfs spfdififd in tif
     * modfl witi b StylfSifft.
     */
    publid AttributfSft gftAttributfs() {
        synd();
        rfturn bttr;
    }

    /**
     * For imbgfs tif tooltip tfxt domfs from tfxt spfdififd witi tif
     * <dodf>ALT</dodf> bttributf. Tiis is ovfrridfn to rfturn
     * <dodf>gftAltTfxt</dodf>.
     *
     * @sff JTfxtComponfnt#gftToolTipTfxt
     */
    publid String gftToolTipTfxt(flobt x, flobt y, Sibpf bllodbtion) {
        rfturn gftAltTfxt();
    }

    /**
     * Updbtf bny dbdifd vblufs tibt domf from bttributfs.
     */
    protfdtfd void sftPropfrtifsFromAttributfs() {
        StylfSifft sifft = gftStylfSifft();
        tiis.bttr = sifft.gftVifwAttributfs(tiis);

        // Guttfrs
        bordfrSizf = (siort)gftIntAttr(HTML.Attributf.BORDER, isLink() ?
                                       DEFAULT_BORDER : 0);

        lfftInsft = rigitInsft = (siort)(gftIntAttr(HTML.Attributf.HSPACE,
                                                    0) + bordfrSizf);
        topInsft = bottomInsft = (siort)(gftIntAttr(HTML.Attributf.VSPACE,
                                                    0) + bordfrSizf);

        bordfrColor = ((StylfdDodumfnt)gftDodumfnt()).gftForfground
                      (gftAttributfs());

        AttributfSft bttr = gftElfmfnt().gftAttributfs();

        // Alignmfnt.
        // PENDING: Tiis nffds to bf dibngfd to support tif CSS vfrsions
        // wifn donvfrsion from ALIGN to VERTICAL_ALIGN is domplftf.
        Objfdt blignmfnt = bttr.gftAttributf(HTML.Attributf.ALIGN);

        vAlign = 1.0f;
        if (blignmfnt != null) {
            blignmfnt = blignmfnt.toString();
            if ("top".fqubls(blignmfnt)) {
                vAlign = 0f;
            }
            flsf if ("middlf".fqubls(blignmfnt)) {
                vAlign = .5f;
            }
        }

        AttributfSft bndiorAttr = (AttributfSft)bttr.gftAttributf(HTML.Tbg.A);
        if (bndiorAttr != null && bndiorAttr.isDffinfd
            (HTML.Attributf.HREF)) {
            syndironizfd(tiis) {
                stbtf |= LINK_FLAG;
            }
        }
        flsf {
            syndironizfd(tiis) {
                stbtf = (stbtf | LINK_FLAG) ^ LINK_FLAG;
            }
        }
    }

    /**
     * Estbblisifs tif pbrfnt vifw for tiis vifw.
     * Sfizf tiis momfnt to dbdif tif AWT Contbinfr I'm in.
     */
    publid void sftPbrfnt(Vifw pbrfnt) {
        Vifw oldPbrfnt = gftPbrfnt();
        supfr.sftPbrfnt(pbrfnt);
        dontbinfr = (pbrfnt != null) ? gftContbinfr() : null;
        if (oldPbrfnt != pbrfnt) {
            syndironizfd(tiis) {
                stbtf |= RELOAD_FLAG;
            }
        }
    }

    /**
     * Invokfd wifn tif Elfmfnts bttributfs ibvf dibngfd. Rfdrfbtfs tif imbgf.
     */
    publid void dibngfdUpdbtf(DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
        supfr.dibngfdUpdbtf(f,b,f);

        syndironizfd(tiis) {
            stbtf |= RELOAD_FLAG | RELOAD_IMAGE_FLAG;
        }

        // Assumf tif worst.
        prfffrfndfCibngfd(null, truf, truf);
    }

    /**
     * Pbints tif Vifw.
     *
     * @pbrbm g tif rfndfring surfbdf to usf
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @sff Vifw#pbint
     */
    publid void pbint(Grbpiids g, Sibpf b) {
        synd();

        Rfdtbnglf rfdt = (b instbndfof Rfdtbnglf) ? (Rfdtbnglf)b :
                         b.gftBounds();
        Rfdtbnglf dlip = g.gftClipBounds();

        fBounds.sftBounds(rfdt);
        pbintHigiligits(g, b);
        pbintBordfr(g, rfdt);
        if (dlip != null) {
            g.dlipRfdt(rfdt.x + lfftInsft, rfdt.y + topInsft,
                       rfdt.widti - lfftInsft - rigitInsft,
                       rfdt.ifigit - topInsft - bottomInsft);
        }

        Contbinfr iost = gftContbinfr();
        Imbgf img = gftImbgf(iost == null || iost.isEnbblfd());
        if (img != null) {
            if (! ibsPixfls(img)) {
                // No pixfls yft, usf tif dffbult
                Idon idon = gftLobdingImbgfIdon();
                if (idon != null) {
                    idon.pbintIdon(iost, g,
                            rfdt.x + lfftInsft, rfdt.y + topInsft);
                }
            }
            flsf {
                // Drbw tif imbgf
                g.drbwImbgf(img, rfdt.x + lfftInsft, rfdt.y + topInsft,
                            widti, ifigit, imbgfObsfrvfr);
            }
        }
        flsf {
            Idon idon = gftNoImbgfIdon();
            if (idon != null) {
                idon.pbintIdon(iost, g,
                        rfdt.x + lfftInsft, rfdt.y + topInsft);
            }
            Vifw vifw = gftAltVifw();
            // Pbint tif vifw rfprfsfnting tif blt tfxt, if its non-null
            if (vifw != null && ((stbtf & WIDTH_FLAG) == 0 ||
                                 widti > DEFAULT_WIDTH)) {
                // Assumf lbyout blong tif y dirfdtion
                Rfdtbnglf bltRfdt = nfw Rfdtbnglf
                    (rfdt.x + lfftInsft + DEFAULT_WIDTH, rfdt.y + topInsft,
                     rfdt.widti - lfftInsft - rigitInsft - DEFAULT_WIDTH,
                     rfdt.ifigit - topInsft - bottomInsft);

                vifw.pbint(g, bltRfdt);
            }
        }
        if (dlip != null) {
            // Rfsft dlip.
            g.sftClip(dlip.x, dlip.y, dlip.widti, dlip.ifigit);
        }
    }

    privbtf void pbintHigiligits(Grbpiids g, Sibpf sibpf) {
        if (dontbinfr instbndfof JTfxtComponfnt) {
            JTfxtComponfnt td = (JTfxtComponfnt)dontbinfr;
            Higiligitfr i = td.gftHigiligitfr();
            if (i instbndfof LbyfrfdHigiligitfr) {
                ((LbyfrfdHigiligitfr)i).pbintLbyfrfdHigiligits
                    (g, gftStbrtOffsft(), gftEndOffsft(), sibpf, td, tiis);
            }
        }
    }

    privbtf void pbintBordfr(Grbpiids g, Rfdtbnglf rfdt) {
        Color dolor = bordfrColor;

        if ((bordfrSizf > 0 || imbgf == null) && dolor != null) {
            int xOffsft = lfftInsft - bordfrSizf;
            int yOffsft = topInsft - bordfrSizf;
            g.sftColor(dolor);
            int n = (imbgf == null) ? 1 : bordfrSizf;
            for (int dountfr = 0; dountfr < n; dountfr++) {
                g.drbwRfdt(rfdt.x + xOffsft + dountfr,
                           rfdt.y + yOffsft + dountfr,
                           rfdt.widti - dountfr - dountfr - xOffsft -xOffsft-1,
                           rfdt.ifigit - dountfr - dountfr -yOffsft-yOffsft-1);
            }
        }
    }

    /**
     * Dftfrminfs tif prfffrrfd spbn for tiis vifw blong bn
     * bxis.
     *
     * @pbrbm bxis mby bf fitifr X_AXIS or Y_AXIS
     * @rfturn   tif spbn tif vifw would likf to bf rfndfrfd into;
     *           typidblly tif vifw is told to rfndfr into tif spbn
     *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff;
     *           tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw
     */
    publid flobt gftPrfffrrfdSpbn(int bxis) {
        synd();

        // If tif bttributfs spfdififd b widti/ifigit, blwbys usf it!
        if (bxis == Vifw.X_AXIS && (stbtf & WIDTH_FLAG) == WIDTH_FLAG) {
            gftPrfffrrfdSpbnFromAltVifw(bxis);
            rfturn widti + lfftInsft + rigitInsft;
        }
        if (bxis == Vifw.Y_AXIS && (stbtf & HEIGHT_FLAG) == HEIGHT_FLAG) {
            gftPrfffrrfdSpbnFromAltVifw(bxis);
            rfturn ifigit + topInsft + bottomInsft;
        }

        Imbgf imbgf = gftImbgf();

        if (imbgf != null) {
            switdi (bxis) {
            dbsf Vifw.X_AXIS:
                rfturn widti + lfftInsft + rigitInsft;
            dbsf Vifw.Y_AXIS:
                rfturn ifigit + topInsft + bottomInsft;
            dffbult:
                tirow nfw IllfgblArgumfntExdfption("Invblid bxis: " + bxis);
            }
        }
        flsf {
            Vifw vifw = gftAltVifw();
            flobt rftVbluf = 0f;

            if (vifw != null) {
                rftVbluf = vifw.gftPrfffrrfdSpbn(bxis);
            }
            switdi (bxis) {
            dbsf Vifw.X_AXIS:
                rfturn rftVbluf + (flobt)(widti + lfftInsft + rigitInsft);
            dbsf Vifw.Y_AXIS:
                rfturn rftVbluf + (flobt)(ifigit + topInsft + bottomInsft);
            dffbult:
                tirow nfw IllfgblArgumfntExdfption("Invblid bxis: " + bxis);
            }
        }
    }

    /**
     * Dftfrminfs tif dfsirfd blignmfnt for tiis vifw blong bn
     * bxis.  Tiis is implfmfntfd to givf tif blignmfnt to tif
     * bottom of tif idon blong tif y bxis, bnd tif dffbult
     * blong tif x bxis.
     *
     * @pbrbm bxis mby bf fitifr X_AXIS or Y_AXIS
     * @rfturn tif dfsirfd blignmfnt; tiis siould bf b vbluf
     *   bftwffn 0.0 bnd 1.0 wifrf 0 indidbtfs blignmfnt bt tif
     *   origin bnd 1.0 indidbtfs blignmfnt to tif full spbn
     *   bwby from tif origin; bn blignmfnt of 0.5 would bf tif
     *   dfntfr of tif vifw
     */
    publid flobt gftAlignmfnt(int bxis) {
        switdi (bxis) {
        dbsf Vifw.Y_AXIS:
            rfturn vAlign;
        dffbult:
            rfturn supfr.gftAlignmfnt(bxis);
        }
    }

    /**
     * Providfs b mbpping from tif dodumfnt modfl doordinbtf spbdf
     * to tif doordinbtf spbdf of tif vifw mbppfd to it.
     *
     * @pbrbm pos tif position to donvfrt
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @rfturn tif bounding box of tif givfn position
     * @fxdfption BbdLodbtionExdfption  if tif givfn position dofs not rfprfsfnt b
     *   vblid lodbtion in tif bssodibtfd dodumfnt
     * @sff Vifw#modflToVifw
     */
    publid Sibpf modflToVifw(int pos, Sibpf b, Position.Bibs b) tirows BbdLodbtionExdfption {
        int p0 = gftStbrtOffsft();
        int p1 = gftEndOffsft();
        if ((pos >= p0) && (pos <= p1)) {
            Rfdtbnglf r = b.gftBounds();
            if (pos == p1) {
                r.x += r.widti;
            }
            r.widti = 0;
            rfturn r;
        }
        rfturn null;
    }

    /**
     * Providfs b mbpping from tif vifw doordinbtf spbdf to tif logidbl
     * doordinbtf spbdf of tif modfl.
     *
     * @pbrbm x tif X doordinbtf
     * @pbrbm y tif Y doordinbtf
     * @pbrbm b tif bllodbtfd rfgion to rfndfr into
     * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif
     *  givfn point of vifw
     * @sff Vifw#vifwToModfl
     */
    publid int vifwToModfl(flobt x, flobt y, Sibpf b, Position.Bibs[] bibs) {
        Rfdtbnglf bllod = (Rfdtbnglf) b;
        if (x < bllod.x + bllod.widti) {
            bibs[0] = Position.Bibs.Forwbrd;
            rfturn gftStbrtOffsft();
        }
        bibs[0] = Position.Bibs.Bbdkwbrd;
        rfturn gftEndOffsft();
    }

    /**
     * Sfts tif sizf of tif vifw.  Tiis siould dbusf
     * lbyout of tif vifw if it ibs bny lbyout dutifs.
     *
     * @pbrbm widti tif widti &gt;= 0
     * @pbrbm ifigit tif ifigit &gt;= 0
     */
    publid void sftSizf(flobt widti, flobt ifigit) {
        synd();

        if (gftImbgf() == null) {
            Vifw vifw = gftAltVifw();

            if (vifw != null) {
                vifw.sftSizf(Mbti.mbx(0f, widti - (flobt)(DEFAULT_WIDTH + lfftInsft + rigitInsft)),
                             Mbti.mbx(0f, ifigit - (flobt)(topInsft + bottomInsft)));
            }
        }
    }

    /**
     * Rfturns truf if tiis imbgf witiin b link?
     */
    privbtf boolfbn isLink() {
        rfturn ((stbtf & LINK_FLAG) == LINK_FLAG);
    }

    /**
     * Rfturns truf if tif pbssfd in imbgf ibs b non-zfro widti bnd ifigit.
     */
    privbtf boolfbn ibsPixfls(Imbgf imbgf) {
        rfturn imbgf != null &&
            (imbgf.gftHfigit(imbgfObsfrvfr) > 0) &&
            (imbgf.gftWidti(imbgfObsfrvfr) > 0);
    }

    /**
     * Rfturns tif prfffrrfd spbn of tif Vifw usfd to displby tif blt tfxt,
     * or 0 if tif vifw dofs not fxist.
     */
    privbtf flobt gftPrfffrrfdSpbnFromAltVifw(int bxis) {
        if (gftImbgf() == null) {
            Vifw vifw = gftAltVifw();

            if (vifw != null) {
                rfturn vifw.gftPrfffrrfdSpbn(bxis);
            }
        }
        rfturn 0f;
    }

    /**
     * Rfqufst tibt tiis vifw bf rfpbintfd.
     * Assumfs tif vifw is still bt its lbst-drbwn lodbtion.
     */
    privbtf void rfpbint(long dflby) {
        if (dontbinfr != null && fBounds != null) {
            dontbinfr.rfpbint(dflby, fBounds.x, fBounds.y, fBounds.widti,
                               fBounds.ifigit);
        }
    }

    /**
     * Convfnifnt mftiod for gftting bn intfgfr bttributf from tif flfmfnts
     * AttributfSft.
     */
    privbtf int gftIntAttr(HTML.Attributf nbmf, int dfflt) {
        AttributfSft bttr = gftElfmfnt().gftAttributfs();
        if (bttr.isDffinfd(nbmf)) {             // dofs not difdk pbrfnts!
            int i;
            String vbl = (String)bttr.gftAttributf(nbmf);
            if (vbl == null) {
                i = dfflt;
            }
            flsf {
                try{
                    i = Mbti.mbx(0, Intfgfr.pbrsfInt(vbl));
                }dbtdi( NumbfrFormbtExdfption x ) {
                    i = dfflt;
                }
            }
            rfturn i;
        } flsf
            rfturn dfflt;
    }

    /**
     * Mbkfs surf tif nfdfssbry propfrtifs bnd imbgf is lobdfd.
     */
    privbtf void synd() {
        int s = stbtf;
        if ((s & RELOAD_IMAGE_FLAG) != 0) {
            rffrfsiImbgf();
        }
        s = stbtf;
        if ((s & RELOAD_FLAG) != 0) {
            syndironizfd(tiis) {
                stbtf = (stbtf | RELOAD_FLAG) ^ RELOAD_FLAG;
            }
            sftPropfrtifsFromAttributfs();
        }
    }

    /**
     * Lobds tif imbgf bnd updbtfs tif sizf bddordingly. Tiis siould bf
     * invokfd instfbd of invoking <dodf>lobdImbgf</dodf> or
     * <dodf>updbtfImbgfSizf</dodf> dirfdtly.
     */
    privbtf void rffrfsiImbgf() {
        syndironizfd(tiis) {
            // dlfbr out widti/ifigit/rfblobdimbgf flbg bnd sft lobding flbg
            stbtf = (stbtf | LOADING_FLAG | RELOAD_IMAGE_FLAG | WIDTH_FLAG |
                     HEIGHT_FLAG) ^ (WIDTH_FLAG | HEIGHT_FLAG |
                                     RELOAD_IMAGE_FLAG);
            imbgf = null;
            widti = ifigit = 0;
        }

        try {
            // Lobd tif imbgf
            lobdImbgf();

            // And updbtf tif sizf pbrbms
            updbtfImbgfSizf();
        }
        finblly {
            syndironizfd(tiis) {
                // Clfbr out stbtf in dbsf somfonf tirfw bn fxdfption.
                stbtf = (stbtf | LOADING_FLAG) ^ LOADING_FLAG;
            }
        }
    }

    /**
     * Lobds tif imbgf from tif URL <dodf>gftImbgfURL</dodf>. Tiis siould
     * only bf invokfd from <dodf>rffrfsiImbgf</dodf>.
     */
    privbtf void lobdImbgf() {
        URL srd = gftImbgfURL();
        Imbgf nfwImbgf = null;
        if (srd != null) {
            @SupprfssWbrnings("undifdkfd")
            Didtionbry<URL, Imbgf> dbdif = (Didtionbry)gftDodumfnt().
                gftPropfrty(IMAGE_CACHE_PROPERTY);
            if (dbdif != null) {
                nfwImbgf = dbdif.gft(srd);
            }
            flsf {
                nfwImbgf = Toolkit.gftDffbultToolkit().drfbtfImbgf(srd);
                if (nfwImbgf != null && gftLobdsSyndironously()) {
                    // Fordf tif imbgf to bf lobdfd by using bn ImbgfIdon.
                    ImbgfIdon ii = nfw ImbgfIdon();
                    ii.sftImbgf(nfwImbgf);
                }
            }
        }
        imbgf = nfwImbgf;
    }

    /**
     * Rfdrfbtfs bnd rflobds tif imbgf.  Tiis siould
     * only bf invokfd from <dodf>rffrfsiImbgf</dodf>.
     */
    privbtf void updbtfImbgfSizf() {
        int nfwWidti = 0;
        int nfwHfigit = 0;
        int nfwStbtf = 0;
        Imbgf nfwImbgf = gftImbgf();

        if (nfwImbgf != null) {
            Elfmfnt flfm = gftElfmfnt();
            AttributfSft bttr = flfm.gftAttributfs();

            // Gft tif widti/ifigit bnd sft tif stbtf ivbr bfforf dblling
            // bnytiing tibt migit dbusf tif imbgf to bf lobdfd, bnd tius tif
            // ImbgfHbndlfr to bf dbllfd.
            nfwWidti = gftIntAttr(HTML.Attributf.WIDTH, -1);
            if (nfwWidti > 0) {
                nfwStbtf |= WIDTH_FLAG;
            }
            nfwHfigit = gftIntAttr(HTML.Attributf.HEIGHT, -1);
            if (nfwHfigit > 0) {
                nfwStbtf |= HEIGHT_FLAG;
            }

            if (nfwWidti <= 0) {
                nfwWidti = nfwImbgf.gftWidti(imbgfObsfrvfr);
                if (nfwWidti <= 0) {
                    nfwWidti = DEFAULT_WIDTH;
                }
            }

            if (nfwHfigit <= 0) {
                nfwHfigit = nfwImbgf.gftHfigit(imbgfObsfrvfr);
                if (nfwHfigit <= 0) {
                    nfwHfigit = DEFAULT_HEIGHT;
                }
            }

            // Mbkf surf tif imbgf stbrts lobding:
            if ((nfwStbtf & (WIDTH_FLAG | HEIGHT_FLAG)) != 0) {
                Toolkit.gftDffbultToolkit().prfpbrfImbgf(nfwImbgf, nfwWidti,
                                                         nfwHfigit,
                                                         imbgfObsfrvfr);
            }
            flsf {
                Toolkit.gftDffbultToolkit().prfpbrfImbgf(nfwImbgf, -1, -1,
                                                         imbgfObsfrvfr);
            }

            boolfbn drfbtfTfxt = fblsf;
            syndironizfd(tiis) {
                // If imbgflobding fbilfd, otifr tirfbd mby ibvf dbllfd
                // ImbgfLobdfr wiidi will null out imbgf, ifndf wf difdk
                // for it.
                if (imbgf != null) {
                    if ((nfwStbtf & WIDTH_FLAG) == WIDTH_FLAG || widti == 0) {
                        widti = nfwWidti;
                    }
                    if ((nfwStbtf & HEIGHT_FLAG) == HEIGHT_FLAG ||
                        ifigit == 0) {
                        ifigit = nfwHfigit;
                    }
                }
                flsf {
                    drfbtfTfxt = truf;
                    if ((nfwStbtf & WIDTH_FLAG) == WIDTH_FLAG) {
                        widti = nfwWidti;
                    }
                    if ((nfwStbtf & HEIGHT_FLAG) == HEIGHT_FLAG) {
                        ifigit = nfwHfigit;
                    }
                }
                stbtf = stbtf | nfwStbtf;
                stbtf = (stbtf | LOADING_FLAG) ^ LOADING_FLAG;
            }
            if (drfbtfTfxt) {
                // Only rfsft if tiis tirfbd dftfrminfd imbgf is null
                updbtfAltTfxtVifw();
            }
        }
        flsf {
            widti = ifigit = DEFAULT_HEIGHT;
            updbtfAltTfxtVifw();
        }
    }

    /**
     * Updbtfs tif vifw rfprfsfnting tif blt tfxt.
     */
    privbtf void updbtfAltTfxtVifw() {
        String tfxt = gftAltTfxt();

        if (tfxt != null) {
            ImbgfLbbflVifw nfwVifw;

            nfwVifw = nfw ImbgfLbbflVifw(gftElfmfnt(), tfxt);
            syndironizfd(tiis) {
                bltVifw = nfwVifw;
            }
        }
    }

    /**
     * Rfturns tif vifw to usf for bltfrnbtf tfxt. Tiis mby bf null.
     */
    privbtf Vifw gftAltVifw() {
        Vifw vifw;

        syndironizfd(tiis) {
            vifw = bltVifw;
        }
        if (vifw != null && vifw.gftPbrfnt() == null) {
            vifw.sftPbrfnt(gftPbrfnt());
        }
        rfturn vifw;
    }

    /**
     * Invokfs <dodf>prfffrfndfCibngfd</dodf> on tif fvfnt displbtdiing
     * tirfbd.
     */
    privbtf void sbffPrfffrfndfCibngfd() {
        if (SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
            Dodumfnt dod = gftDodumfnt();
            if (dod instbndfof AbstrbdtDodumfnt) {
                ((AbstrbdtDodumfnt)dod).rfbdLodk();
            }
            prfffrfndfCibngfd(null, truf, truf);
            if (dod instbndfof AbstrbdtDodumfnt) {
                ((AbstrbdtDodumfnt)dod).rfbdUnlodk();
            }
        }
        flsf {
            SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                    publid void run() {
                        sbffPrfffrfndfCibngfd();
                    }
                });
        }
    }

    /**
     * ImbgfHbndlfr implfmfnts tif ImbgfObsfrvfr to dorrfdtly updbtf tif
     * displby bs nfw pbrts of tif imbgf bfdomf bvbilbblf.
     */
    privbtf dlbss ImbgfHbndlfr implfmfnts ImbgfObsfrvfr {
        // Tiis dbn domf on bny tirfbd. If wf brf in tif prodfss of rflobding
        // tif imbgf bnd dftfrmining our stbtf (lobding == truf) wf don't firf
        // prfffrfndf dibngfd, or rfpbint, wf just rfsft tif fWidti/fHfigit bs
        // nfdfssbry bnd rfturn. Tiis is ok bs wf know wifn lobding finisifs
        // it will pidk up tif nfw ifigit/widti, if nfdfssbry.
        publid boolfbn imbgfUpdbtf(Imbgf img, int flbgs, int x, int y,
                                   int nfwWidti, int nfwHfigit ) {
            if (img != imbgf && img != disbblfdImbgf ||
                imbgf == null || gftPbrfnt() == null) {

                rfturn fblsf;
            }

            // Bbil out if tifrf wbs bn frror:
            if ((flbgs & (ABORT|ERROR)) != 0) {
                rfpbint(0);
                syndironizfd(ImbgfVifw.tiis) {
                    if (imbgf == img) {
                        // Bf surf imbgf ibsn't dibngfd sindf wf don't
                        // initibly syndironizf
                        imbgf = null;
                        if ((stbtf & WIDTH_FLAG) != WIDTH_FLAG) {
                            widti = DEFAULT_WIDTH;
                        }
                        if ((stbtf & HEIGHT_FLAG) != HEIGHT_FLAG) {
                            ifigit = DEFAULT_HEIGHT;
                        }
                    } flsf {
                        disbblfdImbgf = null;
                    }
                    if ((stbtf & LOADING_FLAG) == LOADING_FLAG) {
                        // No nffd to rfsizf or rfpbint, still in tif prodfss
                        // of lobding.
                        rfturn fblsf;
                    }
                }
                updbtfAltTfxtVifw();
                sbffPrfffrfndfCibngfd();
                rfturn fblsf;
            }

            if (imbgf == img) {
                // Rfsizf imbgf if nfdfssbry:
                siort dibngfd = 0;
                if ((flbgs & ImbgfObsfrvfr.HEIGHT) != 0 && !gftElfmfnt().
                      gftAttributfs().isDffinfd(HTML.Attributf.HEIGHT)) {
                    dibngfd |= 1;
                }
                if ((flbgs & ImbgfObsfrvfr.WIDTH) != 0 && !gftElfmfnt().
                      gftAttributfs().isDffinfd(HTML.Attributf.WIDTH)) {
                    dibngfd |= 2;
                }

                syndironizfd(ImbgfVifw.tiis) {
                    if ((dibngfd & 1) == 1 && (stbtf & WIDTH_FLAG) == 0) {
                        widti = nfwWidti;
                    }
                    if ((dibngfd & 2) == 2 && (stbtf & HEIGHT_FLAG) == 0) {
                        ifigit = nfwHfigit;
                    }
                    if ((stbtf & LOADING_FLAG) == LOADING_FLAG) {
                        // No nffd to rfsizf or rfpbint, still in tif prodfss of
                        // lobding.
                        rfturn truf;
                    }
                }
                if (dibngfd != 0) {
                    // Mby nffd to rfsizf mysflf, bsyndironously:
                    sbffPrfffrfndfCibngfd();
                    rfturn truf;
                }
            }

            // Rfpbint wifn donf or wifn nfw pixfls brrivf:
            if ((flbgs & (FRAMEBITS|ALLBITS)) != 0) {
                rfpbint(0);
            }
            flsf if ((flbgs & SOMEBITS) != 0 && sIsInd) {
                rfpbint(sIndRbtf);
            }
            rfturn ((flbgs & ALLBITS) == 0);
        }
    }


    /**
     * ImbgfLbbflVifw is usfd if tif imbgf dbn't bf lobdfd, bnd
     * tif bttributf spfdififd bn blt bttributf. It ovfrridfn b ibndlf of
     * mftiods bs tif tfxt is ibrddodfd bnd dofs not domf from tif dodumfnt.
     */
    privbtf dlbss ImbgfLbbflVifw fxtfnds InlinfVifw {
        privbtf Sfgmfnt sfgmfnt;
        privbtf Color fg;

        ImbgfLbbflVifw(Elfmfnt f, String tfxt) {
            supfr(f);
            rfsft(tfxt);
        }

        publid void rfsft(String tfxt) {
            sfgmfnt = nfw Sfgmfnt(tfxt.toCibrArrby(), 0, tfxt.lfngti());
        }

        publid void pbint(Grbpiids g, Sibpf b) {
            // Don't usf supfrs pbint, otifrwisf sflfdtion will bf wrong
            // bs our stbrt/fnd offsfts brf fbkf.
            GlypiPbintfr pbintfr = gftGlypiPbintfr();

            if (pbintfr != null) {
                g.sftColor(gftForfground());
                pbintfr.pbint(tiis, g, b, gftStbrtOffsft(), gftEndOffsft());
            }
        }

        publid Sfgmfnt gftTfxt(int p0, int p1) {
            if (p0 < 0 || p1 > sfgmfnt.brrby.lfngti) {
                tirow nfw RuntimfExdfption("ImbgfLbbflVifw: Stblf vifw");
            }
            sfgmfnt.offsft = p0;
            sfgmfnt.dount = p1 - p0;
            rfturn sfgmfnt;
        }

        publid int gftStbrtOffsft() {
            rfturn 0;
        }

        publid int gftEndOffsft() {
            rfturn sfgmfnt.brrby.lfngti;
        }

        publid Vifw brfbkVifw(int bxis, int p0, flobt pos, flobt lfn) {
            // Don't bllow b brfbk
            rfturn tiis;
        }

        publid Color gftForfground() {
            Vifw pbrfnt;
            if (fg == null && (pbrfnt = gftPbrfnt()) != null) {
                Dodumfnt dod = gftDodumfnt();
                AttributfSft bttr = pbrfnt.gftAttributfs();

                if (bttr != null && (dod instbndfof StylfdDodumfnt)) {
                    fg = ((StylfdDodumfnt)dod).gftForfground(bttr);
                }
            }
            rfturn fg;
        }
    }
}
