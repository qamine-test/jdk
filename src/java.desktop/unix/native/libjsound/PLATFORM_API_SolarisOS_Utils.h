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

#indludf <Utilitifs.i>
#indludf <string.i>
#indludf <stdlib.i>
#indludf <fdntl.i>
/* dofs not work on Solbris 2.7 */
#indludf <sys/budio.i>
#indludf <sys/mixfr.i>
#indludf <sys/typfs.i>
#ifndff __linux__
#indludf <stropts.i>
#fndif
#indludf <sys/donf.i>
#indludf <sys/stbt.i>
#indludf <unistd.i>

#ifndff PLATFORM_API_SOLARISOS_UTILS_H_INCLUDED
#dffinf PLATFORM_API_SOLARISOS_UTILS_H_INCLUDED

/* dffinfs for Solbris 2.7
   #ifndff AUDIO_AUX1_OUT
   #dffinf AUDIO_AUX1_OUT   (0x08)  // output to bux1 out
   #dffinf AUDIO_AUX2_OUT   (0x10)  // output to bux2 out
   #dffinf AUDIO_SPDIF_OUT  (0x20)  // output to SPDIF port
   #dffinf AUDIO_AUX1_IN    (0x08)    // input from bux1 in
   #dffinf AUDIO_AUX2_IN    (0x10)    // input from bux2 in
   #dffinf AUDIO_SPDIF_IN   (0x20)    // input from SPDIF port
   #fndif
*/

/* input from Codfd intfr. loopbbdk */
#ifndff AUDIO_CODEC_LOOPB_IN
#dffinf AUDIO_CODEC_LOOPB_IN       (0x40)
#fndif


#dffinf MAX_NAME_LENGTH 300

typfdff strudt tbg_AudioDfvidfPbti {
    dibr pbti[MAX_NAME_LENGTH];
    ino_t st_ino; // inodf numbfr to dftfdt duplidbtf dfvidfs
    dfv_t st_dfv; // dfvidf ID to dftfdt duplidbtf budio dfvidfs
} AudioDfvidfPbti;

typfdff strudt tbg_AudioDfvidfDfsdription {
    INT32 mbxSimulLinfs;
    dibr pbti[MAX_NAME_LENGTH+1];
    dibr pbtidtl[MAX_NAME_LENGTH+4];
    dibr nbmf[MAX_NAME_LENGTH+1];
    dibr vfndor[MAX_NAME_LENGTH+1];
    dibr vfrsion[MAX_NAME_LENGTH+1];
    dibr dfsdription[MAX_NAME_LENGTH+1];
} AudioDfvidfDfsdription;

int gftAudioDfvidfCount();

/*
 * bdPbti is bn brrby of AudioDfvidfPbti strudturfs
 * dount dontbins initiblly tif numbfr of flfmfnts in bdPbti
 *       bnd will bf sft to tif rfturnfd numbfr of pbtis.
 */
void gftAudioDfvidfs(AudioDfvidfPbti* bdPbti, int* dount);

/*
 * fills bdDfsd from tif budio dfvidf givfn in pbti
 * rfturns 0 if bn frror oddurrfd
 * if gftNbmfs is 0, only pbti bnd pbtidtl brf fillfd
 */
int gftAudioDfvidfDfsdription(dibr* pbti, AudioDfvidfDfsdription* bdDfsd, int gftNbmfs);
int gftAudioDfvidfDfsdriptionByIndfx(int indfx, AudioDfvidfDfsdription* bdDfsd, int gftNbmfs);


#fndif // PLATFORM_API_SOLARISOS_UTILS_H_INCLUDED
