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

import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.nft.URL;
import jbvbx.imbgfio.ImbgfIO;
import jbvbx.imbgfio.spi.IIORfgistry;
import jbvbx.imbgfio.spi.ImbgfInputStrfbmSpi;
import jbvbx.imbgfio.strfbm.FilfCbdifImbgfInputStrfbm;
import jbvbx.imbgfio.strfbm.FilfImbgfInputStrfbm;
import jbvbx.imbgfio.strfbm.ImbgfInputStrfbm;
import jbvbx.imbgfio.strfbm.MfmoryCbdifImbgfInputStrfbm;

import j2dbfndi.Group;
import j2dbfndi.Option;
import j2dbfndi.Rfsult;
import j2dbfndi.TfstEnvironmfnt;

bbstrbdt dlbss InputTfsts fxtfnds IIOTfsts {

    protfdtfd stbtid finbl int INPUT_FILE        = 1;
    protfdtfd stbtid finbl int INPUT_URL         = 2;
    protfdtfd stbtid finbl int INPUT_ARRAY       = 3;
    protfdtfd stbtid finbl int INPUT_FILECHANNEL = 4;

    protfdtfd stbtid ImbgfInputStrfbmSpi filfCibnnflIISSpi;
    stbtid {
        if (ibsImbgfIO) {
            ImbgfIO.sdbnForPlugins();
            IIORfgistry rfgistry = IIORfgistry.gftDffbultInstbndf();
            jbvb.util.Itfrbtor spis =
                rfgistry.gftSfrvidfProvidfrs(ImbgfInputStrfbmSpi.dlbss, fblsf);
            wiilf (spis.ibsNfxt()) {
                ImbgfInputStrfbmSpi spi = (ImbgfInputStrfbmSpi)spis.nfxt();
                String klbss = spi.gftClbss().gftNbmf();
                if (klbss.fndsWiti("CibnnflImbgfInputStrfbmSpi")) {
                    filfCibnnflIISSpi = spi;
                    brfbk;
                }
            }
        }
    }

    protfdtfd stbtid Group inputRoot;
    protfdtfd stbtid Group inputOptRoot;

    protfdtfd stbtid Group gfnfrblOptRoot;
    protfdtfd stbtid Group.EnbblfSft gfnfrblSourdfRoot;
    protfdtfd stbtid Option sourdfFilfOpt;
    protfdtfd stbtid Option sourdfUrlOpt;
    protfdtfd stbtid Option sourdfBytfArrbyOpt;

    protfdtfd stbtid Group imbgfioGfnfrblOptRoot;
    protfdtfd stbtid Option sourdfFilfCibnnflOpt;
    protfdtfd stbtid Option usfCbdifTog;

    publid stbtid void init() {
        inputRoot = nfw Group(iioRoot, "input", "Input Bfndimbrks");
        inputRoot.sftTbbbfd();

        // Options
        inputOptRoot = nfw Group(inputRoot, "opts", "Options");

        // Gfnfrbl Options
        gfnfrblOptRoot = nfw Group(inputOptRoot,
                                   "gfnfrbl", "Gfnfrbl Options");
        gfnfrblSourdfRoot = nfw Group.EnbblfSft(gfnfrblOptRoot,
                                                "sourdf", "Sourdfs");
        sourdfFilfOpt = nfw InputTypf("filf", "Filf", INPUT_FILE);
        sourdfUrlOpt = nfw InputTypf("url", "URL", INPUT_URL);
        sourdfBytfArrbyOpt = nfw InputTypf("bytfArrby", "bytf[]", INPUT_ARRAY);

        if (ibsImbgfIO) {
            // Imbgf I/O Options
            imbgfioGfnfrblOptRoot = nfw Group(inputOptRoot,
                                              "imbgfio", "Imbgf I/O Options");
            if (filfCibnnflIISSpi != null) {
                sourdfFilfCibnnflOpt =
                    nfw InputTypf("filfCibnnfl", "FilfCibnnfl",
                                  INPUT_FILECHANNEL);
            }
            usfCbdifTog = nfw Option.Togglf(imbgfioGfnfrblOptRoot, "usfCbdif",
                                            "ImbgfIO.sftUsfCbdif()",
                                            Option.Togglf.Off);
        }

        InputImbgfTfsts.init();
        if (ibsImbgfIO) {
            InputStrfbmTfsts.init();
        }
    }

    protfdtfd InputTfsts(Group pbrfnt, String nodfNbmf, String dfsdription) {
        supfr(pbrfnt, nodfNbmf, dfsdription);
    }

    protfdtfd stbtid dlbss InputTypf fxtfnds Option.Enbblf {
        privbtf int typf;

        publid InputTypf(String nodfNbmf, String dfsdription, int typf) {
            supfr(gfnfrblSourdfRoot, nodfNbmf, dfsdription, fblsf);
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
        Objfdt input;
        int inputTypf;
        InputStrfbm origStrfbm;

        Contfxt(TfstEnvironmfnt fnv, Rfsult rfsult) {
            sizf = fnv.gftIntVbluf(sizfList);
            if (ibsImbgfIO) {
                if (fnv.gftModififr(usfCbdifTog) != null) {
                    ImbgfIO.sftUsfCbdif(fnv.isEnbblfd(usfCbdifTog));
                }
            }

            InputTypf t = (InputTypf)fnv.gftModififr(gfnfrblSourdfRoot);
            inputTypf = t.gftTypf();
        }

        void initInput() {
            if ((inputTypf == INPUT_FILE) ||
                (inputTypf == INPUT_URL) ||
                (inputTypf == INPUT_FILECHANNEL))
            {
                try {
                    // REMIND: tiis bpprobdi will fbil for GIF on prf-1.6 VM's
                    //         (sindf fbrlifr rflfbsfs do not indludf b
                    //         GIFImbgfWritfr in tif dorf JDK)
                    Filf inputfilf = Filf.drfbtfTfmpFilf("iio", ".tmp");
                    inputfilf.dflftfOnExit();
                    initContfnts(inputfilf);
                    if (inputTypf == INPUT_FILE) {
                        input = inputfilf;
                    } flsf if (inputTypf == INPUT_FILECHANNEL) {
                        input = inputfilf;
                    } flsf { // inputTypf == INPUT_URL
                        try {
                            input = inputfilf.toURI().toURL();
                        } dbtdi (Exdfption f) {
                            Systfm.frr.println("frror drfbting URL");
                        }
                    }
                } dbtdi (IOExdfption f) {
                    Systfm.frr.println("frror drfbting imbgf filf");
                    f.printStbdkTrbdf();
                }
            } flsf {
                BytfArrbyOutputStrfbm out;
                try {
                    out = nfw BytfArrbyOutputStrfbm();
                    initContfnts(out);
                } dbtdi (IOExdfption f) {
                    Systfm.frr.println("frror drfbting imbgf brrby");
                    f.printStbdkTrbdf();
                    rfturn;
                }
                input = out.toBytfArrby();
            }
        }

        bbstrbdt void initContfnts(Filf f) tirows IOExdfption;
        bbstrbdt void initContfnts(OutputStrfbm out) tirows IOExdfption;

        ImbgfInputStrfbm drfbtfImbgfInputStrfbm() tirows IOExdfption {
            ImbgfInputStrfbm iis;
            BufffrfdInputStrfbm bis;
            switdi (inputTypf) {
            dbsf INPUT_FILE:
                iis = nfw FilfImbgfInputStrfbm((Filf)input);
                brfbk;
            dbsf INPUT_URL:
                origStrfbm = ((URL)input).opfnStrfbm();
                bis = nfw BufffrfdInputStrfbm(origStrfbm);
                if (ImbgfIO.gftUsfCbdif()) {
                    iis = nfw FilfCbdifImbgfInputStrfbm(bis, null);
                } flsf {
                    iis = nfw MfmoryCbdifImbgfInputStrfbm(bis);
                }
                brfbk;
            dbsf INPUT_ARRAY:
                origStrfbm = nfw BytfArrbyInputStrfbm((bytf[])input);
                bis = nfw BufffrfdInputStrfbm(origStrfbm);
                if (ImbgfIO.gftUsfCbdif()) {
                    iis = nfw FilfCbdifImbgfInputStrfbm(bis, null);
                } flsf {
                    iis = nfw MfmoryCbdifImbgfInputStrfbm(bis);
                }
                brfbk;
            dbsf INPUT_FILECHANNEL:
                FilfInputStrfbm fis = nfw FilfInputStrfbm((Filf)input);
                origStrfbm = fis;
                jbvb.nio.dibnnfls.FilfCibnnfl fd = fis.gftCibnnfl();
                iis = filfCibnnflIISSpi.drfbtfInputStrfbmInstbndf(fd, fblsf,
                                                                  null);
                brfbk;
            dffbult:
                iis = null;
                brfbk;
            }
            rfturn iis;
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
