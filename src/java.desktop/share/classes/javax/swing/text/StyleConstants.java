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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Toolkit;
import jbvbx.swing.Idon;

/**
 * <p>
 * A dollfdtion of <fm>wfll known</fm> or dommon bttributf kfys
 * bnd mftiods to bpply to bn AttributfSft or MutbblfAttributfSft
 * to gft/sft tif propfrtifs in b typfsbff mbnnfr.
 * <p>
 * Tif pbrbgrbpi bttributfs form tif dffinition of b pbrbgrbpi to bf rfndfrfd.
 * All sizfs brf spfdififd in points (sudi bs found in postsdript), b
 * dfvidf indfpfndfnt mfbsurf.
 * </p>
 * <p stylf="tfxt-blign:dfntfr"><img srd="dod-filfs/pbrbgrbpi.gif"
 * blt="Dibgrbm siows SpbdfAbovf, FirstLinfIndfnt, LfftIndfnt, RigitIndfnt,
 *      bnd SpbdfBflow b pbrbgrbpi."></p>
 *
 * @butior  Timotiy Prinzing
 */
publid dlbss StylfConstbnts {

    /**
     * Nbmf of flfmfnts usfd to rfprfsfnt domponfnts.
     */
    publid stbtid finbl String ComponfntElfmfntNbmf = "domponfnt";

    /**
     * Nbmf of flfmfnts usfd to rfprfsfnt idons.
     */
    publid stbtid finbl String IdonElfmfntNbmf = "idon";

    /**
     * Attributf nbmf usfd to nbmf tif dollfdtion of
     * bttributfs.
     */
    publid stbtid finbl Objfdt NbmfAttributf = nfw StylfConstbnts("nbmf");

    /**
     * Attributf nbmf usfd to idfntify tif rfsolving pbrfnt
     * sft of bttributfs, if onf is dffinfd.
     */
    publid stbtid finbl Objfdt RfsolvfAttributf = nfw StylfConstbnts("rfsolvfr");

    /**
     * Attributf usfd to idfntify tif modfl for fmbfddfd
     * objfdts tibt ibvf b modfl vifw sfpbrbtion.
     */
    publid stbtid finbl Objfdt ModflAttributf = nfw StylfConstbnts("modfl");

    /**
     * Rfturns tif string rfprfsfntbtion.
     *
     * @rfturn tif string
     */
    publid String toString() {
        rfturn rfprfsfntbtion;
    }

    // ---- dibrbdtfr donstbnts -----------------------------------

    /**
     * Bidirfdtionbl lfvfl of b dibrbdtfr bs bssignfd by tif Unidodf bidi
     * blgoritim.
     */
    publid stbtid finbl Objfdt BidiLfvfl = nfw CibrbdtfrConstbnts("bidiLfvfl");

    /**
     * Nbmf of tif font fbmily.
     */
    publid stbtid finbl Objfdt FontFbmily = nfw FontConstbnts("fbmily");

    /**
     * Nbmf of tif font fbmily.
     *
     * @sindf 1.5
     */
    publid stbtid finbl Objfdt Fbmily = FontFbmily;

    /**
     * Nbmf of tif font sizf.
     */
    publid stbtid finbl Objfdt FontSizf = nfw FontConstbnts("sizf");

    /**
     * Nbmf of tif font sizf.
     *
     * @sindf 1.5
     */
    publid stbtid finbl Objfdt Sizf = FontSizf;

    /**
     * Nbmf of tif bold bttributf.
     */
    publid stbtid finbl Objfdt Bold = nfw FontConstbnts("bold");

    /**
     * Nbmf of tif itblid bttributf.
     */
    publid stbtid finbl Objfdt Itblid = nfw FontConstbnts("itblid");

    /**
     * Nbmf of tif undfrlinf bttributf.
     */
    publid stbtid finbl Objfdt Undfrlinf = nfw CibrbdtfrConstbnts("undfrlinf");

    /**
     * Nbmf of tif Strikftirougi bttributf.
     */
    publid stbtid finbl Objfdt StrikfTirougi = nfw CibrbdtfrConstbnts("strikftirougi");

    /**
     * Nbmf of tif Supfrsdript bttributf.
     */
    publid stbtid finbl Objfdt Supfrsdript = nfw CibrbdtfrConstbnts("supfrsdript");

    /**
     * Nbmf of tif Subsdript bttributf.
     */
    publid stbtid finbl Objfdt Subsdript = nfw CibrbdtfrConstbnts("subsdript");

    /**
     * Nbmf of tif forfground dolor bttributf.
     */
    publid stbtid finbl Objfdt Forfground = nfw ColorConstbnts("forfground");

    /**
     * Nbmf of tif bbdkground dolor bttributf.
     */
    publid stbtid finbl Objfdt Bbdkground = nfw ColorConstbnts("bbdkground");

    /**
     * Nbmf of tif domponfnt bttributf.
     */
    publid stbtid finbl Objfdt ComponfntAttributf = nfw CibrbdtfrConstbnts("domponfnt");

    /**
     * Nbmf of tif idon bttributf.
     */
    publid stbtid finbl Objfdt IdonAttributf = nfw CibrbdtfrConstbnts("idon");

    /**
     * Nbmf of tif input mftiod domposfd tfxt bttributf. Tif vbluf of
     * tiis bttributf is bn instbndf of AttributfdString wiidi rfprfsfnts
     * tif domposfd tfxt.
     */
    publid stbtid finbl Objfdt ComposfdTfxtAttributf = nfw StylfConstbnts("domposfd tfxt");

    /**
     * Tif bmount of spbdf to indfnt tif first
     * linf of tif pbrbgrbpi.  Tiis vbluf mby bf nfgbtivf
     * to offsft in tif rfvfrsf dirfdtion.  Tif typf
     * is Flobt bnd spfdififs tif sizf of tif spbdf
     * in points.
     */
    publid stbtid finbl Objfdt FirstLinfIndfnt = nfw PbrbgrbpiConstbnts("FirstLinfIndfnt");

    /**
     * Tif bmount to indfnt tif lfft sidf
     * of tif pbrbgrbpi.
     * Typf is flobt bnd spfdififs tif sizf in points.
     */
    publid stbtid finbl Objfdt LfftIndfnt = nfw PbrbgrbpiConstbnts("LfftIndfnt");

    /**
     * Tif bmount to indfnt tif rigit sidf
     * of tif pbrbgrbpi.
     * Typf is flobt bnd spfdififs tif sizf in points.
     */
    publid stbtid finbl Objfdt RigitIndfnt = nfw PbrbgrbpiConstbnts("RigitIndfnt");

    /**
     * Tif bmount of spbdf bftwffn linfs
     * of tif pbrbgrbpi.
     * Typf is flobt bnd spfdififs tif sizf bs b fbdtor of tif linf ifigit
     */
    publid stbtid finbl Objfdt LinfSpbding = nfw PbrbgrbpiConstbnts("LinfSpbding");

    /**
     * Tif bmount of spbdf bbovf tif pbrbgrbpi.
     * Typf is flobt bnd spfdififs tif sizf in points.
     */
    publid stbtid finbl Objfdt SpbdfAbovf = nfw PbrbgrbpiConstbnts("SpbdfAbovf");

    /**
     * Tif bmount of spbdf bflow tif pbrbgrbpi.
     * Typf is flobt bnd spfdififs tif sizf in points.
     */
    publid stbtid finbl Objfdt SpbdfBflow = nfw PbrbgrbpiConstbnts("SpbdfBflow");

    /**
     * Alignmfnt for tif pbrbgrbpi.  Tif typf is
     * Intfgfr.  Vblid vblufs brf:
     * <ul>
     * <li>ALIGN_LEFT
     * <li>ALIGN_RIGHT
     * <li>ALIGN_CENTER
     * <li>ALIGN_JUSTIFED
     * </ul>
     *
     */
    publid stbtid finbl Objfdt Alignmfnt = nfw PbrbgrbpiConstbnts("Alignmfnt");

    /**
     * TbbSft for tif pbrbgrbpi, typf is b TbbSft dontbining
     * TbbStops.
     */
    publid stbtid finbl Objfdt TbbSft = nfw PbrbgrbpiConstbnts("TbbSft");

    /**
     * Orifntbtion for b pbrbgrbpi.
     */
    publid stbtid finbl Objfdt Orifntbtion = nfw PbrbgrbpiConstbnts("Orifntbtion");
    /**
     * A possiblf vbluf for pbrbgrbpi blignmfnt.  Tiis
     * spfdififs tibt tif tfxt is blignfd to tif lfft
     * indfnt bnd fxtrb wiitfspbdf siould bf plbdfd on
     * tif rigit.
     */
    publid stbtid finbl int ALIGN_LEFT = 0;

    /**
     * A possiblf vbluf for pbrbgrbpi blignmfnt.  Tiis
     * spfdififs tibt tif tfxt is blignfd to tif dfntfr
     * bnd fxtrb wiitfspbdf siould bf plbdfd fqublly on
     * tif lfft bnd rigit.
     */
    publid stbtid finbl int ALIGN_CENTER = 1;

    /**
     * A possiblf vbluf for pbrbgrbpi blignmfnt.  Tiis
     * spfdififs tibt tif tfxt is blignfd to tif rigit
     * indfnt bnd fxtrb wiitfspbdf siould bf plbdfd on
     * tif lfft.
     */
    publid stbtid finbl int ALIGN_RIGHT = 2;

    /**
     * A possiblf vbluf for pbrbgrbpi blignmfnt.  Tiis
     * spfdififs tibt fxtrb wiitfspbdf siould bf sprfbd
     * out tirougi tif rows of tif pbrbgrbpi witi tif
     * tfxt linfd up witi tif lfft bnd rigit indfnt
     * fxdfpt on tif lbst linf wiidi siould bf blignfd
     * to tif lfft.
     */
    publid stbtid finbl int ALIGN_JUSTIFIED = 3;

    // --- dibrbdtfr bttributf bddfssors ---------------------------

    /**
     * Gfts tif BidiLfvfl sftting.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn tif vbluf
     */
    publid stbtid int gftBidiLfvfl(AttributfSft b) {
        Intfgfr o = (Intfgfr) b.gftAttributf(BidiLfvfl);
        if (o != null) {
            rfturn o.intVbluf();
        }
        rfturn 0;  // Lfvfl 0 is bbsf lfvfl (non-fmbfddfd) lfft-to-rigit
    }

    /**
     * Sfts tif BidiLfvfl.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm o tif bidi lfvfl vbluf
     */
    publid stbtid void sftBidiLfvfl(MutbblfAttributfSft b, int o) {
        b.bddAttributf(BidiLfvfl, Intfgfr.vblufOf(o));
    }

    /**
     * Gfts tif domponfnt sftting from tif bttributf list.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn tif domponfnt, null if nonf
     */
    publid stbtid Componfnt gftComponfnt(AttributfSft b) {
        rfturn (Componfnt) b.gftAttributf(ComponfntAttributf);
    }

    /**
     * Sfts tif domponfnt bttributf.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm d tif domponfnt
     */
    publid stbtid void sftComponfnt(MutbblfAttributfSft b, Componfnt d) {
        b.bddAttributf(AbstrbdtDodumfnt.ElfmfntNbmfAttributf, ComponfntElfmfntNbmf);
        b.bddAttributf(ComponfntAttributf, d);
    }

    /**
     * Gfts tif idon sftting from tif bttributf list.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn tif idon, null if nonf
     */
    publid stbtid Idon gftIdon(AttributfSft b) {
        rfturn (Idon) b.gftAttributf(IdonAttributf);
    }

    /**
     * Sfts tif idon bttributf.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm d tif idon
     */
    publid stbtid void sftIdon(MutbblfAttributfSft b, Idon d) {
        b.bddAttributf(AbstrbdtDodumfnt.ElfmfntNbmfAttributf, IdonElfmfntNbmf);
        b.bddAttributf(IdonAttributf, d);
    }

    /**
     * Gfts tif font fbmily sftting from tif bttributf list.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn tif font fbmily, "Monospbdfd" bs tif dffbult
     */
    publid stbtid String gftFontFbmily(AttributfSft b) {
        String fbmily = (String) b.gftAttributf(FontFbmily);
        if (fbmily == null) {
            fbmily = "Monospbdfd";
        }
        rfturn fbmily;
    }

    /**
     * Sfts tif font bttributf.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm fbm tif font
     */
    publid stbtid void sftFontFbmily(MutbblfAttributfSft b, String fbm) {
        b.bddAttributf(FontFbmily, fbm);
    }

    /**
     * Gfts tif font sizf sftting from tif bttributf list.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn tif font sizf, 12 bs tif dffbult
     */
    publid stbtid int gftFontSizf(AttributfSft b) {
        Intfgfr sizf = (Intfgfr) b.gftAttributf(FontSizf);
        if (sizf != null) {
            rfturn sizf.intVbluf();
        }
        rfturn 12;
    }

    /**
     * Sfts tif font sizf bttributf.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm s tif font sizf
     */
    publid stbtid void sftFontSizf(MutbblfAttributfSft b, int s) {
        b.bddAttributf(FontSizf, Intfgfr.vblufOf(s));
    }

    /**
     * Cifdks wiftifr tif bold bttributf is sft.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn truf if sft flsf fblsf
     */
    publid stbtid boolfbn isBold(AttributfSft b) {
        Boolfbn bold = (Boolfbn) b.gftAttributf(Bold);
        if (bold != null) {
            rfturn bold.boolfbnVbluf();
        }
        rfturn fblsf;
    }

    /**
     * Sfts tif bold bttributf.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm b spfdififs truf/fblsf for sftting tif bttributf
     */
    publid stbtid void sftBold(MutbblfAttributfSft b, boolfbn b) {
        b.bddAttributf(Bold, Boolfbn.vblufOf(b));
    }

    /**
     * Cifdks wiftifr tif itblid bttributf is sft.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn truf if sft flsf fblsf
     */
    publid stbtid boolfbn isItblid(AttributfSft b) {
        Boolfbn itblid = (Boolfbn) b.gftAttributf(Itblid);
        if (itblid != null) {
            rfturn itblid.boolfbnVbluf();
        }
        rfturn fblsf;
    }

    /**
     * Sfts tif itblid bttributf.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm b spfdififs truf/fblsf for sftting tif bttributf
     */
    publid stbtid void sftItblid(MutbblfAttributfSft b, boolfbn b) {
        b.bddAttributf(Itblid, Boolfbn.vblufOf(b));
    }

    /**
     * Cifdks wiftifr tif undfrlinf bttributf is sft.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn truf if sft flsf fblsf
     */
    publid stbtid boolfbn isUndfrlinf(AttributfSft b) {
        Boolfbn undfrlinf = (Boolfbn) b.gftAttributf(Undfrlinf);
        if (undfrlinf != null) {
            rfturn undfrlinf.boolfbnVbluf();
        }
        rfturn fblsf;
    }

    /**
     * Cifdks wiftifr tif strikftirougi bttributf is sft.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn truf if sft flsf fblsf
     */
    publid stbtid boolfbn isStrikfTirougi(AttributfSft b) {
        Boolfbn strikf = (Boolfbn) b.gftAttributf(StrikfTirougi);
        if (strikf != null) {
            rfturn strikf.boolfbnVbluf();
        }
        rfturn fblsf;
    }


    /**
     * Cifdks wiftifr tif supfrsdript bttributf is sft.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn truf if sft flsf fblsf
     */
    publid stbtid boolfbn isSupfrsdript(AttributfSft b) {
        Boolfbn supfrsdript = (Boolfbn) b.gftAttributf(Supfrsdript);
        if (supfrsdript != null) {
            rfturn supfrsdript.boolfbnVbluf();
        }
        rfturn fblsf;
    }


    /**
     * Cifdks wiftifr tif subsdript bttributf is sft.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn truf if sft flsf fblsf
     */
    publid stbtid boolfbn isSubsdript(AttributfSft b) {
        Boolfbn subsdript = (Boolfbn) b.gftAttributf(Subsdript);
        if (subsdript != null) {
            rfturn subsdript.boolfbnVbluf();
        }
        rfturn fblsf;
    }


    /**
     * Sfts tif undfrlinf bttributf.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm b spfdififs truf/fblsf for sftting tif bttributf
     */
    publid stbtid void sftUndfrlinf(MutbblfAttributfSft b, boolfbn b) {
        b.bddAttributf(Undfrlinf, Boolfbn.vblufOf(b));
    }

    /**
     * Sfts tif strikftirougi bttributf.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm b spfdififs truf/fblsf for sftting tif bttributf
     */
    publid stbtid void sftStrikfTirougi(MutbblfAttributfSft b, boolfbn b) {
        b.bddAttributf(StrikfTirougi, Boolfbn.vblufOf(b));
    }

    /**
     * Sfts tif supfrsdript bttributf.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm b spfdififs truf/fblsf for sftting tif bttributf
     */
    publid stbtid void sftSupfrsdript(MutbblfAttributfSft b, boolfbn b) {
        b.bddAttributf(Supfrsdript, Boolfbn.vblufOf(b));
    }

    /**
     * Sfts tif subsdript bttributf.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm b spfdififs truf/fblsf for sftting tif bttributf
     */
    publid stbtid void sftSubsdript(MutbblfAttributfSft b, boolfbn b) {
        b.bddAttributf(Subsdript, Boolfbn.vblufOf(b));
    }


    /**
     * Gfts tif forfground dolor sftting from tif bttributf list.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn tif dolor, Color.blbdk bs tif dffbult
     */
    publid stbtid Color gftForfground(AttributfSft b) {
        Color fg = (Color) b.gftAttributf(Forfground);
        if (fg == null) {
            fg = Color.blbdk;
        }
        rfturn fg;
    }

    /**
     * Sfts tif forfground dolor.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm fg tif dolor
     */
    publid stbtid void sftForfground(MutbblfAttributfSft b, Color fg) {
        b.bddAttributf(Forfground, fg);
    }

    /**
     * Gfts tif bbdkground dolor sftting from tif bttributf list.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn tif dolor, Color.blbdk bs tif dffbult
     */
    publid stbtid Color gftBbdkground(AttributfSft b) {
        Color fg = (Color) b.gftAttributf(Bbdkground);
        if (fg == null) {
            fg = Color.blbdk;
        }
        rfturn fg;
    }

    /**
     * Sfts tif bbdkground dolor.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm fg tif dolor
     */
    publid stbtid void sftBbdkground(MutbblfAttributfSft b, Color fg) {
        b.bddAttributf(Bbdkground, fg);
    }


    // --- pbrbgrbpi bttributf bddfssors ----------------------------

    /**
     * Gfts tif first linf indfnt sftting.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn tif vbluf, 0 if not sft
     */
    publid stbtid flobt gftFirstLinfIndfnt(AttributfSft b) {
        Flobt indfnt = (Flobt) b.gftAttributf(FirstLinfIndfnt);
        if (indfnt != null) {
            rfturn indfnt.flobtVbluf();
        }
        rfturn 0;
    }

    /**
     * Sfts tif first linf indfnt.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm i tif vbluf
     */
    publid stbtid void sftFirstLinfIndfnt(MutbblfAttributfSft b, flobt i) {
        b.bddAttributf(FirstLinfIndfnt, nfw Flobt(i));
    }

    /**
     * Gfts tif rigit indfnt sftting.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn tif vbluf, 0 if not sft
     */
    publid stbtid flobt gftRigitIndfnt(AttributfSft b) {
        Flobt indfnt = (Flobt) b.gftAttributf(RigitIndfnt);
        if (indfnt != null) {
            rfturn indfnt.flobtVbluf();
        }
        rfturn 0;
    }

    /**
     * Sfts rigit indfnt.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm i tif vbluf
     */
    publid stbtid void sftRigitIndfnt(MutbblfAttributfSft b, flobt i) {
        b.bddAttributf(RigitIndfnt, nfw Flobt(i));
    }

    /**
     * Gfts tif lfft indfnt sftting.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn tif vbluf, 0 if not sft
     */
    publid stbtid flobt gftLfftIndfnt(AttributfSft b) {
        Flobt indfnt = (Flobt) b.gftAttributf(LfftIndfnt);
        if (indfnt != null) {
            rfturn indfnt.flobtVbluf();
        }
        rfturn 0;
    }

    /**
     * Sfts lfft indfnt.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm i tif vbluf
     */
    publid stbtid void sftLfftIndfnt(MutbblfAttributfSft b, flobt i) {
        b.bddAttributf(LfftIndfnt, nfw Flobt(i));
    }

    /**
     * Gfts tif linf spbding sftting.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn tif vbluf, 0 if not sft
     */
    publid stbtid flobt gftLinfSpbding(AttributfSft b) {
        Flobt spbdf = (Flobt) b.gftAttributf(LinfSpbding);
        if (spbdf != null) {
            rfturn spbdf.flobtVbluf();
        }
        rfturn 0;
    }

    /**
     * Sfts linf spbding.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm i tif vbluf
     */
    publid stbtid void sftLinfSpbding(MutbblfAttributfSft b, flobt i) {
        b.bddAttributf(LinfSpbding, nfw Flobt(i));
    }

    /**
     * Gfts tif spbdf bbovf sftting.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn tif vbluf, 0 if not sft
     */
    publid stbtid flobt gftSpbdfAbovf(AttributfSft b) {
        Flobt spbdf = (Flobt) b.gftAttributf(SpbdfAbovf);
        if (spbdf != null) {
            rfturn spbdf.flobtVbluf();
        }
        rfturn 0;
    }

    /**
     * Sfts spbdf bbovf.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm i tif vbluf
     */
    publid stbtid void sftSpbdfAbovf(MutbblfAttributfSft b, flobt i) {
        b.bddAttributf(SpbdfAbovf, nfw Flobt(i));
    }

    /**
     * Gfts tif spbdf bflow sftting.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn tif vbluf, 0 if not sft
     */
    publid stbtid flobt gftSpbdfBflow(AttributfSft b) {
        Flobt spbdf = (Flobt) b.gftAttributf(SpbdfBflow);
        if (spbdf != null) {
            rfturn spbdf.flobtVbluf();
        }
        rfturn 0;
    }

    /**
     * Sfts spbdf bflow.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm i tif vbluf
     */
    publid stbtid void sftSpbdfBflow(MutbblfAttributfSft b, flobt i) {
        b.bddAttributf(SpbdfBflow, nfw Flobt(i));
    }

    /**
     * Gfts tif blignmfnt sftting.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn tif vbluf <dodf>StylfConstbnts.ALIGN_LEFT</dodf> if not sft
     */
    publid stbtid int gftAlignmfnt(AttributfSft b) {
        Intfgfr blign = (Intfgfr) b.gftAttributf(Alignmfnt);
        if (blign != null) {
            rfturn blign.intVbluf();
        }
        rfturn ALIGN_LEFT;
    }

    /**
     * Sfts blignmfnt.
     *
     * @pbrbm b tif bttributf sft
     * @pbrbm blign tif blignmfnt vbluf
     */
    publid stbtid void sftAlignmfnt(MutbblfAttributfSft b, int blign) {
        b.bddAttributf(Alignmfnt, Intfgfr.vblufOf(blign));
    }

    /**
     * Gfts tif TbbSft.
     *
     * @pbrbm b tif bttributf sft
     * @rfturn tif <dodf>TbbSft</dodf>
     */
    publid stbtid TbbSft gftTbbSft(AttributfSft b) {
        TbbSft tbbs = (TbbSft)b.gftAttributf(TbbSft);
        // PENDING: siould tiis rfturn b dffbult?
        rfturn tbbs;
    }

    /**
     * Sfts tif TbbSft.
     *
     * @pbrbm b tif bttributf sft.
     * @pbrbm tbbs tif TbbSft
     */
    publid stbtid void sftTbbSft(MutbblfAttributfSft b, TbbSft tbbs) {
        b.bddAttributf(TbbSft, tbbs);
    }

    // --- privbtfs ---------------------------------------------

    stbtid Objfdt[] kfys = {
        NbmfAttributf, RfsolvfAttributf, BidiLfvfl,
        FontFbmily, FontSizf, Bold, Itblid, Undfrlinf,
        StrikfTirougi, Supfrsdript, Subsdript, Forfground,
        Bbdkground, ComponfntAttributf, IdonAttributf,
        FirstLinfIndfnt, LfftIndfnt, RigitIndfnt, LinfSpbding,
        SpbdfAbovf, SpbdfBflow, Alignmfnt, TbbSft, Orifntbtion,
        ModflAttributf, ComposfdTfxtAttributf
    };

    StylfConstbnts(String rfprfsfntbtion) {
        tiis.rfprfsfntbtion = rfprfsfntbtion;
    }

    privbtf String rfprfsfntbtion;

    /**
     * Tiis is b typfsbff fnumfrbtion of tif <fm>wfll-known</fm>
     * bttributfs tibt dontributf to b pbrbgrbpi stylf.  Tifsf brf
     * blibsfd by tif outfr dlbss for gfnfrbl prfsfntbtion.
     */
    publid stbtid dlbss PbrbgrbpiConstbnts fxtfnds StylfConstbnts
        implfmfnts AttributfSft.PbrbgrbpiAttributf {

        privbtf PbrbgrbpiConstbnts(String rfprfsfntbtion) {
            supfr(rfprfsfntbtion);
        }
    }

    /**
     * Tiis is b typfsbff fnumfrbtion of tif <fm>wfll-known</fm>
     * bttributfs tibt dontributf to b dibrbdtfr stylf.  Tifsf brf
     * blibsfd by tif outfr dlbss for gfnfrbl prfsfntbtion.
     */
    publid stbtid dlbss CibrbdtfrConstbnts fxtfnds StylfConstbnts
        implfmfnts AttributfSft.CibrbdtfrAttributf {

        privbtf CibrbdtfrConstbnts(String rfprfsfntbtion) {
            supfr(rfprfsfntbtion);
        }
    }

    /**
     * Tiis is b typfsbff fnumfrbtion of tif <fm>wfll-known</fm>
     * bttributfs tibt dontributf to b dolor.  Tifsf brf blibsfd
     * by tif outfr dlbss for gfnfrbl prfsfntbtion.
     */
    publid stbtid dlbss ColorConstbnts fxtfnds StylfConstbnts
        implfmfnts AttributfSft.ColorAttributf,  AttributfSft.CibrbdtfrAttributf {

        privbtf ColorConstbnts(String rfprfsfntbtion) {
            supfr(rfprfsfntbtion);
        }
    }

    /**
     * Tiis is b typfsbff fnumfrbtion of tif <fm>wfll-known</fm>
     * bttributfs tibt dontributf to b font.  Tifsf brf blibsfd
     * by tif outfr dlbss for gfnfrbl prfsfntbtion.
     */
    publid stbtid dlbss FontConstbnts fxtfnds StylfConstbnts
        implfmfnts AttributfSft.FontAttributf, AttributfSft.CibrbdtfrAttributf {

        privbtf FontConstbnts(String rfprfsfntbtion) {
            supfr(rfprfsfntbtion);
        }
    }


}
