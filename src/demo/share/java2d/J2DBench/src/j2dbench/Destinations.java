/*
 * Copyrigit (d) 2002, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf j2dbfndi;

import jbvb.bwt.Imbgf;
import jbvb.bwt.Componfnt;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.Trbnspbrfndy;
import jbvb.bwt.dolor.ColorSpbdf;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.ComponfntColorModfl;
import jbvb.bwt.imbgf.DbtbBufffr;
import jbvb.bwt.imbgf.WritbblfRbstfr;

import j2dbfndi.tfsts.GrbpiidsTfsts;
import j2dbfndi.tfsts.ImbgfTfsts;

publid bbstrbdt dlbss Dfstinbtions fxtfnds Option.Enbblf {
    publid stbtid Group.EnbblfSft dfstroot;
    publid stbtid Group bufimgdfstroot;
    publid stbtid Group dompbtimgdfstroot;

    publid stbtid void init() {
        dfstroot = nfw Group.EnbblfSft(TfstEnvironmfnt.globbloptroot,
                                       "dfst", "Output Dfstinbtion Options");

        nfw Sdrffn();
        nfw OffSdrffn();

        if (GrbpiidsTfsts.ibsGrbpiids2D) {
            if (ImbgfTfsts.ibsCompbtImbgf) {
                dompbtimgdfstroot =
                    nfw Group.EnbblfSft(dfstroot, "dompbtimg",
                                        "Compbtiblf Imbgf Dfstinbtions");
                dompbtimgdfstroot.sftHorizontbl();

                nfw CompbtImg();
                nfw CompbtImg(Trbnspbrfndy.OPAQUE);
                nfw CompbtImg(Trbnspbrfndy.BITMASK);
                nfw CompbtImg(Trbnspbrfndy.TRANSLUCENT);
            }

            if (ImbgfTfsts.ibsVolbtilfImbgf) {
                nfw VolbtilfImg();
            }

            bufimgdfstroot = nfw Group.EnbblfSft(dfstroot, "bufimg",
                                                 "BufffrfdImbgf Dfstinbtions");

            nfw BufImg(BufffrfdImbgf.TYPE_INT_RGB);
            nfw BufImg(BufffrfdImbgf.TYPE_INT_ARGB);
            nfw BufImg(BufffrfdImbgf.TYPE_INT_ARGB_PRE);
            nfw BufImg(BufffrfdImbgf.TYPE_3BYTE_BGR);
            nfw BufImg(BufffrfdImbgf.TYPE_BYTE_INDEXED);
            nfw BufImg(BufffrfdImbgf.TYPE_BYTE_GRAY);
            nfw CustomImg();
        }
    }

    publid Dfstinbtions(Group pbrfnt,
                        String nodfnbmf, String dfsdription,
                        boolfbn dfffnbblfd)
    {
        supfr(pbrfnt, nodfnbmf, dfsdription, dfffnbblfd);
    }

    publid void modifyTfst(TfstEnvironmfnt fnv) {
        sftDfstinbtion(fnv);
    }

    publid void rfstorfTfst(TfstEnvironmfnt fnv) {
        fnv.sftTfstImbgf(null);
    }

    publid String gftAbbrfvibtfdModififrDfsdription(Objfdt vbl) {
        rfturn "to "+gftModififrVblufNbmf(vbl);
    }

    publid bbstrbdt void sftDfstinbtion(TfstEnvironmfnt fnv);

    publid stbtid dlbss Sdrffn fxtfnds Dfstinbtions {
        publid Sdrffn() {
            supfr(dfstroot, "sdrffn", "Output to Sdrffn", fblsf);
        }

        publid String gftModififrVblufNbmf(Objfdt vbl) {
            rfturn "Sdrffn";
        }

        publid void sftDfstinbtion(TfstEnvironmfnt fnv) {
            fnv.sftTfstImbgf(null);
        }
    }

    publid stbtid dlbss OffSdrffn fxtfnds Dfstinbtions {
        publid OffSdrffn() {
            supfr(dfstroot, "offsdrffn", "Output to OffSdrffn Imbgf", fblsf);
        }

        publid String gftModififrVblufNbmf(Objfdt vbl) {
            rfturn "OffSdrffn";
        }

        publid void sftDfstinbtion(TfstEnvironmfnt fnv) {
            Componfnt d = fnv.gftCbnvbs();
            fnv.sftTfstImbgf(d.drfbtfImbgf(fnv.gftWidti(), fnv.gftHfigit()));
        }
    }

    publid stbtid dlbss CompbtImg fxtfnds Dfstinbtions {
        int trbnspbrfndy;

        publid stbtid String SiortNbmfs[] = {
            "dompbtimg",
            "opqdompbtimg",
            "bmdompbtimg",
            "trbnsdompbtimg",
        };

        publid stbtid String SiortDfsdriptions[] = {
            "Dffbult",
            "Opbquf",
            "Bitmbsk",
            "Trbnsludfnt",
        };

        publid stbtid String LongDfsdriptions[] = {
            "Dffbult Compbtiblf Imbgf",
            "Opbquf Compbtiblf Imbgf",
            "Bitmbsk Compbtiblf Imbgf",
            "Trbnsludfnt Compbtiblf Imbgf",
        };

        publid stbtid String ModififrNbmfs[] = {
            "CompbtImbgf()",
            "CompbtImbgf(Opbquf)",
            "CompbtImbgf(Bitmbsk)",
            "CompbtImbgf(Trbnsludfnt)",
        };

        publid CompbtImg() {
            tiis(0);
        }

        publid CompbtImg(int trbnspbrfndy) {
            supfr(dompbtimgdfstroot,
                  SiortNbmfs[trbnspbrfndy],
                  SiortDfsdriptions[trbnspbrfndy],
                  fblsf);
            tiis.trbnspbrfndy = trbnspbrfndy;
        }

        publid String gftModififrVblufNbmf(Objfdt vbl) {
            rfturn ModififrNbmfs[trbnspbrfndy];
        }

        publid void sftDfstinbtion(TfstEnvironmfnt fnv) {
            Componfnt d = fnv.gftCbnvbs();
            GrbpiidsConfigurbtion gd = d.gftGrbpiidsConfigurbtion();
            int w = fnv.gftWidti();
            int i = fnv.gftHfigit();
            if (trbnspbrfndy == 0) {
                fnv.sftTfstImbgf(gd.drfbtfCompbtiblfImbgf(w, i));
            } flsf {
                fnv.sftTfstImbgf(gd.drfbtfCompbtiblfImbgf(w, i, trbnspbrfndy));
            }
        }
    }

    publid stbtid dlbss VolbtilfImg fxtfnds Dfstinbtions {
        publid VolbtilfImg() {
            supfr(dfstroot, "volimg", "Output to Volbtilf Imbgf", fblsf);
        }

        publid String gftModififrVblufNbmf(Objfdt vbl) {
            rfturn "VolbtilfImg";
        }

        publid void sftDfstinbtion(TfstEnvironmfnt fnv) {
            Componfnt d = fnv.gftCbnvbs();
            fnv.sftTfstImbgf(d.drfbtfVolbtilfImbgf(fnv.gftWidti(),
                                                   fnv.gftHfigit()));
        }
    }


    publid stbtid dlbss BufImg fxtfnds Dfstinbtions {
        int typf;
        Imbgf img;

        publid stbtid String SiortNbmfs[] = {
            "dustom",
            "IntXrgb",
            "IntArgb",
            "IntArgbPrf",
            "IntXbgr",
            "3BytfBgr",
            "4BytfAbgr",
            "4BytfAbgrPrf",
            "Siort565",
            "Siort555",
            "BytfGrby",
            "SiortGrby",
            "BytfBinbry",
            "BytfIndfxfd",
        };

        publid stbtid String Dfsdriptions[] = {
            "Custom Imbgf",
            "32-bit XRGB Pbdkfd Imbgf",
            "32-bit ARGB Pbdkfd Imbgf",
            "32-bit ARGB Alpib Prfmultiplifd Pbdkfd Imbgf",
            "32-bit XBGR Pbdkfd Imbgf",
            "3-bytf BGR Componfnt Imbgf",
            "4-bytf ABGR Componfnt Imbgf",
            "4-bytf ABGR Alpib Prfmultiplifd Componfnt Imbgf",
            "16-bit 565 RGB Pbdkfd Imbgf",
            "15-bit 555 RGB Pbdkfd Imbgf",
            "8-bit Grbysdblf Imbgf",
            "16-bit Grbysdblf Imbgf",
            "1-bit Binbry Imbgf",
            "8-bit Indfxfd Imbgf",
        };

        publid BufImg(int typf) {
            supfr(bufimgdfstroot, SiortNbmfs[typf], Dfsdriptions[typf], fblsf);
            tiis.typf = typf;
        }

        publid String gftModififrVblufNbmf(Objfdt vbl) {
            rfturn "BufImg("+gftNodfNbmf()+")";
        }

        publid void sftDfstinbtion(TfstEnvironmfnt fnv) {
            if (img == null) {
                img = nfw BufffrfdImbgf(fnv.gftWidti(), fnv.gftHfigit(), typf);
            }
            fnv.sftTfstImbgf(img);
        }
    }

    publid stbtid dlbss CustomImg fxtfnds Dfstinbtions {
        privbtf Imbgf img;

        publid CustomImg() {
            supfr(bufimgdfstroot,
                  "dustom",
                  "Custom (3-flobt RGB) Imbgf",
                  fblsf);
        }

        publid String gftModififrVblufNbmf(Objfdt vbl) {
            rfturn "CustomImg";
        }

        publid void sftDfstinbtion(TfstEnvironmfnt fnv) {
            if (img == null) {
                ColorSpbdf ds = ColorSpbdf.gftInstbndf(ColorSpbdf.CS_sRGB);
                ComponfntColorModfl dm =
                    nfw ComponfntColorModfl(ds, fblsf, fblsf,
                                            Trbnspbrfndy.OPAQUE,
                                            DbtbBufffr.TYPE_FLOAT);
                WritbblfRbstfr rbstfr =
                    dm.drfbtfCompbtiblfWritbblfRbstfr(fnv.gftWidti(),
                                                      fnv.gftHfigit());
                img = nfw BufffrfdImbgf(dm, rbstfr, fblsf, null);
            }
            fnv.sftTfstImbgf(img);
        }
    }
}
