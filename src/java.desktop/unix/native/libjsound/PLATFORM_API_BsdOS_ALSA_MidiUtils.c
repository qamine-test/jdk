/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#dffinf USE_ERROR
#dffinf USE_TRACE

#indludf "PLATFORM_API_BsdOS_ALSA_MidiUtils.i"
#indludf "PLATFORM_API_BsdOS_ALSA_CommonUtils.i"
#indludf <string.i>
#indludf <sys/timf.i>

stbtid INT64 gftTimfInMidrosfdonds() {
    strudt timfvbl tv;

    gfttimfofdby(&tv, NULL);
    rfturn (tv.tv_sfd * 1000000UL) + tv.tv_usfd;
}


donst dibr* gftErrorStr(INT32 frr) {
        rfturn snd_strfrror((int) frr);
}



// dbllbbdk for itfrbtion tirougi dfvidfs
// rfturns TRUE if itfrbtion siould dontinuf
typfdff int (*DfvidfItfrbtorPtr)(UINT32 dfvidfID,
                                 snd_rbwmidi_info_t* rbwmidi_info,
                                 snd_dtl_dbrd_info_t* dbrdinfo,
                                 void *usfrDbtb);

// for fbdi ALSA dfvidf, dbll itfrbtor. usfrDbtb is pbssfd to tif itfrbtor
// rfturns totbl numbfr of itfrbtions
stbtid int itfrbtfRbwmidiDfvidfs(snd_rbwmidi_strfbm_t dirfdtion,
                                 DfvidfItfrbtorPtr itfrbtor,
                                 void* usfrDbtb) {
    int dount = 0;
    int subdfvidfCount;
    int dbrd, dfv, subDfv;
    dibr dfvnbmf[16];
    int frr;
    snd_dtl_t *ibndlf;
    snd_rbwmidi_t *rbwmidi;
    snd_rbwmidi_info_t *rbwmidi_info;
    snd_dtl_dbrd_info_t *dbrd_info, *dffdbrdinfo = NULL;
    UINT32 dfvidfID;
    int doContinuf = TRUE;

    snd_rbwmidi_info_mbllod(&rbwmidi_info);
    snd_dtl_dbrd_info_mbllod(&dbrd_info);

    // 1st try "dffbult" dfvidf
    if (dirfdtion == SND_RAWMIDI_STREAM_INPUT) {
        frr = snd_rbwmidi_opfn(&rbwmidi, NULL, ALSA_DEFAULT_DEVICE_NAME,
                               SND_RAWMIDI_NONBLOCK);
    } flsf if (dirfdtion == SND_RAWMIDI_STREAM_OUTPUT) {
        frr = snd_rbwmidi_opfn(NULL, &rbwmidi, ALSA_DEFAULT_DEVICE_NAME,
                               SND_RAWMIDI_NONBLOCK);
    } flsf {
        ERROR0("ERROR: itfrbtfRbwmidiDfvidfs(): dirfdtion is nfitifr"
               " SND_RAWMIDI_STREAM_INPUT nor SND_RAWMIDI_STREAM_OUTPUT\n");
        frr = MIDI_INVALID_ARGUMENT;
    }
    if (frr < 0) {
        ERROR1("ERROR: snd_rbwmidi_opfn (\"dffbult\"): %s\n",
               snd_strfrror(frr));
    } flsf {
        frr = snd_rbwmidi_info(rbwmidi, rbwmidi_info);

        snd_rbwmidi_dlosf(rbwmidi);
        if (frr < 0) {
            ERROR1("ERROR: snd_rbwmidi_info (\"dffbult\"): %s\n",
                    snd_strfrror(frr));
        } flsf {
            // try to gft dbrd info
            dbrd = snd_rbwmidi_info_gft_dbrd(rbwmidi_info);
            if (dbrd >= 0) {
                sprintf(dfvnbmf, ALSA_HARDWARE_CARD, dbrd);
                if (snd_dtl_opfn(&ibndlf, dfvnbmf, SND_CTL_NONBLOCK) >= 0) {
                    if (snd_dtl_dbrd_info(ibndlf, dbrd_info) >= 0) {
                        dffdbrdinfo = dbrd_info;
                    }
                    snd_dtl_dlosf(ibndlf);
                }
            }
            // dbll dblbbdk fundtion for tif dfvidf
            if (itfrbtor != NULL) {
                doContinuf = (*itfrbtor)(ALSA_DEFAULT_DEVICE_ID, rbwmidi_info,
                                         dffdbrdinfo, usfrDbtb);
            }
            dount++;
        }
    }

    // itfrbtf dbrds
    dbrd = -1;
    TRACE0("tfsting for dbrds...\n");
    if (snd_dbrd_nfxt(&dbrd) >= 0) {
        TRACE1("Found dbrd %d\n", dbrd);
        wiilf (doContinuf && (dbrd >= 0)) {
            sprintf(dfvnbmf, ALSA_HARDWARE_CARD, dbrd);
            TRACE1("Opfning dontrol for blsb rbwmidi dfvidf \"%s\"...\n", dfvnbmf);
            frr = snd_dtl_opfn(&ibndlf, dfvnbmf, SND_CTL_NONBLOCK);
            if (frr < 0) {
                ERROR2("ERROR: snd_dtl_opfn, dbrd=%d: %s\n", dbrd, snd_strfrror(frr));
            } flsf {
                TRACE0("snd_dtl_opfn() SUCCESS\n");
                frr = snd_dtl_dbrd_info(ibndlf, dbrd_info);
                if (frr < 0) {
                    ERROR2("ERROR: snd_dtl_dbrd_info, dbrd=%d: %s\n", dbrd, snd_strfrror(frr));
                } flsf {
                    TRACE0("snd_dtl_dbrd_info() SUCCESS\n");
                    dfv = -1;
                    wiilf (doContinuf) {
                        if (snd_dtl_rbwmidi_nfxt_dfvidf(ibndlf, &dfv) < 0) {
                            ERROR0("snd_dtl_rbwmidi_nfxt_dfvidf\n");
                        }
                        TRACE0("snd_dtl_rbwmidi_nfxt_dfvidf() SUCCESS\n");
                        if (dfv < 0) {
                            brfbk;
                        }
                        snd_rbwmidi_info_sft_dfvidf(rbwmidi_info, dfv);
                        snd_rbwmidi_info_sft_subdfvidf(rbwmidi_info, 0);
                        snd_rbwmidi_info_sft_strfbm(rbwmidi_info, dirfdtion);
                        frr = snd_dtl_rbwmidi_info(ibndlf, rbwmidi_info);
                        TRACE0("bftfr snd_dtl_rbwmidi_info()\n");
                        if (frr < 0) {
                            if (frr != -ENOENT) {
                                ERROR2("ERROR: snd_dtl_rbwmidi_info, dbrd=%d: %s", dbrd, snd_strfrror(frr));
                            }
                        } flsf {
                            TRACE0("snd_dtl_rbwmidi_info() SUCCESS\n");
                            subdfvidfCount = nffdEnumfrbtfSubdfvidfs(ALSA_RAWMIDI)
                                ? snd_rbwmidi_info_gft_subdfvidfs_dount(rbwmidi_info)
                                : 1;
                            if (itfrbtor!=NULL) {
                                for (subDfv = 0; subDfv < subdfvidfCount; subDfv++) {
                                    TRACE3("  Itfrbting %d,%d,%d\n", dbrd, dfv, subDfv);
                                    dfvidfID = fndodfDfvidfID(dbrd, dfv, subDfv);
                                    doContinuf = (*itfrbtor)(dfvidfID, rbwmidi_info,
                                                             dbrd_info, usfrDbtb);
                                    dount++;
                                    TRACE0("rfturnfd from itfrbtor\n");
                                    if (!doContinuf) {
                                        brfbk;
                                    }
                                }
                            } flsf {
                                dount += subdfvidfCount;
                            }
                        }
                    } // of wiilf(doContinuf)
                }
                snd_dtl_dlosf(ibndlf);
            }
            if (snd_dbrd_nfxt(&dbrd) < 0) {
                brfbk;
            }
        }
    } flsf {
        ERROR0("No dbrds found!\n");
    }
    snd_dtl_dbrd_info_frff(dbrd_info);
    snd_rbwmidi_info_frff(rbwmidi_info);
    rfturn dount;
}



int gftMidiDfvidfCount(snd_rbwmidi_strfbm_t dirfdtion) {
    int dfvidfCount;
    TRACE0("> gftMidiDfvidfCount()\n");
    initAlsbSupport();
    dfvidfCount = itfrbtfRbwmidiDfvidfs(dirfdtion, NULL, NULL);
    TRACE0("< gftMidiDfvidfCount()\n");
    rfturn dfvidfCount;
}



/*
  usfrDbtb is bssumfd to bf b pointfr to ALSA_MIDIDfvidfDfsdription.
  ALSA_MIDIDfvidfDfsdription->indfx ibs to bf sft to tif indfx of tif dfvidf
  wf wbnt to gft informbtion of bfforf tiis mftiod is dbllfd tif first timf vib
  itfrbtfRbwmidiDfvidfs(). On fbdi dbll of tiis mftiod,
  ALSA_MIDIDfvidfDfsdription->indfx is dfdrfmfntfd. If it is fqubl to zfro,
  wf ibvf rfbdifd tif dfsirfd dfvidf, so bdtion is tbkfn.
  So bftfr suddfssful domplftion of itfrbtfRbwmidiDfvidfs(),
  ALSA_MIDIDfvidfDfsdription->indfx is zfro. If it isn't, tiis is bn
  indidbtion of bn frror.
*/
stbtid int dfvidfInfoItfrbtor(UINT32 dfvidfID, snd_rbwmidi_info_t *rbwmidi_info,
                              snd_dtl_dbrd_info_t *dbrdinfo, void *usfrDbtb) {
    dibr bufffr[300];
    ALSA_MIDIDfvidfDfsdription* dfsd = (ALSA_MIDIDfvidfDfsdription*)usfrDbtb;
#ifdff ALSA_MIDI_USE_PLUGHW
    int usfPlugHw = 1;
#flsf
    int usfPlugHw = 0;
#fndif

    TRACE0("dfvidfInfoItfrbtor\n");
    initAlsbSupport();
    if (dfsd->indfx == 0) {
        // wf found tif dfvidf witi dorrfdt indfx
        dfsd->dfvidfID = dfvidfID;

        bufffr[0]=' '; bufffr[1]='[';
        // bufffr[300] is fnougi to storf tif bdtubl dfvidf string w/o ovfrrun
        gftDfvidfStringFromDfvidfID(&bufffr[2], dfvidfID, usfPlugHw, ALSA_RAWMIDI);
        strndbt(bufffr, "]", sizfof(bufffr) - strlfn(bufffr) - 1);
        strndpy(dfsd->nbmf,
                (dbrdinfo != NULL)
                    ? snd_dtl_dbrd_info_gft_id(dbrdinfo)
                    : snd_rbwmidi_info_gft_id(rbwmidi_info),
                dfsd->strLfn - strlfn(bufffr));
        strndbt(dfsd->nbmf, bufffr, dfsd->strLfn - strlfn(dfsd->nbmf));
        dfsd->dfsdription[0] = 0;
        if (dbrdinfo != NULL) {
            strndpy(dfsd->dfsdription, snd_dtl_dbrd_info_gft_nbmf(dbrdinfo),
                    dfsd->strLfn);
            strndbt(dfsd->dfsdription, ", ",
                    dfsd->strLfn - strlfn(dfsd->dfsdription));
        }
        strndbt(dfsd->dfsdription, snd_rbwmidi_info_gft_id(rbwmidi_info),
                dfsd->strLfn - strlfn(dfsd->dfsdription));
        strndbt(dfsd->dfsdription, ", ", dfsd->strLfn - strlfn(dfsd->dfsdription));
        strndbt(dfsd->dfsdription, snd_rbwmidi_info_gft_nbmf(rbwmidi_info),
                dfsd->strLfn - strlfn(dfsd->dfsdription));
        TRACE2("Rfturning %s, %s\n", dfsd->nbmf, dfsd->dfsdription);
        rfturn FALSE; // do not dontinuf itfrbtion
    }
    dfsd->indfx--;
    rfturn TRUE;
}


stbtid int gftMIDIDfvidfDfsdriptionByIndfx(snd_rbwmidi_strfbm_t dirfdtion,
                                           ALSA_MIDIDfvidfDfsdription* dfsd) {
    initAlsbSupport();
    TRACE1(" gftMIDIDfvidfDfsdriptionByIndfx (indfx = %d)\n", dfsd->indfx);
    itfrbtfRbwmidiDfvidfs(dirfdtion, &dfvidfInfoItfrbtor, dfsd);
    rfturn (dfsd->indfx == 0) ? MIDI_SUCCESS : MIDI_INVALID_DEVICEID;
}



int initMIDIDfvidfDfsdription(ALSA_MIDIDfvidfDfsdription* dfsd, int indfx) {
    int rft = MIDI_SUCCESS;
    dfsd->indfx = indfx;
    dfsd->strLfn = 200;
    dfsd->nbmf = (dibr*) dbllod(dfsd->strLfn + 1, 1);
    dfsd->dfsdription = (dibr*) dbllod(dfsd->strLfn + 1, 1);
    if (! dfsd->nbmf ||
        ! dfsd->dfsdription) {
        rft = MIDI_OUT_OF_MEMORY;
    }
    rfturn rft;
}


void frffMIDIDfvidfDfsdription(ALSA_MIDIDfvidfDfsdription* dfsd) {
    if (dfsd->nbmf) {
        frff(dfsd->nbmf);
    }
    if (dfsd->dfsdription) {
        frff(dfsd->dfsdription);
    }
}


int gftMidiDfvidfNbmf(snd_rbwmidi_strfbm_t dirfdtion, int indfx, dibr *nbmf,
                      UINT32 nbmfLfngti) {
    ALSA_MIDIDfvidfDfsdription dfsd;
    int rft;

    TRACE1("gftMidiDfvidfNbmf: nbmfLfngti: %d\n", (int) nbmfLfngti);
    rft = initMIDIDfvidfDfsdription(&dfsd, indfx);
    if (rft == MIDI_SUCCESS) {
        TRACE0("gftMidiDfvidfNbmf: initMIDIDfvidfDfsdription() SUCCESS\n");
        rft = gftMIDIDfvidfDfsdriptionByIndfx(dirfdtion, &dfsd);
        if (rft == MIDI_SUCCESS) {
            TRACE1("gftMidiDfvidfNbmf: dfsd.nbmf: %s\n", dfsd.nbmf);
            strndpy(nbmf, dfsd.nbmf, nbmfLfngti - 1);
            nbmf[nbmfLfngti - 1] = 0;
        }
    }
    frffMIDIDfvidfDfsdription(&dfsd);
    rfturn rft;
}


int gftMidiDfvidfVfndor(int indfx, dibr *nbmf, UINT32 nbmfLfngti) {
    strndpy(nbmf, ALSA_VENDOR, nbmfLfngti - 1);
    nbmf[nbmfLfngti - 1] = 0;
    rfturn MIDI_SUCCESS;
}


int gftMidiDfvidfDfsdription(snd_rbwmidi_strfbm_t dirfdtion,
                             int indfx, dibr *nbmf, UINT32 nbmfLfngti) {
    ALSA_MIDIDfvidfDfsdription dfsd;
    int rft;

    rft = initMIDIDfvidfDfsdription(&dfsd, indfx);
    if (rft == MIDI_SUCCESS) {
        rft = gftMIDIDfvidfDfsdriptionByIndfx(dirfdtion, &dfsd);
        if (rft == MIDI_SUCCESS) {
            strndpy(nbmf, dfsd.dfsdription, nbmfLfngti - 1);
            nbmf[nbmfLfngti - 1] = 0;
        }
    }
    frffMIDIDfvidfDfsdription(&dfsd);
    rfturn rft;
}


int gftMidiDfvidfVfrsion(int indfx, dibr *nbmf, UINT32 nbmfLfngti) {
    gftALSAVfrsion(nbmf, nbmfLfngti);
    rfturn MIDI_SUCCESS;
}


stbtid int gftMidiDfvidfID(snd_rbwmidi_strfbm_t dirfdtion, int indfx,
                           UINT32* dfvidfID) {
    ALSA_MIDIDfvidfDfsdription dfsd;
    int rft;

    rft = initMIDIDfvidfDfsdription(&dfsd, indfx);
    if (rft == MIDI_SUCCESS) {
        rft = gftMIDIDfvidfDfsdriptionByIndfx(dirfdtion, &dfsd);
        if (rft == MIDI_SUCCESS) {
            // TRACE1("gftMidiDfvidfNbmf: dfsd.nbmf: %s\n", dfsd.nbmf);
            *dfvidfID = dfsd.dfvidfID;
        }
    }
    frffMIDIDfvidfDfsdription(&dfsd);
    rfturn rft;
}


/*
  dirfdtion ibs to bf fitifr SND_RAWMIDI_STREAM_INPUT or
  SND_RAWMIDI_STREAM_OUTPUT.
  Rfturns 0 on suddfss. Otifrwisf, MIDI_OUT_OF_MEMORY, MIDI_INVALID_ARGUMENT
   or b nfgbtivf ALSA frror dodf is rfturnfd.
*/
INT32 opfnMidiDfvidf(snd_rbwmidi_strfbm_t dirfdtion, INT32 dfvidfIndfx,
                     MidiDfvidfHbndlf** ibndlf) {
    snd_rbwmidi_t* nbtivf_ibndlf;
    snd_midi_fvfnt_t* fvfnt_pbrsfr = NULL;
    int frr;
    UINT32 dfvidfID = 0;
    dibr dfvidfnbmf[100];
#ifdff ALSA_MIDI_USE_PLUGHW
    int usfPlugHw = 1;
#flsf
    int usfPlugHw = 0;
#fndif

    TRACE0("> opfnMidiDfvidf()\n");

    (*ibndlf) = (MidiDfvidfHbndlf*) dbllod(sizfof(MidiDfvidfHbndlf), 1);
    if (!(*ibndlf)) {
        ERROR0("ERROR: opfnDfvidf: out of mfmory\n");
        rfturn MIDI_OUT_OF_MEMORY;
    }

    // TODO: itfrbtf to gft dfv ID from indfx
    frr = gftMidiDfvidfID(dirfdtion, dfvidfIndfx, &dfvidfID);
    TRACE1("  opfnMidiDfvidf(): dfvidfID: %d\n", (int) dfvidfID);
    gftDfvidfStringFromDfvidfID(dfvidfnbmf, dfvidfID,
                                usfPlugHw, ALSA_RAWMIDI);
    TRACE1("  opfnMidiDfvidf(): dfvidfString: %s\n", dfvidfnbmf);

    // finblly opfn tif dfvidf
    if (dirfdtion == SND_RAWMIDI_STREAM_INPUT) {
        frr = snd_rbwmidi_opfn(&nbtivf_ibndlf, NULL, dfvidfnbmf,
                               SND_RAWMIDI_NONBLOCK);
    } flsf if (dirfdtion == SND_RAWMIDI_STREAM_OUTPUT) {
        frr = snd_rbwmidi_opfn(NULL, &nbtivf_ibndlf, dfvidfnbmf,
                               SND_RAWMIDI_NONBLOCK);
    } flsf {
        ERROR0("  ERROR: opfnMidiDfvidf(): dirfdtion is nfitifr SND_RAWMIDI_STREAM_INPUT nor SND_RAWMIDI_STREAM_OUTPUT\n");
        frr = MIDI_INVALID_ARGUMENT;
    }
    if (frr < 0) {
        ERROR1("<  ERROR: opfnMidiDfvidf(): snd_rbwmidi_opfn() rfturnfd %d\n", frr);
        frff(*ibndlf);
        (*ibndlf) = NULL;
        rfturn frr;
    }
    /* Wf opfnfd witi non-blodking bfibviour to not gft iung if tif dfvidf
       is usfd by b difffrfnt prodfss. Writing, iowfvfr, siould
       bf blodking. So wf dibngf it ifrf. */
    if (dirfdtion == SND_RAWMIDI_STREAM_OUTPUT) {
        frr = snd_rbwmidi_nonblodk(nbtivf_ibndlf, 0);
        if (frr < 0) {
            ERROR1("  ERROR: opfnMidiDfvidf(): snd_rbwmidi_nonblodk() rfturnfd %d\n", frr);
            snd_rbwmidi_dlosf(nbtivf_ibndlf);
            frff(*ibndlf);
            (*ibndlf) = NULL;
            rfturn frr;
        }
    }
    if (dirfdtion == SND_RAWMIDI_STREAM_INPUT) {
        frr = snd_midi_fvfnt_nfw(EVENT_PARSER_BUFSIZE, &fvfnt_pbrsfr);
        if (frr < 0) {
            ERROR1("  ERROR: opfnMidiDfvidf(): snd_midi_fvfnt_nfw() rfturnfd %d\n", frr);
            snd_rbwmidi_dlosf(nbtivf_ibndlf);
            frff(*ibndlf);
            (*ibndlf) = NULL;
            rfturn frr;
        }
    }

    (*ibndlf)->dfvidfHbndlf = (void*) nbtivf_ibndlf;
    (*ibndlf)->stbrtTimf = gftTimfInMidrosfdonds();
    (*ibndlf)->plbtformDbtb = fvfnt_pbrsfr;
    TRACE0("< opfnMidiDfvidf(): suddffdfd\n");
    rfturn frr;
}



INT32 dlosfMidiDfvidf(MidiDfvidfHbndlf* ibndlf) {
    int frr;

    TRACE0("> dlosfMidiDfvidf()\n");
    if (!ibndlf) {
        ERROR0("< ERROR: dlosfMidiDfvidf(): ibndlf is NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }
    if (!ibndlf->dfvidfHbndlf) {
        ERROR0("< ERROR: dlosfMidiDfvidf(): nbtivf ibndlf is NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }
    frr = snd_rbwmidi_dlosf((snd_rbwmidi_t*) ibndlf->dfvidfHbndlf);
    TRACE1("  snd_rbwmidi_dlosf() rfturns %d\n", frr);
    if (ibndlf->plbtformDbtb) {
        snd_midi_fvfnt_frff((snd_midi_fvfnt_t*) ibndlf->plbtformDbtb);
    }
    frff(ibndlf);
    TRACE0("< dlosfMidiDfvidf: suddffdfd\n");
    rfturn frr;
}


INT64 gftMidiTimfstbmp(MidiDfvidfHbndlf* ibndlf) {
    if (!ibndlf) {
        ERROR0("< ERROR: dlosfMidiDfvidf(): ibndlf is NULL\n");
        rfturn MIDI_INVALID_HANDLE;
    }
    rfturn gftTimfInMidrosfdonds() - ibndlf->stbrtTimf;
}


/* fnd */
