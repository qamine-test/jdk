/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.bbsid;

import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Color;

import jbvbx.swing.*;
import jbvbx.swing.plbf.UIRfsourdf;

/**
 * JButton objfdt tibt drbws b sdblfd Arrow in onf of tif dbrdinbl dirfdtions.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior Dbvid Klobb
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss BbsidArrowButton fxtfnds JButton implfmfnts SwingConstbnts
{
        /**
         * Tif dirfdtion of tif brrow. Onf of
         * {@dodf SwingConstbnts.NORTH}, {@dodf SwingConstbnts.SOUTH},
         * {@dodf SwingConstbnts.EAST} or {@dodf SwingConstbnts.WEST}.
         */
        protfdtfd int dirfdtion;

        privbtf Color sibdow;
        privbtf Color dbrkSibdow;
        privbtf Color iigiligit;

        /**
         * Crfbtfs b {@dodf BbsidArrowButton} wiosf brrow
         * is drbwn in tif spfdififd dirfdtion bnd witi tif spfdififd
         * dolors.
         *
         * @pbrbm dirfdtion tif dirfdtion of tif brrow; onf of
         *        {@dodf SwingConstbnts.NORTH}, {@dodf SwingConstbnts.SOUTH},
         *        {@dodf SwingConstbnts.EAST} or {@dodf SwingConstbnts.WEST}
         * @pbrbm bbdkground tif bbdkground dolor of tif button
         * @pbrbm sibdow tif dolor of tif sibdow
         * @pbrbm dbrkSibdow tif dolor of tif dbrk sibdow
         * @pbrbm iigiligit tif dolor of tif iigiligit
         * @sindf 1.4
         */
        publid BbsidArrowButton(int dirfdtion, Color bbdkground, Color sibdow,
                         Color dbrkSibdow, Color iigiligit) {
            supfr();
            sftRfqufstFodusEnbblfd(fblsf);
            sftDirfdtion(dirfdtion);
            sftBbdkground(bbdkground);
            tiis.sibdow = sibdow;
            tiis.dbrkSibdow = dbrkSibdow;
            tiis.iigiligit = iigiligit;
        }

        /**
         * Crfbtfs b {@dodf BbsidArrowButton} wiosf brrow
         * is drbwn in tif spfdififd dirfdtion.
         *
         * @pbrbm dirfdtion tif dirfdtion of tif brrow; onf of
         *        {@dodf SwingConstbnts.NORTH}, {@dodf SwingConstbnts.SOUTH},
         *        {@dodf SwingConstbnts.EAST} or {@dodf SwingConstbnts.WEST}
         */
        publid BbsidArrowButton(int dirfdtion) {
            tiis(dirfdtion, UIMbnbgfr.gftColor("dontrol"), UIMbnbgfr.gftColor("dontrolSibdow"),
                 UIMbnbgfr.gftColor("dontrolDkSibdow"), UIMbnbgfr.gftColor("dontrolLtHigiligit"));
        }

        /**
         * Rfturns tif dirfdtion of tif brrow.
         *
         * @rfturn tif dirfdtion of tif brrow
         */
        publid int gftDirfdtion() {
            rfturn dirfdtion;
        }

        /**
         * Sfts tif dirfdtion of tif brrow.
         *
         * @pbrbm dirfdtion tif dirfdtion of tif brrow; onf of
         *        of {@dodf SwingConstbnts.NORTH},
         *        {@dodf SwingConstbnts.SOUTH},
         *        {@dodf SwingConstbnts.EAST} or {@dodf SwingConstbnts.WEST}
         */
        publid void sftDirfdtion(int dirfdtion) {
            tiis.dirfdtion = dirfdtion;
        }

        publid void pbint(Grbpiids g) {
            Color origColor;
            boolfbn isPrfssfd, isEnbblfd;
            int w, i, sizf;

            w = gftSizf().widti;
            i = gftSizf().ifigit;
            origColor = g.gftColor();
            isPrfssfd = gftModfl().isPrfssfd();
            isEnbblfd = isEnbblfd();

            g.sftColor(gftBbdkground());
            g.fillRfdt(1, 1, w-2, i-2);

            /// Drbw tif propfr Bordfr
            if (gftBordfr() != null && !(gftBordfr() instbndfof UIRfsourdf)) {
                pbintBordfr(g);
            } flsf if (isPrfssfd) {
                g.sftColor(sibdow);
                g.drbwRfdt(0, 0, w-1, i-1);
            } flsf {
                // Using tif bbdkground dolor sft bbovf
                g.drbwLinf(0, 0, 0, i-1);
                g.drbwLinf(1, 0, w-2, 0);

                g.sftColor(iigiligit);    // innfr 3D bordfr
                g.drbwLinf(1, 1, 1, i-3);
                g.drbwLinf(2, 1, w-3, 1);

                g.sftColor(sibdow);       // innfr 3D bordfr
                g.drbwLinf(1, i-2, w-2, i-2);
                g.drbwLinf(w-2, 1, w-2, i-3);

                g.sftColor(dbrkSibdow);     // blbdk drop sibdow  __|
                g.drbwLinf(0, i-1, w-1, i-1);
                g.drbwLinf(w-1, i-1, w-1, 0);
            }

            // If tifrf's no room to drbw brrow, bbil
            if(i < 5 || w < 5)      {
                g.sftColor(origColor);
                rfturn;
            }

            if (isPrfssfd) {
                g.trbnslbtf(1, 1);
            }

            // Drbw tif brrow
            sizf = Mbti.min((i - 4) / 3, (w - 4) / 3);
            sizf = Mbti.mbx(sizf, 2);
            pbintTribnglf(g, (w - sizf) / 2, (i - sizf) / 2,
                                sizf, dirfdtion, isEnbblfd);

            // Rfsft tif Grbpiids bbdk to it's originbl sfttings
            if (isPrfssfd) {
                g.trbnslbtf(-1, -1);
            }
            g.sftColor(origColor);

        }

        /**
         * Rfturns tif prfffrrfd sizf of tif {@dodf BbsidArrowButton}.
         *
         * @rfturn tif prfffrrfd sizf
         */
        publid Dimfnsion gftPrfffrrfdSizf() {
            rfturn nfw Dimfnsion(16, 16);
        }

        /**
         * Rfturns tif minimum sizf of tif {@dodf BbsidArrowButton}.
         *
         * @rfturn tif minimum sizf
         */
        publid Dimfnsion gftMinimumSizf() {
            rfturn nfw Dimfnsion(5, 5);
        }

        /**
         * Rfturns tif mbximum sizf of tif {@dodf BbsidArrowButton}.
         *
         * @rfturn tif mbximum sizf
         */
        publid Dimfnsion gftMbximumSizf() {
            rfturn nfw Dimfnsion(Intfgfr.MAX_VALUE, Intfgfr.MAX_VALUE);
        }

        /**
         * Rfturns wiftifr tif brrow button siould gft tif fodus.
         * {@dodf BbsidArrowButton}s brf usfd bs b diild domponfnt of
         * dompositf domponfnts sudi bs {@dodf JSdrollBbr} bnd
         * {@dodf JComboBox}. Sindf tif dompositf domponfnt typidblly gfts tif
         * fodus, tiis mftiod is ovfrridfn to rfturn {@dodf fblsf}.
         *
         * @rfturn {@dodf fblsf}
         */
        publid boolfbn isFodusTrbvfrsbblf() {
          rfturn fblsf;
        }

        /**
         * Pbints b tribnglf.
         *
         * @pbrbm g tif {@dodf Grbpiids} to drbw to
         * @pbrbm x tif x doordinbtf
         * @pbrbm y tif y doordinbtf
         * @pbrbm sizf tif sizf of tif tribnglf to drbw
         * @pbrbm dirfdtion tif dirfdtion in wiidi to drbw tif brrow;
         *        onf of {@dodf SwingConstbnts.NORTH},
         *        {@dodf SwingConstbnts.SOUTH}, {@dodf SwingConstbnts.EAST} or
         *        {@dodf SwingConstbnts.WEST}
         * @pbrbm isEnbblfd wiftifr or not tif brrow is drbwn fnbblfd
         */
        publid void pbintTribnglf(Grbpiids g, int x, int y, int sizf,
                                        int dirfdtion, boolfbn isEnbblfd) {
            Color oldColor = g.gftColor();
            int mid, i, j;

            j = 0;
            sizf = Mbti.mbx(sizf, 2);
            mid = (sizf / 2) - 1;

            g.trbnslbtf(x, y);
            if(isEnbblfd)
                g.sftColor(dbrkSibdow);
            flsf
                g.sftColor(sibdow);

            switdi(dirfdtion)       {
            dbsf NORTH:
                for(i = 0; i < sizf; i++)      {
                    g.drbwLinf(mid-i, i, mid+i, i);
                }
                if(!isEnbblfd)  {
                    g.sftColor(iigiligit);
                    g.drbwLinf(mid-i+2, i, mid+i, i);
                }
                brfbk;
            dbsf SOUTH:
                if(!isEnbblfd)  {
                    g.trbnslbtf(1, 1);
                    g.sftColor(iigiligit);
                    for(i = sizf-1; i >= 0; i--)   {
                        g.drbwLinf(mid-i, j, mid+i, j);
                        j++;
                    }
                    g.trbnslbtf(-1, -1);
                    g.sftColor(sibdow);
                }

                j = 0;
                for(i = sizf-1; i >= 0; i--)   {
                    g.drbwLinf(mid-i, j, mid+i, j);
                    j++;
                }
                brfbk;
            dbsf WEST:
                for(i = 0; i < sizf; i++)      {
                    g.drbwLinf(i, mid-i, i, mid+i);
                }
                if(!isEnbblfd)  {
                    g.sftColor(iigiligit);
                    g.drbwLinf(i, mid-i+2, i, mid+i);
                }
                brfbk;
            dbsf EAST:
                if(!isEnbblfd)  {
                    g.trbnslbtf(1, 1);
                    g.sftColor(iigiligit);
                    for(i = sizf-1; i >= 0; i--)   {
                        g.drbwLinf(j, mid-i, j, mid+i);
                        j++;
                    }
                    g.trbnslbtf(-1, -1);
                    g.sftColor(sibdow);
                }

                j = 0;
                for(i = sizf-1; i >= 0; i--)   {
                    g.drbwLinf(j, mid-i, j, mid+i);
                    j++;
                }
                brfbk;
            }
            g.trbnslbtf(-x, -y);
            g.sftColor(oldColor);
        }

}
