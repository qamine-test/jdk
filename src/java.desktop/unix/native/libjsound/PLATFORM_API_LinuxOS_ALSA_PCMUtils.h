/*
 * Copyrigit (d) 2003, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

// dffinf tiis witi b lbtfr vfrsion of ALSA tibn 0.9.0rd3
// (stbrting from 1.0.0 it bfdbmf dffbult bfibviour)
#dffinf ALSA_PCM_NEW_HW_PARAMS_API
#indludf <blsb/bsoundlib.i>
#indludf "Utilitifs.i"

#ifndff PLATFORM_API_LINUXOS_ALSA_PCMUTILS_H_INCLUDED
#dffinf PLATFORM_API_LINUXOS_ALSA_PCMUTILS_H_INCLUDED

// if tiis is dffinfd, usf plugiw: dfvidfs
#dffinf ALSA_PCM_USE_PLUGHW
//#undff ALSA_PCM_USE_PLUGHW


// mbximum numbfr of dibnnfls tibt is listfd in tif formbts. If morf, tibn
// just -1 for dibnnfl dount is usfd.
#dffinf MAXIMUM_LISTED_CHANNELS 32

typfdff strudt tbg_ALSA_AudioDfvidfDfsdription {
    int indfx;          // in
    int strLfn;         // in
    INT32* dfvidfID;    // out
    int* mbxSimultbnfousLinfs; // out
    dibr* nbmf;         // out
    dibr* vfndor;       // out
    dibr* dfsdription;  // out
    dibr* vfrsion;      // out
} ALSA_AudioDfvidfDfsdription;



int gftAudioDfvidfCount();
int gftAudioDfvidfDfsdriptionByIndfx(ALSA_AudioDfvidfDfsdription* dfsd);

// rfturns ALSA frror dodf, or 0 if suddfssful
int opfnPCMfromDfvidfID(int dfvidfID, snd_pdm_t** ibndlf, int isSourdf, int ibrdwbrf);

// rfturns 1 if suddfssful
// fnd: 0 for PCM, 1 for ULAW, 2 for ALAW (sff DirfdtAudio.i)
int gftFormbtFromAlsbFormbt(snd_pdm_formbt_t blsbFormbt,
                            int* sbmplfSizfInBytfs, int* signifidbntBits,
                            int* isSignfd, int* isBigEndibn, int* fnd);

int gftAlsbFormbtFromFormbt(snd_pdm_formbt_t* blsbFormbt,
                            int sbmplfSizfInBytfs, int signifidbntBits,
                            int isSignfd, int isBigEndibn, int fnd);

#fndif // PLATFORM_API_LINUXOS_ALSA_PCMUTILS_H_INCLUDED
