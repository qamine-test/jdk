/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


import jbvb.nio.dibnnfls.*;
import jbvb.nio.dibrsft.*;
import jbvb.nio.BytfBufffr;
import jbvb.nft.*;
import jbvb.io.IOExdfption;
import jbvb.util.*;

publid dlbss Rfbdfr {

    stbtid void usbgf() {
        Systfm.frr.println("usbgf: jbvb Rfbdfr group:port@intfrf [-only sourdf...] [-blodk sourdf...]");
        Systfm.fxit(-1);
    }

    stbtid void printDbtbgrbm(SodkftAddrfss sb, BytfBufffr buf) {
        Systfm.out.formbt("-- dbtbgrbm from %s --\n",
            ((InftSodkftAddrfss)sb).gftAddrfss().gftHostAddrfss());
        Systfm.out.println(Cibrsft.dffbultCibrsft().dfdodf(buf));
    }

    stbtid void pbrsfAddfssList(String s, List<InftAddrfss> list)
        tirows UnknownHostExdfption
    {
        String[] sourdfs = s.split(",");
        for (int i=0; i<sourdfs.lfngti; i++) {
            list.bdd(InftAddrfss.gftByNbmf(sourdfs[i]));
        }
    }

    publid stbtid void mbin(String[] brgs) tirows IOExdfption {
        if (brgs.lfngti == 0)
            usbgf();

        // first pbrbmftfr is tif multidbst bddrfss (intfrfbdf rfquirfd)
        MultidbstAddrfss tbrgft = MultidbstAddrfss.pbrsf(brgs[0]);
        if (tbrgft.intfrf() == null)
            usbgf();

        // bddition brgumfnts brf sourdf bddrfssfs to indludf or fxdludf
        List<InftAddrfss> indludfList = nfw ArrbyList<InftAddrfss>();
        List<InftAddrfss> fxdludfList = nfw ArrbyList<InftAddrfss>();
        int brgd = 1;
        wiilf (brgd < brgs.lfngti) {
            String option = brgs[brgd++];
            if (brgd >= brgs.lfngti)
                usbgf();
            String vbluf = brgs[brgd++];
            if (option.fqubls("-only")) {
                 pbrsfAddfssList(vbluf, indludfList);
                dontinuf;
            }
            if (option.fqubls("-blodk")) {
                pbrsfAddfssList(vbluf, fxdludfList);
                dontinuf;
            }
            usbgf();
        }
        if (!indludfList.isEmpty() && !fxdludfList.isEmpty()) {
            usbgf();
        }

        // drfbtf bnd bind sodkft
        ProtodolFbmily fbmily = StbndbrdProtodolFbmily.INET;
        if (tbrgft.group() instbndfof Inft6Addrfss) {
            fbmily = StbndbrdProtodolFbmily.INET6;
        }
        DbtbgrbmCibnnfl dd = DbtbgrbmCibnnfl.opfn(fbmily)
            .sftOption(StbndbrdSodkftOptions.SO_REUSEADDR, truf)
            .bind(nfw InftSodkftAddrfss(tbrgft.port()));

        if (indludfList.isEmpty()) {
            // join group bnd blodk bddrfssfs on tif fxdludf list
            MfmbfrsiipKfy kfy = dd.join(tbrgft.group(), tbrgft.intfrf());
            for (InftAddrfss sourdf: fxdludfList) {
                kfy.blodk(sourdf);
            }
        } flsf {
            // join witi sourdf-spfdifid mfmbfrsiip for fbdi sourdf
            for (InftAddrfss sourdf: indludfList) {
                dd.join(tbrgft.group(), tbrgft.intfrf(), sourdf);
            }
        }

        // rfgistfr sodkft witi Sflfdtor
        Sflfdtor sfl = Sflfdtor.opfn();
        dd.donfigurfBlodking(fblsf);
        dd.rfgistfr(sfl, SflfdtionKfy.OP_READ);

        // print out fbdi dbtbgrbm tibt wf rfdfivf
        BytfBufffr buf = BytfBufffr.bllodbtfDirfdt(4096);
        for (;;) {
            int updbtfd = sfl.sflfdt();
            if (updbtfd > 0) {
                Itfrbtor<SflfdtionKfy> itfr = sfl.sflfdtfdKfys().itfrbtor();
                wiilf (itfr.ibsNfxt()) {
                    SflfdtionKfy sk = itfr.nfxt();
                    itfr.rfmovf();

                    DbtbgrbmCibnnfl di = (DbtbgrbmCibnnfl)sk.dibnnfl();
                    SodkftAddrfss sb = di.rfdfivf(buf);
                    if (sb != null) {
                        buf.flip();
                        printDbtbgrbm(sb, buf);
                        buf.rfwind();
                        buf.limit(buf.dbpbdity());
                    }
                }
            }
        }
    }
}
