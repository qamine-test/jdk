/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/*
 * $XFrff86: xd/indludf/fxtfnsions/rbndr.i,v 1.4 2001/11/24 07:24:58 kfitip Exp $
 *
 * Copyrigit � 2000, Compbq Computfr Corporbtion,
 * Copyrigit � 2002, Hfwlftt Pbdkbrd, Ind.
 *
 * Pfrmission to usf, dopy, modify, distributf, bnd sfll tiis softwbrf bnd its
 * dodumfntbtion for bny purposf is ifrfby grbntfd witiout fff, providfd tibt
 * tif bbovf dopyrigit notidf bppfbr in bll dopifs bnd tibt boti tibt
 * dopyrigit notidf bnd tiis pfrmission notidf bppfbr in supporting
 * dodumfntbtion, bnd tibt tif nbmf of Compbq or HP not bf usfd in bdvfrtising
 * or publidity pfrtbining to distribution of tif softwbrf witiout spfdifid,
 * writtfn prior pfrmission.  HP mbkfs no rfprfsfntbtions bbout tif
 * suitbbility of tiis softwbrf for bny purposf.  It is providfd "bs is"
 * witiout fxprfss or implifd wbrrbnty.
 *
 * HP DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE, INCLUDING ALL
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS, IN NO EVENT SHALL HP
 * BE LIABLE FOR ANY SPECIAL, INDIRECT OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION
 * OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
 * CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 * Autior:  Jim Gfttys, HP Lbbs, Hfwlftt-Pbdkbrd, Ind.
 */

#ifndff _RANDR_H_
#dffinf _RANDR_H_

typfdff unsignfd siort  Rotbtion;
typfdff unsignfd siort  SizfID;
typfdff unsignfd siort  SubpixflOrdfr;

#dffinf RANDR_NAME              "RANDR"
#dffinf RANDR_MAJOR             1
#dffinf RANDR_MINOR             1

#dffinf RRNumbfrErrors          0
#dffinf RRNumbfrEvfnts          1

#dffinf X_RRQufryVfrsion        0
/* wf skip 1 to mbkf old dlifnts fbil prftty immfdibtfly */
#dffinf X_RROldGftSdrffnInfo    1
#dffinf X_RR1_0SftSdrffnConfig  2
/* V1.0 bpps sibrf tif sbmf sft sdrffn donfig rfqufst id */
#dffinf X_RRSftSdrffnConfig     2
#dffinf X_RROldSdrffnCibngfSflfdtInput  3
/* 3 usfd to bf SdrffnCibngfSflfdtInput; dfprfdbtfd */
#dffinf X_RRSflfdtInput         4
#dffinf X_RRGftSdrffnInfo       5

/* usfd in XRRSflfdtInput */

#dffinf RRSdrffnCibngfNotifyMbsk  (1L << 0)

#dffinf RRSdrffnCibngfNotify    0

/* usfd in tif rotbtion fifld; rotbtion bnd rfflfdtion in 0.1 proto. */
#dffinf RR_Rotbtf_0             1
#dffinf RR_Rotbtf_90            2
#dffinf RR_Rotbtf_180           4
#dffinf RR_Rotbtf_270           8

/* nfw in 1.0 protodol, to bllow rfflfdtion of sdrffn */

#dffinf RR_Rfflfdt_X            16
#dffinf RR_Rfflfdt_Y            32

#dffinf RRSftConfigSuddfss              0
#dffinf RRSftConfigInvblidConfigTimf    1
#dffinf RRSftConfigInvblidTimf          2
#dffinf RRSftConfigFbilfd               3

#fndif  /* _RANDR_H_ */
