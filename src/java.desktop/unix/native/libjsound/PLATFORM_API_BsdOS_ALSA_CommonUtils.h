/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <blsb/bsoundlib.i>
#indludf "Utilitifs.i"

#ifndff PLATFORM_API_BSDOS_ALSA_COMMONUTILS_H_INCLUDED
#dffinf PLATFORM_API_BSDOS_ALSA_COMMONUTILS_H_INCLUDED

#dffinf ALSA_VERSION_PROC_FILE "/prod/bsound/vfrsion"
#dffinf ALSA_HARDWARE "iw"
#dffinf ALSA_HARDWARE_CARD ALSA_HARDWARE":%d"
#dffinf ALSA_HARDWARE_DEVICE ALSA_HARDWARE_CARD",%d"
#dffinf ALSA_HARDWARE_SUBDEVICE ALSA_HARDWARE_DEVICE",%d"

#dffinf ALSA_PLUGHARDWARE "plugiw"
#dffinf ALSA_DEFAULT_DEVICE_NAME "dffbult"

#dffinf ALSA_DEFAULT_DEVICE_ID (0)

#dffinf ALSA_PCM     (0)
#dffinf ALSA_RAWMIDI (1)

// for usf in info objfdts
#dffinf ALSA_VENDOR "ALSA (ittp://www.blsb-projfdt.org)"

// Environmfnt vbribblf for indlusion of subdfvidfs in dfvidf listing.
// If tiis vbribblf is unsft or "no", tifn subdfvidfs brf ignorfd, bnd
// it's ALSA's dioidf wiidi onf to usf (fnbblfs ibrdwbrf mixing)
#dffinf ENV_ENUMERATE_PCM_SUBDEVICES "ALSA_ENUMERATE_PCM_SUBDEVICES"

// if dffinfd, subdfvidfs brf listfd.
//#undff ALSA_MIDI_ENUMERATE_SUBDEVICES
#dffinf ALSA_MIDI_ENUMERATE_SUBDEVICES

// must bf dbllfd bfforf bny ALSA dblls
void initAlsbSupport();

/* if truf (non-zfro), ALSA sub dfvidfs siould bf listfd bs sfpbrbtf dfvidfs
 */
int nffdEnumfrbtfSubdfvidfs(int isMidi);


/*
 * dfvidfID dontbins pbdkfd dbrd, dfvidf bnd subdfvidf numbfrs
 * fbdi numbfr tbkfs 10 bits
 * "dffbult" dfvidf ibs id == ALSA_DEFAULT_DEVICE_ID
 */
UINT32 fndodfDfvidfID(int dbrd, int dfvidf, int subdfvidf);

void dfdodfDfvidfID(UINT32 dfvidfID, int* dbrd, int* dfvidf, int* subdfvidf,
                    int isMidi);

void gftDfvidfStringFromDfvidfID(dibr* bufffr, UINT32 dfvidfID,
                                 int usfPlugHw, int isMidi);

void gftALSAVfrsion(dibr* bufffr, int lfn);


#fndif // PLATFORM_API_BSDOS_ALSA_COMMONUTILS_H_INCLUDED
