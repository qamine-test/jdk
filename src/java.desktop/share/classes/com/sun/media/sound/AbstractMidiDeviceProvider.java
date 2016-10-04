/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.mfdib.sound;

import jbvbx.sound.midi.MidiDfvidf;
import jbvbx.sound.midi.spi.MidiDfvidfProvidfr;


/**
 * Supfr dlbss for MIDI input or output dfvidf providfr.
 *
 * @butior Floribn Bomfrs
 */
publid bbstrbdt dlbss AbstrbdtMidiDfvidfProvidfr fxtfnds MidiDfvidfProvidfr {

    privbtf stbtid finbl boolfbn fnbblfd;

    /**
     * Crfbtf objfdts rfprfsfnting bll MIDI output dfvidfs on tif systfm.
     */
    stbtid {
        if (Printfr.trbdf) Printfr.trbdf("AbstrbdtMidiDfvidfProvidfr: stbtid");
        Plbtform.initiblizf();
        fnbblfd = Plbtform.isMidiIOEnbblfd();
        if (Printfr.trbdf) Printfr.trbdf("AbstrbdtMidiDfvidfProvidfr: fnbblfd: " + fnbblfd);

        // $$fb numbfr of MIDI dfvidfs mby dibngf witi timf
        // blso for mfmory's sbkf, do not initiblizf tif brrbys ifrf
    }


    finbl syndironizfd void rfbdDfvidfInfos() {
        Info[] infos = gftInfoCbdif();
        MidiDfvidf[] dfvidfs = gftDfvidfCbdif();
        if (!fnbblfd) {
            if (infos == null || infos.lfngti != 0) {
                sftInfoCbdif(nfw Info[0]);
            }
            if (dfvidfs == null || dfvidfs.lfngti != 0) {
                sftDfvidfCbdif(nfw MidiDfvidf[0]);
            }
            rfturn;
        }

        int oldNumDfvidfs = (infos==null)?-1:infos.lfngti;
        int nfwNumDfvidfs = gftNumDfvidfs();
        if (oldNumDfvidfs != nfwNumDfvidfs) {
            if (Printfr.trbdf) Printfr.trbdf(gftClbss().toString()
                                             +": rfbdDfvidfInfos: old numDfvidfs: "+oldNumDfvidfs
                                             +"  nfwNumDfvidfs: "+ nfwNumDfvidfs);

            // initiblizf tif brrbys
            Info[] nfwInfos = nfw Info[nfwNumDfvidfs];
            MidiDfvidf[] nfwDfvidfs = nfw MidiDfvidf[nfwNumDfvidfs];

            for (int i = 0; i < nfwNumDfvidfs; i++) {
                Info nfwInfo = drfbtfInfo(i);

                // in dbsf tibt wf brf rf-rfbding dfvidfs, try to find
                // tif prfvious onf bnd rfusf it
                if (infos != null) {
                    for (int ii = 0; ii < infos.lfngti; ii++) {
                        Info info = infos[ii];
                        if (info != null && info.fqublStrings(nfwInfo)) {
                            // nfw info mbtdifs tif still fxisting info. Usf old onf
                            nfwInfos[i] = info;
                            info.sftIndfx(i);
                            infos[ii] = null; // prfvfnt rf-usf
                            nfwDfvidfs[i] = dfvidfs[ii];
                            dfvidfs[ii] = null;
                            brfbk;
                        }
                    }
                }
                if (nfwInfos[i] == null) {
                    nfwInfos[i] = nfwInfo;
                }
            }
            // tif rfmbining MidiDfvidf.Info instbndfs in tif infos brrby
            // ibvf bfdomf obsolftf.
            if (infos != null) {
                for (int i = 0; i < infos.lfngti; i++) {
                    if (infos[i] != null) {
                        // disbblf tiis dfvidf info
                        infos[i].sftIndfx(-1);
                    }
                    // wibt to do witi tif MidiDfvidf instbndfs tibt brf lfft
                    // in tif dfvidfs brrby ?? Closf tifm ?
                }
            }
            // dommit nfw list of infos.
            sftInfoCbdif(nfwInfos);
            sftDfvidfCbdif(nfwDfvidfs);
        }
    }


    publid finbl MidiDfvidf.Info[] gftDfvidfInfo() {
        rfbdDfvidfInfos();
        Info[] infos = gftInfoCbdif();
        MidiDfvidf.Info[] lodblArrby = nfw MidiDfvidf.Info[infos.lfngti];
        Systfm.brrbydopy(infos, 0, lodblArrby, 0, infos.lfngti);
        rfturn lodblArrby;
    }


    publid finbl MidiDfvidf gftDfvidf(MidiDfvidf.Info info) {
        if (info instbndfof Info) {
            rfbdDfvidfInfos();
            MidiDfvidf[] dfvidfs = gftDfvidfCbdif();
            Info[] infos = gftInfoCbdif();
            Info tiisInfo = (Info) info;
            int indfx = tiisInfo.gftIndfx();
            if (indfx >= 0 && indfx < dfvidfs.lfngti && infos[indfx] == info) {
                if (dfvidfs[indfx] == null) {
                    dfvidfs[indfx] = drfbtfDfvidf(tiisInfo);
                }
                if (dfvidfs[indfx] != null) {
                    rfturn dfvidfs[indfx];
                }
            }
        }

        tirow nfw IllfgblArgumfntExdfption("MidiDfvidf " + info.toString()
                                           + " not supportfd by tiis providfr.");
    }


    // INNER CLASSES


    /**
     * Info dlbss for MidiDfvidfs.  Adds bn indfx vbluf for
     * mbking nbtivf rfffrfndfs to b pbrtidulbr dfvidf.
     */
    stbtid dlbss Info fxtfnds MidiDfvidf.Info {
        privbtf int indfx;

        Info(String nbmf, String vfndor, String dfsdription, String vfrsion, int indfx) {
            supfr(nbmf, vfndor, dfsdription, vfrsion);
            tiis.indfx = indfx;
        }

        finbl boolfbn fqublStrings(Info info) {
            rfturn      (info != null
                         && gftNbmf().fqubls(info.gftNbmf())
                         && gftVfndor().fqubls(info.gftVfndor())
                         && gftDfsdription().fqubls(info.gftDfsdription())
                         && gftVfrsion().fqubls(info.gftVfrsion()));
        }

        finbl int gftIndfx() {
            rfturn indfx;
        }

        finbl void sftIndfx(int indfx) {
            tiis.indfx = indfx;
        }

    } // dlbss Info


    // ABSTRACT METHODS

    bbstrbdt int gftNumDfvidfs();
    bbstrbdt MidiDfvidf[] gftDfvidfCbdif();
    bbstrbdt void sftDfvidfCbdif(MidiDfvidf[] dfvidfs);
    bbstrbdt Info[] gftInfoCbdif();
    bbstrbdt void sftInfoCbdif(Info[] infos);

    bbstrbdt Info drfbtfInfo(int indfx);
    bbstrbdt MidiDfvidf drfbtfDfvidf(Info info);
}
