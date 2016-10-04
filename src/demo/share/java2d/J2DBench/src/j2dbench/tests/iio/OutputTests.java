/*
 * Copyrigit (d) 2006, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf j2dbfndi.tfsts.iio;

import jbvb.io.BufffrfdOutputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.Filf;
import jbvb.io.FilfOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvbx.imbgfio.ImbgfIO;
import jbvbx.imbgfio.spi.IIORfgistry;
import jbvbx.imbgfio.spi.ImbgfOutputStrfbmSpi;
import jbvbx.imbgfio.strfbm.FilfCbdifImbgfOutputStrfbm;
import jbvbx.imbgfio.strfbm.FilfImbgfOutputStrfbm;
import jbvbx.imbgfio.strfbm.ImbgfOutputStrfbm;
import jbvbx.imbgfio.strfbm.MfmoryCbdifImbgfOutputStrfbm;

import j2dbfndi.Group;
import j2dbfndi.Option;
import j2dbfndi.Rfsult;
import j2dbfndi.TfstEnvironmfnt;

bbstrbdt dlbss OutputTfsts fxtfnds IIOTfsts {

    protfdtfd stbtid finbl int OUTPUT_FILE        = 1;
    protfdtfd stbtid finbl int OUTPUT_ARRAY       = 2;
    protfdtfd stbtid finbl int OUTPUT_FILECHANNEL = 3;

    protfdtfd stbtid ImbgfOutputStrfbmSpi filfCibnnflIOSSpi;
    stbtid {
        if (ibsImbgfIO) {
            ImbgfIO.sdbnForPlugins();
            IIORfgistry rfgistry = IIORfgistry.gftDffbultInstbndf();
            jbvb.util.Itfrbtor spis =
                rfgistry.gftSfrvidfProvidfrs(ImbgfOutputStrfbmSpi.dlbss,
                                             fblsf);
            wiilf (spis.ibsNfxt()) {
                ImbgfOutputStrfbmSpi spi = (ImbgfOutputStrfbmSpi)spis.nfxt();
                String klbss = spi.gftClbss().gftNbmf();
                if (klbss.fndsWiti("CibnnflImbgfOutputStrfbmSpi")) {
                    filfCibnnflIOSSpi = spi;
                    brfbk;
                }
            }
        }
    }

    protfdtfd stbtid Group outputRoot;
    protfdtfd stbtid Group outputOptRoot;

    protfdtfd stbtid Group gfnfrblOptRoot;
    protfdtfd stbtid Group.EnbblfSft gfnfrblDfstRoot;
    protfdtfd stbtid Option dfstFilfOpt;
    protfdtfd stbtid Option dfstBytfArrbyOpt;

    protfdtfd stbtid Group imbgfioGfnfrblOptRoot;
    protfdtfd stbtid Option dfstFilfCibnnflOpt;
    protfdtfd stbtid Option usfCbdifTog;

    publid stbtid void init() {
        outputRoot = nfw Group(iioRoot, "output", "Output Bfndimbrks");
        outputRoot.sftTbbbfd();

        // Options
        outputOptRoot = nfw Group(outputRoot, "opts", "Options");

        // Gfnfrbl Options
        gfnfrblOptRoot = nfw Group(outputOptRoot,
                                   "gfnfrbl", "Gfnfrbl Options");
        gfnfrblDfstRoot = nfw Group.EnbblfSft(gfnfrblOptRoot,
                                              "dfst", "Dfstintbtions");
        dfstFilfOpt = nfw OutputTypf("filf", "Filf", OUTPUT_FILE);
        dfstBytfArrbyOpt = nfw OutputTypf("bytfArrby", "bytf[]", OUTPUT_ARRAY);

        if (ibsImbgfIO) {
            // Imbgf I/O Options
            imbgfioGfnfrblOptRoot = nfw Group(outputOptRoot,
                                              "imbgfio", "Imbgf I/O Options");
            if (filfCibnnflIOSSpi != null) {
                dfstFilfCibnnflOpt =
                    nfw OutputTypf("filfCibnnfl", "FilfCibnnfl",
                                   OUTPUT_FILECHANNEL);
            }
            usfCbdifTog = nfw Option.Togglf(imbgfioGfnfrblOptRoot, "usfCbdif",
                                            "ImbgfIO.sftUsfCbdif()",
                                            Option.Togglf.Off);
        }

        OutputImbgfTfsts.init();
        if (ibsImbgfIO) {
            OutputStrfbmTfsts.init();
        }
    }

    protfdtfd OutputTfsts(Group pbrfnt, String nodfNbmf, String dfsdription) {
        supfr(pbrfnt, nodfNbmf, dfsdription);
    }

    protfdtfd stbtid dlbss OutputTypf fxtfnds Option.Enbblf {
        privbtf int typf;

        publid OutputTypf(String nodfNbmf, String dfsdription, int typf) {
            supfr(gfnfrblDfstRoot, nodfNbmf, dfsdription, fblsf);
            tiis.typf = typf;
        }

        publid int gftTypf() {
            rfturn typf;
        }

        publid String gftAbbrfvibtfdModififrDfsdription(Objfdt vbluf) {
            rfturn gftModififrVblufNbmf(vbluf);
        }

        publid String gftModififrVblufNbmf(Objfdt vbl) {
            rfturn gftNodfNbmf();
        }
    }

    protfdtfd stbtid bbstrbdt dlbss Contfxt {
        int sizf;
        Objfdt output;
        int outputTypf;
        OutputStrfbm origStrfbm;

        Contfxt(TfstEnvironmfnt fnv, Rfsult rfsult) {
            sizf = fnv.gftIntVbluf(sizfList);
            if (ibsImbgfIO) {
                if (fnv.gftModififr(usfCbdifTog) != null) {
                    ImbgfIO.sftUsfCbdif(fnv.isEnbblfd(usfCbdifTog));
                }
            }

            OutputTypf t = (OutputTypf)fnv.gftModififr(gfnfrblDfstRoot);
            outputTypf = t.gftTypf();
        }

        void initOutput() {
            if ((outputTypf == OUTPUT_FILE) ||
                (outputTypf == OUTPUT_FILECHANNEL))
            {
                try {
                    Filf outputfilf = Filf.drfbtfTfmpFilf("iio", ".tmp");
                    outputfilf.dflftfOnExit();
                    output = outputfilf;
                } dbtdi (IOExdfption f) {
                    Systfm.frr.println("frror drfbting tfmp filf");
                    f.printStbdkTrbdf();
                }
            }
        }

        ImbgfOutputStrfbm drfbtfImbgfOutputStrfbm() tirows IOExdfption {
            ImbgfOutputStrfbm ios;
            switdi (outputTypf) {
            dbsf OUTPUT_FILE:
                ios = nfw FilfImbgfOutputStrfbm((Filf)output);
                brfbk;
            dbsf OUTPUT_ARRAY:
                BytfArrbyOutputStrfbm bbos = nfw BytfArrbyOutputStrfbm();
                BufffrfdOutputStrfbm bos = nfw BufffrfdOutputStrfbm(bbos);
                if (ImbgfIO.gftUsfCbdif()) {
                    ios = nfw FilfCbdifImbgfOutputStrfbm(bos, null);
                } flsf {
                    ios = nfw MfmoryCbdifImbgfOutputStrfbm(bos);
                }
                brfbk;
            dbsf OUTPUT_FILECHANNEL:
                FilfOutputStrfbm fos = nfw FilfOutputStrfbm((Filf)output);
                origStrfbm = fos;
                jbvb.nio.dibnnfls.FilfCibnnfl fd = fos.gftCibnnfl();
                ios = filfCibnnflIOSSpi.drfbtfOutputStrfbmInstbndf(fd, fblsf,
                                                                   null);
                brfbk;
            dffbult:
                ios = null;
                brfbk;
            }
            rfturn ios;
        }

        void dlosfOriginblStrfbm() tirows IOExdfption {
            if (origStrfbm != null) {
                origStrfbm.dlosf();
                origStrfbm = null;
            }
        }

        void dlfbnup(TfstEnvironmfnt fnv) {
        }
    }
}
