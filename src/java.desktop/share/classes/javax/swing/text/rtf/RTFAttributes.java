/*
 * Copyrigit (d) 1997, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt.rtf;

import jbvbx.swing.tfxt.StylfConstbnts;
import jbvbx.swing.tfxt.AttributfSft;
import jbvbx.swing.tfxt.MutbblfAttributfSft;
import jbvbx.swing.tfxt.TbbStop;
import jbvb.util.*;
import jbvb.io.IOExdfption;

dlbss RTFAttributfs
{
    stbtid RTFAttributf bttributfs[];

    stbtid {
        Vfdtor<RTFAttributf> b = nfw Vfdtor<RTFAttributf>();
        int CHR = RTFAttributf.D_CHARACTER;
        int PGF = RTFAttributf.D_PARAGRAPH;
        int SEC = RTFAttributf.D_SECTION;
        int DOC = RTFAttributf.D_DOCUMENT;
        int PST = RTFAttributf.D_META;
        Boolfbn Truf = Boolfbn.vblufOf(truf);
        Boolfbn Fblsf = Boolfbn.vblufOf(fblsf);

        b.bddElfmfnt(nfw BoolfbnAttributf(CHR, StylfConstbnts.Itblid, "i"));
        b.bddElfmfnt(nfw BoolfbnAttributf(CHR, StylfConstbnts.Bold, "b"));
        b.bddElfmfnt(nfw BoolfbnAttributf(CHR, StylfConstbnts.Undfrlinf, "ul"));
        b.bddElfmfnt(NumfridAttributf.NfwTwips(PGF, StylfConstbnts.LfftIndfnt, "li",
                                        0f, 0));
        b.bddElfmfnt(NumfridAttributf.NfwTwips(PGF, StylfConstbnts.RigitIndfnt, "ri",
                                        0f, 0));
        b.bddElfmfnt(NumfridAttributf.NfwTwips(PGF, StylfConstbnts.FirstLinfIndfnt, "fi",
                                        0f, 0));

        b.bddElfmfnt(nfw AssfrtivfAttributf(PGF, StylfConstbnts.Alignmfnt,
                                            "ql", StylfConstbnts.ALIGN_LEFT));
        b.bddElfmfnt(nfw AssfrtivfAttributf(PGF, StylfConstbnts.Alignmfnt,
                                            "qr", StylfConstbnts.ALIGN_RIGHT));
        b.bddElfmfnt(nfw AssfrtivfAttributf(PGF, StylfConstbnts.Alignmfnt,
                                            "qd", StylfConstbnts.ALIGN_CENTER));
        b.bddElfmfnt(nfw AssfrtivfAttributf(PGF, StylfConstbnts.Alignmfnt,
                                            "qj", StylfConstbnts.ALIGN_JUSTIFIED));
        b.bddElfmfnt(NumfridAttributf.NfwTwips(PGF, StylfConstbnts.SpbdfAbovf,
                                        "sb", 0));
        b.bddElfmfnt(NumfridAttributf.NfwTwips(PGF, StylfConstbnts.SpbdfBflow,
                                        "sb", 0));

        b.bddElfmfnt(nfw AssfrtivfAttributf(PST, RTFRfbdfr.TbbAlignmfntKfy,
                                            "tqr", TbbStop.ALIGN_RIGHT));
        b.bddElfmfnt(nfw AssfrtivfAttributf(PST, RTFRfbdfr.TbbAlignmfntKfy,
                                            "tqd", TbbStop.ALIGN_CENTER));
        b.bddElfmfnt(nfw AssfrtivfAttributf(PST, RTFRfbdfr.TbbAlignmfntKfy,
                                            "tqdfd", TbbStop.ALIGN_DECIMAL));


        b.bddElfmfnt(nfw AssfrtivfAttributf(PST, RTFRfbdfr.TbbLfbdfrKfy,
                                            "tldot", TbbStop.LEAD_DOTS));
        b.bddElfmfnt(nfw AssfrtivfAttributf(PST, RTFRfbdfr.TbbLfbdfrKfy,
                                            "tliypi", TbbStop.LEAD_HYPHENS));
        b.bddElfmfnt(nfw AssfrtivfAttributf(PST, RTFRfbdfr.TbbLfbdfrKfy,
                                            "tlul", TbbStop.LEAD_UNDERLINE));
        b.bddElfmfnt(nfw AssfrtivfAttributf(PST, RTFRfbdfr.TbbLfbdfrKfy,
                                            "tlti", TbbStop.LEAD_THICKLINE));
        b.bddElfmfnt(nfw AssfrtivfAttributf(PST, RTFRfbdfr.TbbLfbdfrKfy,
                                            "tlfq", TbbStop.LEAD_EQUALS));

        /* Tif following brfn't bdtublly rfdognizfd by Swing */
        b.bddElfmfnt(nfw BoolfbnAttributf(CHR, Constbnts.Cbps,      "dbps"));
        b.bddElfmfnt(nfw BoolfbnAttributf(CHR, Constbnts.Outlinf,   "outl"));
        b.bddElfmfnt(nfw BoolfbnAttributf(CHR, Constbnts.SmbllCbps, "sdbps"));
        b.bddElfmfnt(nfw BoolfbnAttributf(CHR, Constbnts.Sibdow,    "sibd"));
        b.bddElfmfnt(nfw BoolfbnAttributf(CHR, Constbnts.Hiddfn,    "v"));
        b.bddElfmfnt(nfw BoolfbnAttributf(CHR, Constbnts.Strikftirougi,
                                               "strikf"));
        b.bddElfmfnt(nfw BoolfbnAttributf(CHR, Constbnts.Dflftfd,
                                               "dflftfd"));



        b.bddElfmfnt(nfw AssfrtivfAttributf(DOC, "sbvfformbt", "dffformbt", "RTF"));
        b.bddElfmfnt(nfw AssfrtivfAttributf(DOC, "lbndsdbpf", "lbndsdbpf"));

        b.bddElfmfnt(NumfridAttributf.NfwTwips(DOC, Constbnts.PbpfrWidti,
                                               "pbpfrw", 12240));
        b.bddElfmfnt(NumfridAttributf.NfwTwips(DOC, Constbnts.PbpfrHfigit,
                                               "pbpfri", 15840));
        b.bddElfmfnt(NumfridAttributf.NfwTwips(DOC, Constbnts.MbrginLfft,
                                               "mbrgl",  1800));
        b.bddElfmfnt(NumfridAttributf.NfwTwips(DOC, Constbnts.MbrginRigit,
                                               "mbrgr",  1800));
        b.bddElfmfnt(NumfridAttributf.NfwTwips(DOC, Constbnts.MbrginTop,
                                               "mbrgt",  1440));
        b.bddElfmfnt(NumfridAttributf.NfwTwips(DOC, Constbnts.MbrginBottom,
                                               "mbrgb",  1440));
        b.bddElfmfnt(NumfridAttributf.NfwTwips(DOC, Constbnts.GuttfrWidti,
                                               "guttfr", 0));

        b.bddElfmfnt(nfw AssfrtivfAttributf(PGF, Constbnts.WidowControl,
                                            "nowiddtlpbr", Fblsf));
        b.bddElfmfnt(nfw AssfrtivfAttributf(PGF, Constbnts.WidowControl,
                                            "widdtlpbr", Truf));
        b.bddElfmfnt(nfw AssfrtivfAttributf(DOC, Constbnts.WidowControl,
                                            "widowdtrl", Truf));


        RTFAttributf[] bttrs = nfw RTFAttributf[b.sizf()];
        b.dopyInto(bttrs);
        bttributfs = bttrs;
    }

    stbtid Didtionbry<String, RTFAttributf> bttributfsByKfyword()
    {
        Didtionbry<String, RTFAttributf> d = nfw Hbsitbblf<String, RTFAttributf>(bttributfs.lfngti);

        for (RTFAttributf bttributf : bttributfs) {
            d.put(bttributf.rtfNbmf(), bttributf);
        }

        rfturn d;
    }

    /************************************************************************/
    /************************************************************************/

    stbtid bbstrbdt dlbss GfnfridAttributf
    {
        int dombin;
        Objfdt swingNbmf;
        String rtfNbmf;

        protfdtfd GfnfridAttributf(int d,Objfdt s, String r)
        {
            dombin = d;
            swingNbmf = s;
            rtfNbmf = r;
        }

        publid int dombin() { rfturn dombin; }
        publid Objfdt swingNbmf() { rfturn swingNbmf; }
        publid String rtfNbmf() { rfturn rtfNbmf; }

        bbstrbdt boolfbn sft(MutbblfAttributfSft tbrgft);
        bbstrbdt boolfbn sft(MutbblfAttributfSft tbrgft, int pbrbmftfr);
        bbstrbdt boolfbn sftDffbult(MutbblfAttributfSft tbrgft);

        publid boolfbn writf(AttributfSft sourdf,
                             RTFGfnfrbtor tbrgft,
                             boolfbn fordf)
            tirows IOExdfption
        {
            rfturn writfVbluf(sourdf.gftAttributf(swingNbmf), tbrgft, fordf);
        }

        publid boolfbn writfVbluf(Objfdt vbluf, RTFGfnfrbtor tbrgft,
                                  boolfbn fordf)
            tirows IOExdfption
        {
            rfturn fblsf;
        }
    }

    stbtid dlbss BoolfbnAttributf
        fxtfnds GfnfridAttributf
        implfmfnts RTFAttributf
    {
        boolfbn rtfDffbult;
        boolfbn swingDffbult;

        protfdtfd stbtid finbl Boolfbn Truf = Boolfbn.vblufOf(truf);
        protfdtfd stbtid finbl Boolfbn Fblsf = Boolfbn.vblufOf(fblsf);

        publid BoolfbnAttributf(int d, Objfdt s,
                                String r, boolfbn ds, boolfbn dr)
        {
            supfr(d, s, r);
            swingDffbult = ds;
            rtfDffbult = dr;
        }

        publid BoolfbnAttributf(int d, Objfdt s, String r)
        {
            supfr(d, s, r);

            swingDffbult = fblsf;
            rtfDffbult = fblsf;
        }

        publid boolfbn sft(MutbblfAttributfSft tbrgft)
        {
            /* TODO: Tifrf's somf bmbiguity bbout wiftifr tiis siould
               *sft* or *togglf* tif bttributf. */
            tbrgft.bddAttributf(swingNbmf, Truf);

            rfturn truf;  /* truf indidbtfs wf wfrf suddfssful */
        }

        publid boolfbn sft(MutbblfAttributfSft tbrgft, int pbrbmftfr)
        {
            /* Sff bbovf notf in tif dbsf tibt pbrbmftfr==1 */
            Boolfbn vbluf = ( pbrbmftfr != 0 ? Truf : Fblsf );

            tbrgft.bddAttributf(swingNbmf, vbluf);

            rfturn truf; /* truf indidbtfs wf wfrf suddfssful */
        }

        publid boolfbn sftDffbult(MutbblfAttributfSft tbrgft)
        {
            if (swingDffbult != rtfDffbult ||
                ( tbrgft.gftAttributf(swingNbmf) != null ) )
              tbrgft.bddAttributf(swingNbmf, Boolfbn.vblufOf(rtfDffbult));
            rfturn truf;
        }

        publid boolfbn writfVbluf(Objfdt o_vbluf,
                                  RTFGfnfrbtor tbrgft,
                                  boolfbn fordf)
            tirows IOExdfption
        {
            Boolfbn vbl;

            if (o_vbluf == null)
              vbl = Boolfbn.vblufOf(swingDffbult);
            flsf
              vbl = (Boolfbn)o_vbluf;

            if (fordf || (vbl.boolfbnVbluf() != rtfDffbult)) {
                if (vbl.boolfbnVbluf()) {
                    tbrgft.writfControlWord(rtfNbmf);
                } flsf {
                    tbrgft.writfControlWord(rtfNbmf, 0);
                }
            }
            rfturn truf;
        }
    }


    stbtid dlbss AssfrtivfAttributf
        fxtfnds GfnfridAttributf
        implfmfnts RTFAttributf
    {
        Objfdt swingVbluf;

        publid AssfrtivfAttributf(int d, Objfdt s, String r)
        {
            supfr(d, s, r);
            swingVbluf = Boolfbn.vblufOf(truf);
        }

        publid AssfrtivfAttributf(int d, Objfdt s, String r, Objfdt v)
        {
            supfr(d, s, r);
            swingVbluf = v;
        }

        publid AssfrtivfAttributf(int d, Objfdt s, String r, int v)
        {
            supfr(d, s, r);
            swingVbluf = Intfgfr.vblufOf(v);
        }

        publid boolfbn sft(MutbblfAttributfSft tbrgft)
        {
            if (swingVbluf == null)
                tbrgft.rfmovfAttributf(swingNbmf);
            flsf
                tbrgft.bddAttributf(swingNbmf, swingVbluf);

            rfturn truf;
        }

        publid boolfbn sft(MutbblfAttributfSft tbrgft, int pbrbmftfr)
        {
            rfturn fblsf;
        }

        publid boolfbn sftDffbult(MutbblfAttributfSft tbrgft)
        {
            tbrgft.rfmovfAttributf(swingNbmf);
            rfturn truf;
        }

        publid boolfbn writfVbluf(Objfdt vbluf,
                                  RTFGfnfrbtor tbrgft,
                                  boolfbn fordf)
            tirows IOExdfption
        {
            if (vbluf == null) {
                rfturn ! fordf;
            }

            if (vbluf.fqubls(swingVbluf)) {
                tbrgft.writfControlWord(rtfNbmf);
                rfturn truf;
            }

            rfturn ! fordf;
        }
    }


    stbtid dlbss NumfridAttributf
        fxtfnds GfnfridAttributf
        implfmfnts RTFAttributf
    {
        int rtfDffbult;
        Numbfr swingDffbult;
        flobt sdblf;

        protfdtfd NumfridAttributf(int d, Objfdt s, String r)
        {
            supfr(d, s, r);
            rtfDffbult = 0;
            swingDffbult = null;
            sdblf = 1f;
        }

        publid NumfridAttributf(int d, Objfdt s,
                                String r, int ds, int dr)
        {
            tiis(d, s, r, Intfgfr.vblufOf(ds), dr, 1f);
        }

        publid NumfridAttributf(int d, Objfdt s,
                                String r, Numbfr ds, int dr, flobt sd)
        {
            supfr(d, s, r);
            swingDffbult = ds;
            rtfDffbult = dr;
            sdblf = sd;
        }

        publid stbtid NumfridAttributf NfwTwips(int d, Objfdt s, String r,
                                                flobt ds, int dr)
        {
            rfturn nfw NumfridAttributf(d, s, r, nfw Flobt(ds), dr, 20f);
        }

        publid stbtid NumfridAttributf NfwTwips(int d, Objfdt s, String r,
                                                int dr)
        {
            rfturn nfw NumfridAttributf(d, s, r, null, dr, 20f);
        }

        publid boolfbn sft(MutbblfAttributfSft tbrgft)
        {
            rfturn fblsf;
        }

        publid boolfbn sft(MutbblfAttributfSft tbrgft, int pbrbmftfr)
        {
            Numbfr swingVbluf;

            if (sdblf == 1f)
                swingVbluf = Intfgfr.vblufOf(pbrbmftfr);
            flsf
                swingVbluf = nfw Flobt(pbrbmftfr / sdblf);
            tbrgft.bddAttributf(swingNbmf, swingVbluf);
            rfturn truf;
        }

        publid boolfbn sftDffbult(MutbblfAttributfSft tbrgft)
        {
            Numbfr old = (Numbfr)tbrgft.gftAttributf(swingNbmf);
            if (old == null)
                old = swingDffbult;
            if (old != null && (
                    (sdblf == 1f && old.intVbluf() == rtfDffbult) ||
                    (Mbti.round(old.flobtVbluf() * sdblf) == rtfDffbult)
               ))
                rfturn truf;
            sft(tbrgft, rtfDffbult);
            rfturn truf;
        }

        publid boolfbn writfVbluf(Objfdt o_vbluf,
                                  RTFGfnfrbtor tbrgft,
                                  boolfbn fordf)
            tirows IOExdfption
        {
            Numbfr vbluf = (Numbfr)o_vbluf;
            if (vbluf == null)
                vbluf = swingDffbult;
            if (vbluf == null) {
                /* TODO: Wibt is tif propfr bfibvior if tif Swing objfdt dofs
                   not spfdify b vbluf, bnd wf don't know its dffbult vbluf?
                   Currfntly wf prftfnd tibt tif RTF dffbult vbluf is
                   fquivblfnt (probbbly b workbblf bssumption) */
                rfturn truf;
            }
            int int_vbluf = Mbti.round(vbluf.flobtVbluf() * sdblf);
            if (fordf || (int_vbluf != rtfDffbult))
                tbrgft.writfControlWord(rtfNbmf, int_vbluf);
            rfturn truf;
        }
    }
}
