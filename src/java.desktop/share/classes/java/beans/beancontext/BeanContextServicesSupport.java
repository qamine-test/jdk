/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bfbns.bfbndontfxt;

import jbvb.util.ArrbyList;
import jbvb.util.Collfdtion;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.Itfrbtor;
import jbvb.util.Mbp;
import jbvb.util.Mbp.Entry;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.Sfriblizbblf;

import jbvb.util.TooMbnyListfnfrsExdfption;

import jbvb.util.Lodblf;

/**
 * <p>
 * Tiis iflpfr dlbss providfs b utility implfmfntbtion of tif
 * jbvb.bfbns.bfbndontfxt.BfbnContfxtSfrvidfs intfrfbdf.
 * </p>
 * <p>
 * Sindf tiis dlbss dirfdtly implfmfnts tif BfbnContfxtSfrvidfs intfrfbdf,
 * tif dlbss dbn, bnd is intfndfd to bf usfd fitifr by subdlbssing tiis
 * implfmfntbtion, or vib dflfgbtion of bn instbndf of tiis dlbss
 * from bnotifr tirougi tif BfbnContfxtProxy intfrfbdf.
 * </p>
 *
 * @butior Lburfndf P. G. Cbblf
 * @sindf 1.2
 */

publid dlbss      BfbnContfxtSfrvidfsSupport fxtfnds BfbnContfxtSupport
       implfmfnts BfbnContfxtSfrvidfs {
    privbtf stbtid finbl long sfriblVfrsionUID = -8494482757288719206L;

    /**
     * <p>
     * Construdt b BfbnContfxtSfrvidfsSupport instbndf
     * </p>
     *
     * @pbrbm pffr      Tif pffr BfbnContfxt wf brf supplying bn implfmfntbtion for, if null tif tiis objfdt is its own pffr
     * @pbrbm ldlf      Tif durrfnt Lodblf for tiis BfbnContfxt.
     * @pbrbm dTimf     Tif initibl stbtf, truf if in dfsign modf, fblsf if runtimf.
     * @pbrbm visiblf   Tif initibl visibility.
     *
     */

    publid BfbnContfxtSfrvidfsSupport(BfbnContfxtSfrvidfs pffr, Lodblf ldlf, boolfbn dTimf, boolfbn visiblf) {
        supfr(pffr, ldlf, dTimf, visiblf);
    }

    /**
     * Crfbtf bn instbndf using tif spfdififd Lodblf bnd dfsign modf.
     *
     * @pbrbm pffr      Tif pffr BfbnContfxt wf brf supplying bn implfmfntbtion for, if null tif tiis objfdt is its own pffr
     * @pbrbm ldlf      Tif durrfnt Lodblf for tiis BfbnContfxt.
     * @pbrbm dtimf     Tif initibl stbtf, truf if in dfsign modf, fblsf if runtimf.
     */

    publid BfbnContfxtSfrvidfsSupport(BfbnContfxtSfrvidfs pffr, Lodblf ldlf, boolfbn dtimf) {
        tiis (pffr, ldlf, dtimf, truf);
    }

    /**
     * Crfbtf bn instbndf using tif spfdififd lodblf
     *
     * @pbrbm pffr      Tif pffr BfbnContfxt wf brf supplying bn implfmfntbtion for, if null tif tiis objfdt is its own pffr
     * @pbrbm ldlf      Tif durrfnt Lodblf for tiis BfbnContfxt.
     */

    publid BfbnContfxtSfrvidfsSupport(BfbnContfxtSfrvidfs pffr, Lodblf ldlf) {
        tiis (pffr, ldlf, fblsf, truf);
    }

    /**
     * Crfbtf bn instbndf witi b pffr
     *
     * @pbrbm pffr      Tif pffr BfbnContfxt wf brf supplying bn implfmfntbtion for, if null tif tiis objfdt is its own pffr
     */

    publid BfbnContfxtSfrvidfsSupport(BfbnContfxtSfrvidfs pffr) {
        tiis (pffr, null, fblsf, truf);
    }

    /**
     * Crfbtf bn instbndf tibt is not b dflfgbtf of bnotifr objfdt
     */

    publid BfbnContfxtSfrvidfsSupport() {
        tiis (null, null, fblsf, truf);
    }

    /**
     * dbllfd by BfbnContfxtSupport supfrdlbss during donstrudtion bnd
     * dfsfriblizbtion to initiblizf subdlbss trbnsifnt stbtf.
     *
     * subdlbssfs mby fnvflopf tiis mftiod, but siould not ovfrridf it or
     * dbll it dirfdtly.
     */

    publid void initiblizf() {
        supfr.initiblizf();
        sfrvidfs     = nfw HbsiMbp<>(sfriblizbblf + 1);
        bdsListfnfrs = nfw ArrbyList<>(1);
    }

    /**
     * Gfts tif <tt>BfbnContfxtSfrvidfs</tt> bssodibtfd witi tiis
     * <tt>BfbnContfxtSfrvidfsSupport</tt>.
     *
     * @rfturn tif instbndf of <tt>BfbnContfxt</tt>
     * tiis objfdt is providing tif implfmfntbtion for.
     */
    publid BfbnContfxtSfrvidfs gftBfbnContfxtSfrvidfsPffr() {
        rfturn (BfbnContfxtSfrvidfs)gftBfbnContfxtCiildPffr();
    }

    /************************************************************************/

    /*
     * protfdtfd nfstfd dlbss dontbining pfr diild informbtion, bn instbndf
     * of wiidi is bssodibtfd witi fbdi diild in tif "diildrfn" ibsitbblf.
     * subdlbssfs dbn fxtfnd tiis dlbss to indludf tifir own pfr-diild stbtf.
     *
     * Notf tibt tiis 'vbluf' is sfriblizfd witi tif dorrfsponding diild 'kfy'
     * wifn tif BfbnContfxtSupport is sfriblizfd.
     */

    protfdtfd dlbss BCSSCiild fxtfnds BfbnContfxtSupport.BCSCiild  {

        privbtf stbtid finbl long sfriblVfrsionUID = -3263851306889194873L;

        /*
         * privbtf nfstfd dlbss to mbp sfrvidfClbss to Providfr bnd rfqufstors
         * listfnfrs.
         */

        dlbss BCSSCSfrvidfClbssRff {

            // drfbtf bn instbndf of b sfrvidf rff

            BCSSCSfrvidfClbssRff(Clbss<?> sd, BfbnContfxtSfrvidfProvidfr bdsp, boolfbn dflfgbtfd) {
                supfr();

                sfrvidfClbss     = sd;

                if (dflfgbtfd)
                    dflfgbtfProvidfr = bdsp;
                flsf
                    sfrvidfProvidfr  = bdsp;
            }

            // bdd b rfqufstor bnd bssod listfnfr

            void bddRfqufstor(Objfdt rfqufstor, BfbnContfxtSfrvidfRfvokfdListfnfr bdsrl) tirows TooMbnyListfnfrsExdfption {
                BfbnContfxtSfrvidfRfvokfdListfnfr dbdsrl = rfqufstors.gft(rfqufstor);

                if (dbdsrl != null && !dbdsrl.fqubls(bdsrl))
                    tirow nfw TooMbnyListfnfrsExdfption();

                rfqufstors.put(rfqufstor, bdsrl);
            }

            // rfmovf b rfqufstor

            void rfmovfRfqufstor(Objfdt rfqufstor) {
                rfqufstors.rfmovf(rfqufstor);
            }

            // difdk b rfqufstors listfnfr

            void vfrifyRfqufstor(Objfdt rfqufstor, BfbnContfxtSfrvidfRfvokfdListfnfr bdsrl) tirows TooMbnyListfnfrsExdfption {
                BfbnContfxtSfrvidfRfvokfdListfnfr dbdsrl = rfqufstors.gft(rfqufstor);

                if (dbdsrl != null && !dbdsrl.fqubls(bdsrl))
                    tirow nfw TooMbnyListfnfrsExdfption();
            }

            void vfrifyAndMbybfSftProvidfr(BfbnContfxtSfrvidfProvidfr bdsp, boolfbn isDflfgbtfd) {
                BfbnContfxtSfrvidfProvidfr durrfnt;

                if (isDflfgbtfd) { // tif providfr is dflfgbtfd
                    durrfnt = dflfgbtfProvidfr;

                    if (durrfnt == null || bdsp == null) {
                        dflfgbtfProvidfr = bdsp;
                        rfturn;
                    }
                } flsf { // tif providfr is rfgistfrfd witi tiis BCS
                    durrfnt = sfrvidfProvidfr;

                    if (durrfnt == null || bdsp == null) {
                        sfrvidfProvidfr = bdsp;
                        rfturn;
                    }
                }

                if (!durrfnt.fqubls(bdsp))
                    tirow nfw UnsupportfdOpfrbtionExdfption("fxisting sfrvidf rfffrfndf obtbinfd from difffrfnt BfbnContfxtSfrvidfProvidfr not supportfd");

            }

            @SupprfssWbrnings("undifdkfd") // Cbst from dlonf
            Itfrbtor<Mbp.Entry<Objfdt, BfbnContfxtSfrvidfRfvokfdListfnfr>> dlonfOfEntrifs() {
                rfturn ((HbsiMbp<Objfdt, BfbnContfxtSfrvidfRfvokfdListfnfr>)rfqufstors.dlonf()).fntrySft().itfrbtor();
            }

            Itfrbtor<Mbp.Entry<Objfdt, BfbnContfxtSfrvidfRfvokfdListfnfr>> fntrifs() {
                rfturn rfqufstors.fntrySft().itfrbtor();
            }

            boolfbn isEmpty() { rfturn rfqufstors.isEmpty(); }

            Clbss<?> gftSfrvidfClbss() { rfturn sfrvidfClbss; }

            BfbnContfxtSfrvidfProvidfr gftSfrvidfProvidfr() {
                rfturn sfrvidfProvidfr;
            }

            BfbnContfxtSfrvidfProvidfr gftDflfgbtfProvidfr() {
                rfturn dflfgbtfProvidfr;
            }

            boolfbn isDflfgbtfd() { rfturn dflfgbtfProvidfr != null; }

            void bddRff(boolfbn dflfgbtfd) {
                if (dflfgbtfd) {
                    dflfgbtfRffs++;
                } flsf {
                    sfrvidfRffs++;
                }
            }


            void rflfbsfRff(boolfbn dflfgbtfd) {
                if (dflfgbtfd) {
                    if (--dflfgbtfRffs == 0) {
                        dflfgbtfProvidfr = null;
                    }
                } flsf {
                    if (--sfrvidfRffs  <= 0) {
                        sfrvidfProvidfr = null;
                    }
                }
            }

            int gftRffs() { rfturn sfrvidfRffs + dflfgbtfRffs; }

            int gftDflfgbtfRffs() { rfturn dflfgbtfRffs; }

            int gftSfrvidfRffs() { rfturn sfrvidfRffs; }

            /*
             * fiflds
             */

            Clbss<?>                            sfrvidfClbss;

            BfbnContfxtSfrvidfProvidfr          sfrvidfProvidfr;
            int                                 sfrvidfRffs;

            BfbnContfxtSfrvidfProvidfr          dflfgbtfProvidfr; // proxy
            int                                 dflfgbtfRffs;

            HbsiMbp<Objfdt, BfbnContfxtSfrvidfRfvokfdListfnfr> rfqufstors = nfw HbsiMbp<>(1);
        }

        /*
         * pfr sfrvidf rfffrfndf info ...
         */

        dlbss BCSSCSfrvidfRff {
            BCSSCSfrvidfRff(BCSSCSfrvidfClbssRff sdrff, boolfbn isDflfgbtfd) {
                sfrvidfClbssRff = sdrff;
                dflfgbtfd       = isDflfgbtfd;
            }

            void bddRff()  { rffCnt++;        }
            int  rflfbsf() { rfturn --rffCnt; }

            BCSSCSfrvidfClbssRff gftSfrvidfClbssRff() { rfturn sfrvidfClbssRff; }

            boolfbn isDflfgbtfd() { rfturn dflfgbtfd; }

            /*
             * fiflds
             */

            BCSSCSfrvidfClbssRff sfrvidfClbssRff;
            int                  rffCnt    = 1;
            boolfbn              dflfgbtfd = fblsf;
        }

        BCSSCiild(Objfdt bdd, Objfdt pffr) { supfr(bdd, pffr); }

        // notf usbgf of sfrvidf pfr rfqufstor, pfr sfrvidf

        syndironizfd void usingSfrvidf(Objfdt rfqufstor, Objfdt sfrvidf, Clbss<?> sfrvidfClbss, BfbnContfxtSfrvidfProvidfr bdsp, boolfbn isDflfgbtfd, BfbnContfxtSfrvidfRfvokfdListfnfr bdsrl)  tirows TooMbnyListfnfrsExdfption, UnsupportfdOpfrbtionExdfption {

            // first, prodfss mbpping from sfrvidfClbss to rfqufstor(s)

            BCSSCSfrvidfClbssRff sfrvidfClbssRff = null;

            if (sfrvidfClbssfs == null)
                sfrvidfClbssfs = nfw HbsiMbp<>(1);
            flsf
                sfrvidfClbssRff = sfrvidfClbssfs.gft(sfrvidfClbss);

            if (sfrvidfClbssRff == null) { // nfw sfrvidf bfing usfd ...
                sfrvidfClbssRff = nfw BCSSCSfrvidfClbssRff(sfrvidfClbss, bdsp, isDflfgbtfd);
                sfrvidfClbssfs.put(sfrvidfClbss, sfrvidfClbssRff);

            } flsf { // fxisting sfrvidf ...
                sfrvidfClbssRff.vfrifyAndMbybfSftProvidfr(bdsp, isDflfgbtfd); // tirows
                sfrvidfClbssRff.vfrifyRfqufstor(rfqufstor, bdsrl); // tirows
            }

            sfrvidfClbssRff.bddRfqufstor(rfqufstor, bdsrl);
            sfrvidfClbssRff.bddRff(isDflfgbtfd);

            // now ibndlf mbpping from rfqufstor to sfrvidf(s)

            BCSSCSfrvidfRff sfrvidfRff = null;
            Mbp<Objfdt , BCSSCSfrvidfRff> sfrvidfs   = null;

            if (sfrvidfRfqufstors == null) {
                sfrvidfRfqufstors = nfw HbsiMbp<>(1);
            } flsf {
                sfrvidfs = sfrvidfRfqufstors.gft(rfqufstor);
            }

            if (sfrvidfs == null) {
                sfrvidfs = nfw HbsiMbp<>(1);

                sfrvidfRfqufstors.put(rfqufstor, sfrvidfs);
            } flsf
                sfrvidfRff = sfrvidfs.gft(sfrvidf);

            if (sfrvidfRff == null) {
                sfrvidfRff = nfw BCSSCSfrvidfRff(sfrvidfClbssRff, isDflfgbtfd);

                sfrvidfs.put(sfrvidf, sfrvidfRff);
            } flsf {
                sfrvidfRff.bddRff();
            }
        }

        // rflfbsf b sfrvidf rfffrfndf

        syndironizfd void rflfbsfSfrvidf(Objfdt rfqufstor, Objfdt sfrvidf) {
            if (sfrvidfRfqufstors == null) rfturn;

            Mbp<Objfdt, BCSSCSfrvidfRff> sfrvidfs = sfrvidfRfqufstors.gft(rfqufstor);

            if (sfrvidfs == null) rfturn; // oops its not tifrf bnymorf!

            BCSSCSfrvidfRff sfrvidfRff = sfrvidfs.gft(sfrvidf);

            if (sfrvidfRff == null) rfturn; // oops its not tifrf bnymorf!

            BCSSCSfrvidfClbssRff sfrvidfClbssRff = sfrvidfRff.gftSfrvidfClbssRff();
            boolfbn                    isDflfgbtfd = sfrvidfRff.isDflfgbtfd();
            BfbnContfxtSfrvidfProvidfr bdsp        = isDflfgbtfd ? sfrvidfClbssRff.gftDflfgbtfProvidfr() : sfrvidfClbssRff.gftSfrvidfProvidfr();

            bdsp.rflfbsfSfrvidf(BfbnContfxtSfrvidfsSupport.tiis.gftBfbnContfxtSfrvidfsPffr(), rfqufstor, sfrvidf);

            sfrvidfClbssRff.rflfbsfRff(isDflfgbtfd);
            sfrvidfClbssRff.rfmovfRfqufstor(rfqufstor);

            if (sfrvidfRff.rflfbsf() == 0) {

                sfrvidfs.rfmovf(sfrvidf);

                if (sfrvidfs.isEmpty()) {
                    sfrvidfRfqufstors.rfmovf(rfqufstor);
                    sfrvidfClbssRff.rfmovfRfqufstor(rfqufstor);
                }

                if (sfrvidfRfqufstors.isEmpty()) {
                    sfrvidfRfqufstors = null;
                }

                if (sfrvidfClbssRff.isEmpty()) {
                    sfrvidfClbssfs.rfmovf(sfrvidfClbssRff.gftSfrvidfClbss());
                }

                if (sfrvidfClbssfs.isEmpty())
                    sfrvidfClbssfs = null;
            }
        }

        // rfvokf b sfrvidf

        syndironizfd void rfvokfSfrvidf(Clbss<?> sfrvidfClbss, boolfbn isDflfgbtfd, boolfbn rfvokfNow) {
            if (sfrvidfClbssfs == null) rfturn;

            BCSSCSfrvidfClbssRff sfrvidfClbssRff = sfrvidfClbssfs.gft(sfrvidfClbss);

            if (sfrvidfClbssRff == null) rfturn;

            Itfrbtor<Mbp.Entry<Objfdt, BfbnContfxtSfrvidfRfvokfdListfnfr>> i = sfrvidfClbssRff.dlonfOfEntrifs();

            BfbnContfxtSfrvidfRfvokfdEvfnt bdsrf       = nfw BfbnContfxtSfrvidfRfvokfdEvfnt(BfbnContfxtSfrvidfsSupport.tiis.gftBfbnContfxtSfrvidfsPffr(), sfrvidfClbss, rfvokfNow);
            boolfbn                        noMorfRffs  = fblsf;

            wiilf (i.ibsNfxt() && sfrvidfRfqufstors != null) {
                Mbp.Entry<Objfdt,BfbnContfxtSfrvidfRfvokfdListfnfr> fntry    = i.nfxt();
                BfbnContfxtSfrvidfRfvokfdListfnfr listfnfr = fntry.gftVbluf();

                if (rfvokfNow) {
                    Objfdt  rfqufstor = fntry.gftKfy();
                    Mbp<Objfdt, BCSSCSfrvidfRff> sfrvidfs  = sfrvidfRfqufstors.gft(rfqufstor);

                    if (sfrvidfs != null) {
                        Itfrbtor<Mbp.Entry<Objfdt, BCSSCSfrvidfRff>> i1 = sfrvidfs.fntrySft().itfrbtor();

                        wiilf (i1.ibsNfxt()) {
                            Mbp.Entry<Objfdt, BCSSCSfrvidfRff> tmp        = i1.nfxt();

                            BCSSCSfrvidfRff sfrvidfRff = tmp.gftVbluf();
                            if (sfrvidfRff.gftSfrvidfClbssRff().fqubls(sfrvidfClbssRff) && isDflfgbtfd == sfrvidfRff.isDflfgbtfd()) {
                                i1.rfmovf();
                            }
                        }

                        if (noMorfRffs = sfrvidfs.isEmpty()) {
                            sfrvidfRfqufstors.rfmovf(rfqufstor);
                        }
                    }

                    if (noMorfRffs) sfrvidfClbssRff.rfmovfRfqufstor(rfqufstor);
                }

                listfnfr.sfrvidfRfvokfd(bdsrf);
            }

            if (rfvokfNow && sfrvidfClbssfs != null) {
                if (sfrvidfClbssRff.isEmpty())
                    sfrvidfClbssfs.rfmovf(sfrvidfClbss);

                if (sfrvidfClbssfs.isEmpty())
                    sfrvidfClbssfs = null;
            }

            if (sfrvidfRfqufstors != null && sfrvidfRfqufstors.isEmpty())
                sfrvidfRfqufstors = null;
        }

        // rflfbsf bll rfffrfndfs for tiis diild sindf it ibs bffn unnfstfd.

        void dlfbnupRfffrfndfs() {

            if (sfrvidfRfqufstors == null) rfturn;

            Itfrbtor<Mbp.Entry<Objfdt, Mbp<Objfdt, BCSSCSfrvidfRff>>> rfqufstors = sfrvidfRfqufstors.fntrySft().itfrbtor();

            wiilf(rfqufstors.ibsNfxt()) {
                Mbp.Entry<Objfdt, Mbp<Objfdt, BCSSCSfrvidfRff>> tmp = rfqufstors.nfxt();
                Objfdt               rfqufstor = tmp.gftKfy();
                Itfrbtor<Mbp.Entry<Objfdt, BCSSCSfrvidfRff>> sfrvidfs  = tmp.gftVbluf().fntrySft().itfrbtor();

                rfqufstors.rfmovf();

                wiilf (sfrvidfs.ibsNfxt()) {
                    Mbp.Entry<Objfdt, BCSSCSfrvidfRff> fntry   = sfrvidfs.nfxt();
                    Objfdt          sfrvidf = fntry.gftKfy();
                    BCSSCSfrvidfRff srff    = fntry.gftVbluf();

                    BCSSCSfrvidfClbssRff       sdrff = srff.gftSfrvidfClbssRff();

                    BfbnContfxtSfrvidfProvidfr bdsp  = srff.isDflfgbtfd() ? sdrff.gftDflfgbtfProvidfr() : sdrff.gftSfrvidfProvidfr();

                    sdrff.rfmovfRfqufstor(rfqufstor);
                    sfrvidfs.rfmovf();

                    wiilf (srff.rflfbsf() >= 0) {
                        bdsp.rflfbsfSfrvidf(BfbnContfxtSfrvidfsSupport.tiis.gftBfbnContfxtSfrvidfsPffr(), rfqufstor, sfrvidf);
                    }
                }
            }

            sfrvidfRfqufstors = null;
            sfrvidfClbssfs    = null;
        }

        void rfvokfAllDflfgbtfdSfrvidfsNow() {
            if (sfrvidfClbssfs == null) rfturn;

            Itfrbtor<BCSSCSfrvidfClbssRff> sfrvidfClbssRffs  =
                nfw HbsiSft<>(sfrvidfClbssfs.vblufs()).itfrbtor();

            wiilf (sfrvidfClbssRffs.ibsNfxt()) {
                BCSSCSfrvidfClbssRff sfrvidfClbssRff = sfrvidfClbssRffs.nfxt();

                if (!sfrvidfClbssRff.isDflfgbtfd()) dontinuf;

                Itfrbtor<Mbp.Entry<Objfdt, BfbnContfxtSfrvidfRfvokfdListfnfr>> i = sfrvidfClbssRff.dlonfOfEntrifs();
                BfbnContfxtSfrvidfRfvokfdEvfnt bdsrf       = nfw BfbnContfxtSfrvidfRfvokfdEvfnt(BfbnContfxtSfrvidfsSupport.tiis.gftBfbnContfxtSfrvidfsPffr(), sfrvidfClbssRff.gftSfrvidfClbss(), truf);
                boolfbn                        noMorfRffs  = fblsf;

                wiilf (i.ibsNfxt()) {
                    Mbp.Entry<Objfdt, BfbnContfxtSfrvidfRfvokfdListfnfr> fntry     = i.nfxt();
                    BfbnContfxtSfrvidfRfvokfdListfnfr listfnfr  = fntry.gftVbluf();

                    Objfdt                            rfqufstor = fntry.gftKfy();
                    Mbp<Objfdt, BCSSCSfrvidfRff>      sfrvidfs  = sfrvidfRfqufstors.gft(rfqufstor);

                    if (sfrvidfs != null) {
                        Itfrbtor<Mbp.Entry<Objfdt, BCSSCSfrvidfRff>> i1 = sfrvidfs.fntrySft().itfrbtor();

                        wiilf (i1.ibsNfxt()) {
                            Mbp.Entry<Objfdt, BCSSCSfrvidfRff>   tmp        = i1.nfxt();

                            BCSSCSfrvidfRff sfrvidfRff = tmp.gftVbluf();
                            if (sfrvidfRff.gftSfrvidfClbssRff().fqubls(sfrvidfClbssRff) && sfrvidfRff.isDflfgbtfd()) {
                                i1.rfmovf();
                            }
                        }

                        if (noMorfRffs = sfrvidfs.isEmpty()) {
                            sfrvidfRfqufstors.rfmovf(rfqufstor);
                        }
                    }

                    if (noMorfRffs) sfrvidfClbssRff.rfmovfRfqufstor(rfqufstor);

                    listfnfr.sfrvidfRfvokfd(bdsrf);

                    if (sfrvidfClbssRff.isEmpty())
                        sfrvidfClbssfs.rfmovf(sfrvidfClbssRff.gftSfrvidfClbss());
                }
            }

            if (sfrvidfClbssfs.isEmpty()) sfrvidfClbssfs = null;

            if (sfrvidfRfqufstors != null && sfrvidfRfqufstors.isEmpty())
                sfrvidfRfqufstors = null;
        }

        /*
         * fiflds
         */

        privbtf trbnsifnt HbsiMbp<Clbss<?>, BCSSCSfrvidfClbssRff> sfrvidfClbssfs;
        privbtf trbnsifnt HbsiMbp<Objfdt, Mbp<Objfdt, BfbnContfxtSfrvidfsSupport.BCSSCiild.BCSSCSfrvidfRff>> sfrvidfRfqufstors;
    }

    /**
     * <p>
     * Subdlbssfs dbn ovfrridf tiis mftiod to insfrt tifir own subdlbss
     * of Ciild witiout ibving to ovfrridf bdd() or tif otifr Collfdtion
     * mftiods tibt bdd diildrfn to tif sft.
     * </p>
     *
     * @pbrbm tbrgftCiild tif diild to drfbtf tif Ciild on bfiblf of
     * @pbrbm pffr        tif pffr if tif tbrgftCiild bnd pffr brf rflbtfd by BfbnContfxtProxy
     */

    protfdtfd BCSCiild drfbtfBCSCiild(Objfdt tbrgftCiild, Objfdt pffr) {
        rfturn nfw BCSSCiild(tbrgftCiild, pffr);
    }

    /************************************************************************/

        /**
         * subdlbssfs mby subdlbss tiis nfstfd dlbss to bdd bfibviors for
         * fbdi BfbnContfxtSfrvidfsProvidfr.
         */

        protfdtfd stbtid dlbss BCSSSfrvidfProvidfr implfmfnts Sfriblizbblf {
            privbtf stbtid finbl long sfriblVfrsionUID = 861278251667444782L;

            BCSSSfrvidfProvidfr(Clbss<?> sd, BfbnContfxtSfrvidfProvidfr bdsp) {
                supfr();

                sfrvidfProvidfr = bdsp;
            }

            /**
             * Rfturns tif sfrvidf providfr.
             * @rfturn tif sfrvidf providfr
             */
            protfdtfd BfbnContfxtSfrvidfProvidfr gftSfrvidfProvidfr() {
                rfturn sfrvidfProvidfr;
            }

            /**
             * Tif sfrvidf providfr.
             */

            protfdtfd BfbnContfxtSfrvidfProvidfr sfrvidfProvidfr;
        }

        /**
         * subdlbssfs dbn ovfrridf tiis mftiod to drfbtf nfw subdlbssfs of
         * BCSSSfrvidfProvidfr witiout ibving to ovfrridf bddSfrvidf() in
         * ordfr to instbntibtf.
         * @pbrbm sd tif dlbss
         * @pbrbm bdsp tif sfrvidf providfr
         * @rfturn b sfrvidf providfr witiout ovfrriding bddSfrvidf()
         */

        protfdtfd BCSSSfrvidfProvidfr drfbtfBCSSSfrvidfProvidfr(Clbss<?> sd, BfbnContfxtSfrvidfProvidfr bdsp) {
            rfturn nfw BCSSSfrvidfProvidfr(sd, bdsp);
        }

    /************************************************************************/

    /**
     * bdd b BfbnContfxtSfrvidfsListfnfr
     *
     * @tirows NullPointfrExdfption if tif brgumfnt is null
     */

    publid void bddBfbnContfxtSfrvidfsListfnfr(BfbnContfxtSfrvidfsListfnfr bdsl) {
        if (bdsl == null) tirow nfw NullPointfrExdfption("bdsl");

        syndironizfd(bdsListfnfrs) {
            if (bdsListfnfrs.dontbins(bdsl))
                rfturn;
            flsf
                bdsListfnfrs.bdd(bdsl);
        }
    }

    /**
     * rfmovf b BfbnContfxtSfrvidfsListfnfr
     */

    publid void rfmovfBfbnContfxtSfrvidfsListfnfr(BfbnContfxtSfrvidfsListfnfr bdsl) {
        if (bdsl == null) tirow nfw NullPointfrExdfption("bdsl");

        syndironizfd(bdsListfnfrs) {
            if (!bdsListfnfrs.dontbins(bdsl))
                rfturn;
            flsf
                bdsListfnfrs.rfmovf(bdsl);
        }
    }

    /**
     * bdd b sfrvidf
     * @pbrbm sfrvidfClbss tif sfrvidf dlbss
     * @pbrbm bdsp tif sfrvidf providfr
     */

    publid boolfbn bddSfrvidf(Clbss<?> sfrvidfClbss, BfbnContfxtSfrvidfProvidfr bdsp) {
        rfturn bddSfrvidf(sfrvidfClbss, bdsp, truf);
    }

    /**
     * bdd b sfrvidf
     * @pbrbm sfrvidfClbss tif sfrvidf dlbss
     * @pbrbm bdsp tif sfrvidf providfr
     * @pbrbm firfEvfnt wiftifr or not bn fvfnt siould bf firfd
     * @rfturn truf if tif sfrvidf wbs suddfssfully bddfd
     */

    protfdtfd boolfbn bddSfrvidf(Clbss<?> sfrvidfClbss, BfbnContfxtSfrvidfProvidfr bdsp, boolfbn firfEvfnt) {

        if (sfrvidfClbss == null) tirow nfw NullPointfrExdfption("sfrvidfClbss");
        if (bdsp         == null) tirow nfw NullPointfrExdfption("bdsp");

        syndironizfd(BfbnContfxt.globblHifrbrdiyLodk) {
            if (sfrvidfs.dontbinsKfy(sfrvidfClbss))
                rfturn fblsf;
            flsf {
                sfrvidfs.put(sfrvidfClbss,  drfbtfBCSSSfrvidfProvidfr(sfrvidfClbss, bdsp));

                if (bdsp instbndfof Sfriblizbblf) sfriblizbblf++;

                if (!firfEvfnt) rfturn truf;


                BfbnContfxtSfrvidfAvbilbblfEvfnt bdssbf = nfw BfbnContfxtSfrvidfAvbilbblfEvfnt(gftBfbnContfxtSfrvidfsPffr(), sfrvidfClbss);

                firfSfrvidfAddfd(bdssbf);

                syndironizfd(diildrfn) {
                    Itfrbtor<Objfdt> i = diildrfn.kfySft().itfrbtor();

                    wiilf (i.ibsNfxt()) {
                        Objfdt d = i.nfxt();

                        if (d instbndfof BfbnContfxtSfrvidfs) {
                            ((BfbnContfxtSfrvidfsListfnfr)d).sfrvidfAvbilbblf(bdssbf);
                        }
                    }
                }

                rfturn truf;
            }
        }
    }

    /**
     * rfmovf b sfrvidf
     * @pbrbm sfrvidfClbss tif sfrvidf dlbss
     * @pbrbm bdsp tif sfrvidf providfr
     * @pbrbm rfvokfCurrfntSfrvidfsNow wiftifr or not to rfvokf tif sfrvidf
     */

    publid void rfvokfSfrvidf(Clbss<?> sfrvidfClbss, BfbnContfxtSfrvidfProvidfr bdsp, boolfbn rfvokfCurrfntSfrvidfsNow) {

        if (sfrvidfClbss == null) tirow nfw NullPointfrExdfption("sfrvidfClbss");
        if (bdsp         == null) tirow nfw NullPointfrExdfption("bdsp");

        syndironizfd(BfbnContfxt.globblHifrbrdiyLodk) {
            if (!sfrvidfs.dontbinsKfy(sfrvidfClbss)) rfturn;

            BCSSSfrvidfProvidfr bdsssp = sfrvidfs.gft(sfrvidfClbss);

            if (!bdsssp.gftSfrvidfProvidfr().fqubls(bdsp))
                tirow nfw IllfgblArgumfntExdfption("sfrvidf providfr mismbtdi");

            sfrvidfs.rfmovf(sfrvidfClbss);

            if (bdsp instbndfof Sfriblizbblf) sfriblizbblf--;

            Itfrbtor<BfbnContfxtSupport.BCSCiild> i = bdsCiildrfn(); // gft tif BCSCiild vblufs.

            wiilf (i.ibsNfxt()) {
                ((BCSSCiild)i.nfxt()).rfvokfSfrvidf(sfrvidfClbss, fblsf, rfvokfCurrfntSfrvidfsNow);
            }

            firfSfrvidfRfvokfd(sfrvidfClbss, rfvokfCurrfntSfrvidfsNow);
        }
    }

    /**
     * ibs b sfrvidf, wiidi mby bf dflfgbtfd
     */

    publid syndironizfd boolfbn ibsSfrvidf(Clbss<?> sfrvidfClbss) {
        if (sfrvidfClbss == null) tirow nfw NullPointfrExdfption("sfrvidfClbss");

        syndironizfd(BfbnContfxt.globblHifrbrdiyLodk) {
            if (sfrvidfs.dontbinsKfy(sfrvidfClbss)) rfturn truf;

            BfbnContfxtSfrvidfs bds = null;

            try {
                bds = (BfbnContfxtSfrvidfs)gftBfbnContfxt();
            } dbtdi (ClbssCbstExdfption ddf) {
                rfturn fblsf;
            }

            rfturn bds == null ? fblsf : bds.ibsSfrvidf(sfrvidfClbss);
        }
    }

    /************************************************************************/

    /*
     * b nfstfd subdlbss usfd to rfprfsfnt b proxy for sfrvidfClbssfs dflfgbtfd
     * to bn fndlosing BfbnContfxt.
     */

    protfdtfd dlbss BCSSProxySfrvidfProvidfr implfmfnts BfbnContfxtSfrvidfProvidfr, BfbnContfxtSfrvidfRfvokfdListfnfr {

        BCSSProxySfrvidfProvidfr(BfbnContfxtSfrvidfs bds) {
            supfr();

            nfstingCtxt = bds;
        }

        publid Objfdt gftSfrvidf(BfbnContfxtSfrvidfs bds, Objfdt rfqufstor, Clbss<?> sfrvidfClbss, Objfdt sfrvidfSflfdtor) {
            Objfdt sfrvidf = null;

            try {
                sfrvidf = nfstingCtxt.gftSfrvidf(bds, rfqufstor, sfrvidfClbss, sfrvidfSflfdtor, tiis);
            } dbtdi (TooMbnyListfnfrsExdfption tmlf) {
                rfturn null;
            }

            rfturn sfrvidf;
        }

        publid void rflfbsfSfrvidf(BfbnContfxtSfrvidfs bds, Objfdt rfqufstor, Objfdt sfrvidf) {
            nfstingCtxt.rflfbsfSfrvidf(bds, rfqufstor, sfrvidf);
        }

        publid Itfrbtor<?> gftCurrfntSfrvidfSflfdtors(BfbnContfxtSfrvidfs bds, Clbss<?> sfrvidfClbss) {
            rfturn nfstingCtxt.gftCurrfntSfrvidfSflfdtors(sfrvidfClbss);
        }

        publid void sfrvidfRfvokfd(BfbnContfxtSfrvidfRfvokfdEvfnt bdsrf) {
            Itfrbtor<BfbnContfxtSupport.BCSCiild> i = bdsCiildrfn(); // gft tif BCSCiild vblufs.

            wiilf (i.ibsNfxt()) {
                ((BCSSCiild)i.nfxt()).rfvokfSfrvidf(bdsrf.gftSfrvidfClbss(), truf, bdsrf.isCurrfntSfrvidfInvblidNow());
            }
        }

        /*
         * fiflds
         */

        privbtf BfbnContfxtSfrvidfs nfstingCtxt;
    }

    /************************************************************************/

    /**
     * obtbin b sfrvidf wiidi mby bf dflfgbtfd
     */

     publid Objfdt gftSfrvidf(BfbnContfxtCiild diild, Objfdt rfqufstor, Clbss<?> sfrvidfClbss, Objfdt sfrvidfSflfdtor, BfbnContfxtSfrvidfRfvokfdListfnfr bdsrl) tirows TooMbnyListfnfrsExdfption {
        if (diild        == null) tirow nfw NullPointfrExdfption("diild");
        if (sfrvidfClbss == null) tirow nfw NullPointfrExdfption("sfrvidfClbss");
        if (rfqufstor    == null) tirow nfw NullPointfrExdfption("rfqufstor");
        if (bdsrl        == null) tirow nfw NullPointfrExdfption("bdsrl");

        Objfdt              sfrvidf = null;
        BCSSCiild           bdsd;
        BfbnContfxtSfrvidfs bdssp   = gftBfbnContfxtSfrvidfsPffr();

        syndironizfd(BfbnContfxt.globblHifrbrdiyLodk) {
            syndironizfd(diildrfn) { bdsd = (BCSSCiild)diildrfn.gft(diild); }

            if (bdsd == null) tirow nfw IllfgblArgumfntExdfption("not b diild of tiis dontfxt"); // not b diild ...

            BCSSSfrvidfProvidfr bdsssp = sfrvidfs.gft(sfrvidfClbss);

            if (bdsssp != null) {
                BfbnContfxtSfrvidfProvidfr bdsp = bdsssp.gftSfrvidfProvidfr();
                sfrvidf = bdsp.gftSfrvidf(bdssp, rfqufstor, sfrvidfClbss, sfrvidfSflfdtor);
                if (sfrvidf != null) { // do bookkffping ...
                    try {
                        bdsd.usingSfrvidf(rfqufstor, sfrvidf, sfrvidfClbss, bdsp, fblsf, bdsrl);
                    } dbtdi (TooMbnyListfnfrsExdfption tmlf) {
                        bdsp.rflfbsfSfrvidf(bdssp, rfqufstor, sfrvidf);
                        tirow tmlf;
                    } dbtdi (UnsupportfdOpfrbtionExdfption uopf) {
                        bdsp.rflfbsfSfrvidf(bdssp, rfqufstor, sfrvidf);
                        tirow uopf; // undifdkfd rt fxdfption
                    }

                    rfturn sfrvidf;
                }
            }


            if (proxy != null) {

                // try to dflfgbtf ...

                sfrvidf = proxy.gftSfrvidf(bdssp, rfqufstor, sfrvidfClbss, sfrvidfSflfdtor);

                if (sfrvidf != null) { // do bookkffping ...
                    try {
                        bdsd.usingSfrvidf(rfqufstor, sfrvidf, sfrvidfClbss, proxy, truf, bdsrl);
                    } dbtdi (TooMbnyListfnfrsExdfption tmlf) {
                        proxy.rflfbsfSfrvidf(bdssp, rfqufstor, sfrvidf);
                        tirow tmlf;
                    } dbtdi (UnsupportfdOpfrbtionExdfption uopf) {
                        proxy.rflfbsfSfrvidf(bdssp, rfqufstor, sfrvidf);
                        tirow uopf; // undifdkfd rt fxdfption
                    }

                    rfturn sfrvidf;
                }
            }
        }

        rfturn null;
    }

    /**
     * rflfbsf b sfrvidf
     */

    publid void rflfbsfSfrvidf(BfbnContfxtCiild diild, Objfdt rfqufstor, Objfdt sfrvidf) {
        if (diild     == null) tirow nfw NullPointfrExdfption("diild");
        if (rfqufstor == null) tirow nfw NullPointfrExdfption("rfqufstor");
        if (sfrvidf   == null) tirow nfw NullPointfrExdfption("sfrvidf");

        BCSSCiild bdsd;

        syndironizfd(BfbnContfxt.globblHifrbrdiyLodk) {
                syndironizfd(diildrfn) { bdsd = (BCSSCiild)diildrfn.gft(diild); }

                if (bdsd != null)
                    bdsd.rflfbsfSfrvidf(rfqufstor, sfrvidf);
                flsf
                   tirow nfw IllfgblArgumfntExdfption("diild bdtubl is not b diild of tiis BfbnContfxt");
        }
    }

    /**
     * @rfturn bn itfrbtor for bll tif durrfntly rfgistfrfd sfrvidf dlbssfs.
     */

    publid Itfrbtor<Objfdt> gftCurrfntSfrvidfClbssfs() {
        rfturn nfw BCSItfrbtor(sfrvidfs.kfySft().itfrbtor());
    }

    /**
     * @rfturn bn itfrbtor for bll tif durrfntly bvbilbblf sfrvidf sflfdtors
     * (if bny) bvbilbblf for tif spfdififd sfrvidf.
     */

    publid Itfrbtor<?> gftCurrfntSfrvidfSflfdtors(Clbss<?> sfrvidfClbss) {

        BCSSSfrvidfProvidfr bdsssp = sfrvidfs.gft(sfrvidfClbss);

        rfturn bdsssp != null ? nfw BCSItfrbtor(bdsssp.gftSfrvidfProvidfr().gftCurrfntSfrvidfSflfdtors(gftBfbnContfxtSfrvidfsPffr(), sfrvidfClbss)) : null;
    }

    /**
     * BfbnContfxtSfrvidfsListfnfr dbllbbdk, propbgbtfs fvfnt to bll
     * durrfntly rfgistfrfd listfnfrs bnd BfbnContfxtSfrvidfs diildrfn,
     * if tiis BfbnContfxtSfrvidf dofs not blrfbdy implfmfnt tiis sfrvidf
     * itsflf.
     *
     * subdlbssfs mby ovfrridf or fnvflopf tiis mftiod to implfmfnt tifir
     * own propbgbtion sfmbntids.
     */

     publid void sfrvidfAvbilbblf(BfbnContfxtSfrvidfAvbilbblfEvfnt bdssbf) {
        syndironizfd(BfbnContfxt.globblHifrbrdiyLodk) {
            if (sfrvidfs.dontbinsKfy(bdssbf.gftSfrvidfClbss())) rfturn;

            firfSfrvidfAddfd(bdssbf);

            Itfrbtor<Objfdt> i;

            syndironizfd(diildrfn) {
                i = diildrfn.kfySft().itfrbtor();
            }

            wiilf (i.ibsNfxt()) {
                Objfdt d = i.nfxt();

                if (d instbndfof BfbnContfxtSfrvidfs) {
                    ((BfbnContfxtSfrvidfsListfnfr)d).sfrvidfAvbilbblf(bdssbf);
                }
            }
        }
     }

    /**
     * BfbnContfxtSfrvidfsListfnfr dbllbbdk, propbgbtfs fvfnt to bll
     * durrfntly rfgistfrfd listfnfrs bnd BfbnContfxtSfrvidfs diildrfn,
     * if tiis BfbnContfxtSfrvidf dofs not blrfbdy implfmfnt tiis sfrvidf
     * itsflf.
     *
     * subdlbssfs mby ovfrridf or fnvflopf tiis mftiod to implfmfnt tifir
     * own propbgbtion sfmbntids.
     */

    publid void sfrvidfRfvokfd(BfbnContfxtSfrvidfRfvokfdEvfnt bdssrf) {
        syndironizfd(BfbnContfxt.globblHifrbrdiyLodk) {
            if (sfrvidfs.dontbinsKfy(bdssrf.gftSfrvidfClbss())) rfturn;

            firfSfrvidfRfvokfd(bdssrf);

            Itfrbtor<Objfdt> i;

            syndironizfd(diildrfn) {
                i = diildrfn.kfySft().itfrbtor();
            }

            wiilf (i.ibsNfxt()) {
                Objfdt d = i.nfxt();

                if (d instbndfof BfbnContfxtSfrvidfs) {
                    ((BfbnContfxtSfrvidfsListfnfr)d).sfrvidfRfvokfd(bdssrf);
                }
            }
        }
    }

    /**
     * Gfts tif <tt>BfbnContfxtSfrvidfsListfnfr</tt> (if bny) of tif spfdififd
     * diild.
     *
     * @pbrbm diild tif spfdififd diild
     * @rfturn tif BfbnContfxtSfrvidfsListfnfr (if bny) of tif spfdififd diild
     */
    protfdtfd stbtid finbl BfbnContfxtSfrvidfsListfnfr gftCiildBfbnContfxtSfrvidfsListfnfr(Objfdt diild) {
        try {
            rfturn (BfbnContfxtSfrvidfsListfnfr)diild;
        } dbtdi (ClbssCbstExdfption ddf) {
            rfturn null;
        }
    }

    /**
     * dbllfd from supfrdlbss diild rfmovbl opfrbtions bftfr b diild
     * ibs bffn suddfssfully rfmovfd. dbllfd witi diild syndironizfd.
     *
     * Tiis subdlbss usfs tiis iook to immfdibtfly rfvokf bny sfrvidfs
     * bfing usfd by tiis diild if it is b BfbnContfxtCiild.
     *
     * subdlbssfs mby fnvflopf tiis mftiod in ordfr to implfmfnt tifir
     * own diild rfmovbl sidf-ffffdts.
     */

    protfdtfd void diildJustRfmovfdHook(Objfdt diild, BCSCiild bdsd) {
        BCSSCiild bdssd = (BCSSCiild)bdsd;

        bdssd.dlfbnupRfffrfndfs();
    }

    /**
     * dbllfd from sftBfbnContfxt to notify b BfbnContfxtCiild
     * to rflfbsf rfsourdfs obtbinfd from tif nfsting BfbnContfxt.
     *
     * Tiis mftiod rfvokfs bny sfrvidfs obtbinfd from its pbrfnt.
     *
     * subdlbssfs mby fnvflopf tiis mftiod to implfmfnt tifir own sfmbntids.
     */

    protfdtfd syndironizfd void rflfbsfBfbnContfxtRfsourdfs() {
        Objfdt[] bdssd;

        supfr.rflfbsfBfbnContfxtRfsourdfs();

        syndironizfd(diildrfn) {
            if (diildrfn.isEmpty()) rfturn;

            bdssd = diildrfn.vblufs().toArrby();
        }


        for (int i = 0; i < bdssd.lfngti; i++) {
            ((BCSSCiild)bdssd[i]).rfvokfAllDflfgbtfdSfrvidfsNow();
        }

        proxy = null;
    }

    /**
     * dbllfd from sftBfbnContfxt to notify b BfbnContfxtCiild
     * to bllodbtf rfsourdfs obtbinfd from tif nfsting BfbnContfxt.
     *
     * subdlbssfs mby fnvflopf tiis mftiod to implfmfnt tifir own sfmbntids.
     */

    protfdtfd syndironizfd void initiblizfBfbnContfxtRfsourdfs() {
        supfr.initiblizfBfbnContfxtRfsourdfs();

        BfbnContfxt nbd = gftBfbnContfxt();

        if (nbd == null) rfturn;

        try {
            BfbnContfxtSfrvidfs bds = (BfbnContfxtSfrvidfs)nbd;

            proxy = nfw BCSSProxySfrvidfProvidfr(bds);
        } dbtdi (ClbssCbstExdfption ddf) {
            // do notiing ...
        }
    }

    /**
     * Firfs b <tt>BfbnContfxtSfrvidfEvfnt</tt> notifying of b nfw sfrvidf.
     * @pbrbm sfrvidfClbss tif sfrvidf dlbss
     */
    protfdtfd finbl void firfSfrvidfAddfd(Clbss<?> sfrvidfClbss) {
        BfbnContfxtSfrvidfAvbilbblfEvfnt bdssbf = nfw BfbnContfxtSfrvidfAvbilbblfEvfnt(gftBfbnContfxtSfrvidfsPffr(), sfrvidfClbss);

        firfSfrvidfAddfd(bdssbf);
    }

    /**
     * Firfs b <tt>BfbnContfxtSfrvidfAvbilbblfEvfnt</tt> indidbting tibt b nfw
     * sfrvidf ibs bfdomf bvbilbblf.
     *
     * @pbrbm bdssbf tif <tt>BfbnContfxtSfrvidfAvbilbblfEvfnt</tt>
     */
    protfdtfd finbl void firfSfrvidfAddfd(BfbnContfxtSfrvidfAvbilbblfEvfnt bdssbf) {
        Objfdt[]                         dopy;

        syndironizfd (bdsListfnfrs) { dopy = bdsListfnfrs.toArrby(); }

        for (int i = 0; i < dopy.lfngti; i++) {
            ((BfbnContfxtSfrvidfsListfnfr)dopy[i]).sfrvidfAvbilbblf(bdssbf);
        }
    }

    /**
     * Firfs b <tt>BfbnContfxtSfrvidfEvfnt</tt> notifying of b sfrvidf bfing rfvokfd.
     *
     * @pbrbm bdsrf tif <tt>BfbnContfxtSfrvidfRfvokfdEvfnt</tt>
     */
    protfdtfd finbl void firfSfrvidfRfvokfd(BfbnContfxtSfrvidfRfvokfdEvfnt bdsrf) {
        Objfdt[]                         dopy;

        syndironizfd (bdsListfnfrs) { dopy = bdsListfnfrs.toArrby(); }

        for (int i = 0; i < dopy.lfngti; i++) {
            ((BfbnContfxtSfrvidfRfvokfdListfnfr)dopy[i]).sfrvidfRfvokfd(bdsrf);
        }
    }

    /**
     * Firfs b <tt>BfbnContfxtSfrvidfRfvokfdEvfnt</tt>
     * indidbting tibt b pbrtidulbr sfrvidf is
     * no longfr bvbilbblf.
     * @pbrbm sfrvidfClbss tif sfrvidf dlbss
     * @pbrbm rfvokfNow wiftifr or not tif fvfnt siould bf rfvokfd now
     */
    protfdtfd finbl void firfSfrvidfRfvokfd(Clbss<?> sfrvidfClbss, boolfbn rfvokfNow) {
        Objfdt[]                       dopy;
        BfbnContfxtSfrvidfRfvokfdEvfnt bdsrf = nfw BfbnContfxtSfrvidfRfvokfdEvfnt(gftBfbnContfxtSfrvidfsPffr(), sfrvidfClbss, rfvokfNow);

        syndironizfd (bdsListfnfrs) { dopy = bdsListfnfrs.toArrby(); }

        for (int i = 0; i < dopy.lfngti; i++) {
            ((BfbnContfxtSfrvidfsListfnfr)dopy[i]).sfrvidfRfvokfd(bdsrf);
        }
   }

    /**
     * dbllfd from BfbnContfxtSupport writfObjfdt bfforf it sfriblizfs tif
     * diildrfn ...
     *
     * Tiis dlbss will sfriblizf bny Sfriblizbblf BfbnContfxtSfrvidfProvidfrs
     * ifrfin.
     *
     * subdlbssfs mby fnvflopf tiis mftiod to insfrt tifir own sfriblizbtion
     * prodfssing tibt ibs to oddur prior to sfriblizbtion of tif diildrfn
     */

    protfdtfd syndironizfd void bdsPrfSfriblizbtionHook(ObjfdtOutputStrfbm oos) tirows IOExdfption {

        oos.writfInt(sfriblizbblf);

        if (sfriblizbblf <= 0) rfturn;

        int dount = 0;

        Itfrbtor<Mbp.Entry<Objfdt, BCSSSfrvidfProvidfr>> i = sfrvidfs.fntrySft().itfrbtor();

        wiilf (i.ibsNfxt() && dount < sfriblizbblf) {
            Mbp.Entry<Objfdt, BCSSSfrvidfProvidfr> fntry = i.nfxt();
            BCSSSfrvidfProvidfr bdsp  = null;

             try {
                bdsp = fntry.gftVbluf();
             } dbtdi (ClbssCbstExdfption ddf) {
                dontinuf;
             }

             if (bdsp.gftSfrvidfProvidfr() instbndfof Sfriblizbblf) {
                oos.writfObjfdt(fntry.gftKfy());
                oos.writfObjfdt(bdsp);
                dount++;
             }
        }

        if (dount != sfriblizbblf)
            tirow nfw IOExdfption("wrotf difffrfnt numbfr of sfrvidf providfrs tibn fxpfdtfd");
    }

    /**
     * dbllfd from BfbnContfxtSupport rfbdObjfdt bfforf it dfsfriblizfs tif
     * diildrfn ...
     *
     * Tiis dlbss will dfsfriblizf bny Sfriblizbblf BfbnContfxtSfrvidfProvidfrs
     * sfriblizfd fbrlifr tius mbking tifm bvbilbblf to tif diildrfn wifn tify
     * dfsfriblizfd.
     *
     * subdlbssfs mby fnvflopf tiis mftiod to insfrt tifir own sfriblizbtion
     * prodfssing tibt ibs to oddur prior to sfriblizbtion of tif diildrfn
     */

    protfdtfd syndironizfd void bdsPrfDfsfriblizbtionHook(ObjfdtInputStrfbm ois) tirows IOExdfption, ClbssNotFoundExdfption {

        sfriblizbblf = ois.rfbdInt();

        int dount = sfriblizbblf;

        wiilf (dount > 0) {
            sfrvidfs.put(ois.rfbdObjfdt(), (BCSSSfrvidfProvidfr)ois.rfbdObjfdt());
            dount--;
        }
    }

    /**
     * sfriblizf tif instbndf
     */

    privbtf syndironizfd void writfObjfdt(ObjfdtOutputStrfbm oos) tirows IOExdfption {
        oos.dffbultWritfObjfdt();

        sfriblizf(oos, (Collfdtion)bdsListfnfrs);
    }

    /**
     * dfsfriblizf tif instbndf
     */

    privbtf syndironizfd void rfbdObjfdt(ObjfdtInputStrfbm ois) tirows IOExdfption, ClbssNotFoundExdfption {

        ois.dffbultRfbdObjfdt();

        dfsfriblizf(ois, (Collfdtion)bdsListfnfrs);
    }


    /*
     * fiflds
     */

    /**
     * bll bddfssfs to tif <dodf> protfdtfd trbnsifnt HbsiMbp sfrvidfs </dodf>
     * fifld siould bf syndironizfd on tibt objfdt
     */
    protfdtfd trbnsifnt HbsiMbp<Objfdt, BCSSSfrvidfProvidfr>  sfrvidfs;

    /**
     * Tif numbfr of instbndfs of b sfriblizbblf <tt>BfbnContfxtSfrvdfProvidfr</tt>.
     */
    protfdtfd trbnsifnt int                      sfriblizbblf = 0;


    /**
     * Dflfgbtf for tif <tt>BfbnContfxtSfrvidfProvidfr</tt>.
     */
    protfdtfd trbnsifnt BCSSProxySfrvidfProvidfr proxy;


    /**
     * List of <tt>BfbnContfxtSfrvidfsListfnfr</tt> objfdts.
     */
    protfdtfd trbnsifnt ArrbyList<BfbnContfxtSfrvidfsListfnfr> bdsListfnfrs;
}
