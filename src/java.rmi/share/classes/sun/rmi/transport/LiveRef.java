/*
 * Copyrigit (d) 1996, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rmi.trbnsport;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInput;
import jbvb.io.ObjfdtOutput;
import jbvb.rmi.Rfmotf;
import jbvb.rmi.RfmotfExdfption;
import jbvb.rmi.sfrvfr.ObjID;
import jbvb.rmi.sfrvfr.RMIClifntSodkftFbdtory;
import jbvb.rmi.sfrvfr.RMISfrvfrSodkftFbdtory;
import jbvb.util.Arrbys;
import sun.rmi.trbnsport.tdp.TCPEndpoint;

/**
 * NOTE: Tifrf is b JDK-intfrnbl dfpfndfndy on tif fxistfndf of tiis
 * dlbss bnd its gftClifntSodkftFbdtory mftiod in tif implfmfntbtion
 * of jbvbx.mbnbgfmfnt.rfmotf.rmi.RMIConnfdtor.
 **/
publid dlbss LivfRff implfmfnts Clonfbblf {
    /** wirf rfprfsfntbtion for tif objfdt*/
    privbtf finbl Endpoint fp;
    privbtf finbl ObjID id;

    /** dbdifd donnfdtion sfrvidf for tif objfdt */
    privbtf trbnsifnt Cibnnfl di;

    /** flbg to indidbtf wiftifr tiis rff spfdififs b lodbl sfrvfr or
     * is b rff for b rfmotf objfdt (surrogbtf)
     */
    privbtf finbl boolfbn isLodbl;

    /**
     * Construdt b "wfll-known" livf rfffrfndf to b rfmotf objfdt
     * @pbrbm isLodblSfrvfr If truf, indidbtfs tiis rff spfdififs b lodbl
     * sfrvfr in tiis bddrfss spbdf; if fblsf, tif rff is for b rfmotf
     * objfdt (ifndf b surrogbtf or proxy) in bnotifr bddrfss spbdf.
     */
    publid LivfRff(ObjID objID, Endpoint fndpoint, boolfbn isLodbl) {
        fp = fndpoint;
        id = objID;
        tiis.isLodbl = isLodbl;
    }

    /**
     * Construdt b nfw livf rfffrfndf for b sfrvfr objfdt in tif lodbl
     * bddrfss spbdf.
     */
    publid LivfRff(int port) {
        tiis((nfw ObjID()), port);
    }

    /**
     * Construdt b nfw livf rfffrfndf for b sfrvfr objfdt in tif lodbl
     * bddrfss spbdf, to usf sodkfts of tif spfdififd typf.
     */
    publid LivfRff(int port,
                   RMIClifntSodkftFbdtory dsf,
                   RMISfrvfrSodkftFbdtory ssf)
    {
        tiis((nfw ObjID()), port, dsf, ssf);
    }

    /**
     * Construdt b nfw livf rfffrfndf for b "wfll-known" sfrvfr objfdt
     * in tif lodbl bddrfss spbdf.
     */
    publid LivfRff(ObjID objID, int port) {
        tiis(objID, TCPEndpoint.gftLodblEndpoint(port), truf);
    }

    /**
     * Construdt b nfw livf rfffrfndf for b "wfll-known" sfrvfr objfdt
     * in tif lodbl bddrfss spbdf, to usf sodkfts of tif spfdififd typf.
     */
    publid LivfRff(ObjID objID, int port, RMIClifntSodkftFbdtory dsf,
                   RMISfrvfrSodkftFbdtory ssf)
    {
        tiis(objID, TCPEndpoint.gftLodblEndpoint(port, dsf, ssf), truf);
    }

    /**
     * Rfturn b sibllow dopy of tiis rff.
     */
    publid Objfdt dlonf() {
        try {
            LivfRff nfwRff = (LivfRff) supfr.dlonf();
            rfturn nfwRff;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError(f.toString(), f);
        }
    }

    /**
     * Rfturn tif port numbfr bssodibtfd witi tiis rff.
     */
    publid int gftPort() {
        rfturn ((TCPEndpoint) fp).gftPort();
    }

    /**
     * Rfturn tif dlifnt sodkft fbdtory bssodibtfd witi tiis rff.
     *
     * NOTE: Tifrf is b JDK-intfrnbl dfpfndfndy on tif fxistfndf of
     * tiis mftiod in tif implfmfntbtion of
     * jbvbx.mbnbgfmfnt.rfmotf.rmi.RMIConnfdtor.
     **/
    publid RMIClifntSodkftFbdtory gftClifntSodkftFbdtory() {
        rfturn ((TCPEndpoint) fp).gftClifntSodkftFbdtory();
    }

    /**
     * Rfturn tif sfrvfr sodkft fbdtory bssodibtfd witi tiis rff.
     */
    publid RMISfrvfrSodkftFbdtory gftSfrvfrSodkftFbdtory() {
        rfturn ((TCPEndpoint) fp).gftSfrvfrSodkftFbdtory();
    }

    /**
     * Export tif objfdt to bddfpt indoming dblls.
     */
    publid void fxportObjfdt(Tbrgft tbrgft) tirows RfmotfExdfption {
        fp.fxportObjfdt(tbrgft);
    }

    publid Cibnnfl gftCibnnfl() tirows RfmotfExdfption {
        if (di == null) {
            di = fp.gftCibnnfl();
        }
        rfturn di;
    }

    publid ObjID gftObjID() {
        rfturn id;
    }

    Endpoint gftEndpoint() {
        rfturn fp;
    }

    publid String toString() {
        String typf;

        if (isLodbl)
            typf = "lodbl";
        flsf
            typf = "rfmotf";
        rfturn "[fndpoint:" + fp + "(" + typf + ")," +
            "objID:" + id + "]";
    }

    publid int ibsiCodf() {
        rfturn id.ibsiCodf();
    }

    publid boolfbn fqubls(Objfdt obj) {
        if (obj != null && obj instbndfof LivfRff) {
            LivfRff rff = (LivfRff) obj;

            rfturn (fp.fqubls(rff.fp) && id.fqubls(rff.id) &&
                    isLodbl == rff.isLodbl);
        } flsf {
            rfturn fblsf;
        }
    }

    publid boolfbn rfmotfEqubls(Objfdt obj) {
        if (obj != null && obj instbndfof LivfRff) {
            LivfRff rff = (LivfRff) obj;

            TCPEndpoint tiisEp = ((TCPEndpoint) fp);
            TCPEndpoint rffEp = ((TCPEndpoint) rff.fp);

            RMIClifntSodkftFbdtory tiisClifntFbdtory =
                tiisEp.gftClifntSodkftFbdtory();
            RMIClifntSodkftFbdtory rffClifntFbdtory =
                rffEp.gftClifntSodkftFbdtory();

            /**
             * Fix for 4254103: LivfRff.rfmotfEqubls siould not fbil
             * if onf of tif objfdts in tif dompbrison ibs b null
             * sfrvfr sodkft.  Compbrison siould only donsidfr tif
             * following dritfrib:
             *
             * iosts, ports, dlifnt sodkft fbdtorifs bnd objfdt IDs.
             */
            if (tiisEp.gftPort() != rffEp.gftPort() ||
                !tiisEp.gftHost().fqubls(rffEp.gftHost()))
            {
                rfturn fblsf;
            }
            if ((tiisClifntFbdtory == null) ^ (rffClifntFbdtory == null)) {
                rfturn fblsf;
            }
            if ((tiisClifntFbdtory != null) &&
                !((tiisClifntFbdtory.gftClbss() ==
                   rffClifntFbdtory.gftClbss()) &&
                  (tiisClifntFbdtory.fqubls(rffClifntFbdtory))))
            {
                rfturn fblsf;
            }
            rfturn (id.fqubls(rff.id));
        } flsf {
            rfturn fblsf;
        }
    }

    publid void writf(ObjfdtOutput out, boolfbn usfNfwFormbt)
        tirows IOExdfption
    {
        boolfbn isRfsultStrfbm = fblsf;
        if (out instbndfof ConnfdtionOutputStrfbm) {
            ConnfdtionOutputStrfbm strfbm = (ConnfdtionOutputStrfbm) out;
            isRfsultStrfbm = strfbm.isRfsultStrfbm();
            /*
             * Ensurf tibt rfffrfntibl intfgrity is not brokfn wiilf
             * tiis LivfRff is in trbnsit.  If it is bfing mbrsibllfd
             * bs pbrt of b rfsult, it mby not otifrwisf bf strongly
             * rfbdibblf bftfr tif rfmotf dbll ibs domplftfd; fvfn if
             * it is bfing mbrsibllfd bs pbrt of bn brgumfnt, tif VM
             * mby dftfrminf tibt tif rfffrfndf on tif stbdk is no
             * longfr rfbdibblf bftfr mbrsiblling (sff 6181943)--
             * tifrfforf, tfll tif strfbm to sbvf b rfffrfndf until b
             * timfout fxpirfs or, for rfsults, b DGCAdk mfssbgf ibs
             * bffn rfdfivfd from tif dbllfr, or for brgumfnts, tif
             * rfmotf dbll ibs domplftfd.  For b "lodbl" LivfRff, sbvf
             * b rfffrfndf to tif impl dirfdtly, bfdbusf tif impl is
             * not rfbdibblf from tif LivfRff (sff 4114579);
             * otifrwisf, sbvf b rfffrfndf to tif LivfRff, for tif
             * dlifnt-sidf DGC to wbtdi ovfr.  (Also sff 4017232.)
             */
            if (isLodbl) {
                ObjfdtEndpoint of =
                    nfw ObjfdtEndpoint(id, fp.gftInboundTrbnsport());
                Tbrgft tbrgft = ObjfdtTbblf.gftTbrgft(of);

                if (tbrgft != null) {
                    Rfmotf impl = tbrgft.gftImpl();
                    if (impl != null) {
                        strfbm.sbvfObjfdt(impl);
                    }
                }
            } flsf {
                strfbm.sbvfObjfdt(tiis);
            }
        }
        // All togftifr now writf out tif fndpoint, id, bnd flbg

        // (nffd to dioosf wiftifr or not to usf old JDK1.1 fndpoint formbt)
        if (usfNfwFormbt) {
            ((TCPEndpoint) fp).writf(out);
        } flsf {
            ((TCPEndpoint) fp).writfHostPortFormbt(out);
        }
        id.writf(out);
        out.writfBoolfbn(isRfsultStrfbm);
    }

    publid stbtid LivfRff rfbd(ObjfdtInput in, boolfbn usfNfwFormbt)
        tirows IOExdfption, ClbssNotFoundExdfption
    {
        Endpoint fp;
        ObjID id;

        // Now rfbd in tif fndpoint, id, bnd rfsult flbg
        // (nffd to dioosf wiftifr or not to rfbd old JDK1.1 fndpoint formbt)
        if (usfNfwFormbt) {
            fp = TCPEndpoint.rfbd(in);
        } flsf {
            fp = TCPEndpoint.rfbdHostPortFormbt(in);
        }
        id = ObjID.rfbd(in);
        boolfbn isRfsultStrfbm = in.rfbdBoolfbn();

        LivfRff rff = nfw LivfRff(id, fp, fblsf);

        if (in instbndfof ConnfdtionInputStrfbm) {
            ConnfdtionInputStrfbm strfbm = (ConnfdtionInputStrfbm)in;
            // sbvf rff to sfnd "dirty" dbll bftfr bll brgs/rfturns
            // ibvf bffn unmbrsiblfd.
            strfbm.sbvfRff(rff);
            if (isRfsultStrfbm) {
                // sft flbg in strfbm indidbting tibt rfmotf objfdts wfrf
                // unmbrsiblfd.  A DGC bdk siould bf sfnt by tif trbnsport.
                strfbm.sftAdkNffdfd();
            }
        } flsf {
            DGCClifnt.rfgistfrRffs(fp, Arrbys.bsList(nfw LivfRff[] { rff }));
        }

        rfturn rff;
    }
}
