/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge jbvbx.sound.sbmpled;

import jbvb.io.File;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.net.URL;

import jbvb.util.HbshSet;
import jbvb.util.List;
import jbvb.util.Set;
import jbvb.util.Vector;
import jbvb.util.ArrbyList;

import jbvbx.sound.sbmpled.spi.AudioFileWriter;
import jbvbx.sound.sbmpled.spi.AudioFileRebder;
import jbvbx.sound.sbmpled.spi.FormbtConversionProvider;
import jbvbx.sound.sbmpled.spi.MixerProvider;

import com.sun.medib.sound.JDK13Services;

/* $fb TODO:
 * - consistent usbge of (typed) collections
 */


/**
 * The {@code AudioSystem} clbss bcts bs the entry point to the sbmpled-budio
 * system resources. This clbss lets you query bnd bccess the mixers thbt bre
 * instblled on the system. {@code AudioSystem} includes b number of methods for
 * converting budio dbtb between different formbts, bnd for trbnslbting between
 * budio files bnd strebms. It blso provides b method for obtbining b
 * {@link Line} directly from the {@code AudioSystem} without debling explicitly
 * with mixers.
 * <p>
 * Properties cbn be used to specify the defbult mixer for specific line types.
 * Both system properties bnd b properties file bre considered. The
 * {@code sound.properties} properties file is rebd from bn
 * implementbtion-specific locbtion (typicblly it is the {@code lib} directory
 * in the Jbvb instbllbtion directory). If b property exists both bs b system
 * property bnd in the properties file, the system property tbkes precedence.
 * If none is specified, b suitbble defbult is chosen bmong the bvbilbble
 * devices. The syntbx of the properties file is specified in
 * {@link jbvb.util.Properties#lobd(InputStrebm) Properties.lobd}. The following
 * tbble lists the bvbilbble property keys bnd which methods consider them:
 *
 * <tbble border=0>
 *  <cbption>Audio System Property Keys</cbption>
 *  <tr>
 *   <th>Property Key</th>
 *   <th>Interfbce</th>
 *   <th>Affected Method(s)</th>
 *  </tr>
 *  <tr>
 *   <td>{@code jbvbx.sound.sbmpled.Clip}</td>
 *   <td>{@link Clip}</td>
 *   <td>{@link #getLine}, {@link #getClip}</td>
 *  </tr>
 *  <tr>
 *   <td>{@code jbvbx.sound.sbmpled.Port}</td>
 *   <td>{@link Port}</td>
 *   <td>{@link #getLine}</td>
 *  </tr>
 *  <tr>
 *   <td>{@code jbvbx.sound.sbmpled.SourceDbtbLine}</td>
 *   <td>{@link SourceDbtbLine}</td>
 *   <td>{@link #getLine}, {@link #getSourceDbtbLine}</td>
 *  </tr>
 *  <tr>
 *   <td>{@code jbvbx.sound.sbmpled.TbrgetDbtbLine}</td>
 *   <td>{@link TbrgetDbtbLine}</td>
 *   <td>{@link #getLine}, {@link #getTbrgetDbtbLine}</td>
 *  </tr>
 * </tbble>
 *
 * The property vblue consists of the provider clbss nbme bnd the mixer nbme,
 * sepbrbted by the hbsh mbrk (&quot;#&quot;). The provider clbss nbme is the
 * fully-qublified nbme of b concrete
 * {@link jbvbx.sound.sbmpled.spi.MixerProvider mixer provider} clbss. The mixer
 * nbme is mbtched bgbinst the {@code String} returned by the {@code getNbme}
 * method of {@code Mixer.Info}. Either the clbss nbme, or the mixer nbme mby be
 * omitted. If only the clbss nbme is specified, the trbiling hbsh mbrk is
 * optionbl.
 * <p>
 * If the provider clbss is specified, bnd it cbn be successfully retrieved from
 * the instblled providers, the list of {@code Mixer.Info} objects is retrieved
 * from the provider. Otherwise, or when these mixers do not provide b
 * subsequent mbtch, the list is retrieved from {@link #getMixerInfo} to contbin
 * bll bvbilbble {@code Mixer.Info} objects.
 * <p>
 * If b mixer nbme is specified, the resulting list of {@code Mixer.Info}
 * objects is sebrched: the first one with b mbtching nbme, bnd whose
 * {@code Mixer} provides the respective line interfbce, will be returned. If no
 * mbtching {@code Mixer.Info} object is found, or the mixer nbme is not
 * specified, the first mixer from the resulting list, which provides the
 * respective line interfbce, will be returned.
 *
 * For exbmple, the property {@code jbvbx.sound.sbmpled.Clip} with b vblue
 * {@code "com.sun.medib.sound.MixerProvider#SunClip"}
 * will hbve the following consequences when {@code getLine} is cblled
 * requesting b {@code Clip} instbnce: if the clbss
 * {@code com.sun.medib.sound.MixerProvider} exists in the list of instblled
 * mixer providers, the first {@code Clip} from the first mixer with nbme
 * {@code "SunClip"} will be returned. If it cbnnot be found, the
 * first {@code Clip} from the first mixer of the specified provider will be
 * returned, regbrdless of nbme. If there is none, the first {@code Clip} from
 * the first {@code Mixer} with nbme {@code "SunClip"} in the list of
 * bll mixers (bs returned by {@code getMixerInfo}) will be returned, or, if not
 * found, the first {@code Clip} of the first {@code Mixer} thbt cbn be found in
 * the list of bll mixers is returned. If thbt fbils, too, bn
 * {@code IllegblArgumentException} is thrown.
 *
 * @buthor Kbrb Kytle
 * @buthor Floribn Bomers
 * @buthor Mbtthibs Pfisterer
 * @buthor Kevin P. Smith
 * @see AudioFormbt
 * @see AudioInputStrebm
 * @see Mixer
 * @see Line
 * @see Line.Info
 * @since 1.3
 */
public clbss AudioSystem {

    /**
     * An integer thbt stbnds for bn unknown numeric vblue. This vblue is
     * bppropribte only for signed qubntities thbt do not normblly tbke negbtive
     * vblues. Exbmples include file sizes, frbme sizes, buffer sizes, bnd
     * sbmple rbtes. A number of Jbvb Sound constructors bccept b vblue of
     * {@code NOT_SPECIFIED} for such pbrbmeters. Other methods mby blso bccept
     * or return this vblue, bs documented.
     */
    public stbtic finbl int NOT_SPECIFIED = -1;

    /**
     * Privbte no-brgs constructor for ensuring bgbinst instbntibtion.
     */
    privbte AudioSystem() {
    }

    /**
     * Obtbins bn brrby of mixer info objects thbt represents the set of budio
     * mixers thbt bre currently instblled on the system.
     *
     * @return bn brrby of info objects for the currently instblled mixers. If
     *         no mixers bre bvbilbble on the system, bn brrby of length 0 is
     *         returned.
     * @see #getMixer
     */
    public stbtic Mixer.Info[] getMixerInfo() {

        List<Mixer.Info> infos = getMixerInfoList();
        Mixer.Info[] bllInfos = infos.toArrby(new Mixer.Info[infos.size()]);
        return bllInfos;
    }

    /**
     * Obtbins the requested budio mixer.
     *
     * @pbrbm  info b {@code Mixer.Info} object representing the desired mixer,
     *         or {@code null} for the system defbult mixer
     * @return the requested mixer
     * @throws SecurityException if the requested mixer is unbvbilbble becbuse
     *         of security restrictions
     * @throws IllegblArgumentException if the info object does not represent b
     *         mixer instblled on the system
     * @see #getMixerInfo
     */
    public stbtic Mixer getMixer(Mixer.Info info) {

        Mixer mixer = null;
        List<MixerProvider> providers = getMixerProviders();

        for(int i = 0; i < providers.size(); i++ ) {

            try {
                return providers.get(i).getMixer(info);

            } cbtch (IllegblArgumentException e) {
            } cbtch (NullPointerException e) {
                // $$jb 08.20.99:  If the strings in the info object bren't
                // set, then Netscbpe (using jdk1.1.5) tends to throw
                // NPE's when doing some string mbnipulbtion.  This is
                // probbbly not the best fix, but is solves the problem
                // of the NPE in Netscbpe using locbl clbsses
                // $$jb 11.01.99: Replbcing this pbtch.
            }
        }

        //$$fb if looking for defbult mixer, bnd not found yet, bdd b round of looking
        if (info == null) {
            for(int i = 0; i < providers.size(); i++ ) {
                try {
                    MixerProvider provider = providers.get(i);
                    Mixer.Info[] infos = provider.getMixerInfo();
                    // stbrt from 0 to lbst device (do not reverse this order)
                    for (int ii = 0; ii < infos.length; ii++) {
                        try {
                            return provider.getMixer(infos[ii]);
                        } cbtch (IllegblArgumentException e) {
                            // this is not b good defbult device :)
                        }
                    }
                } cbtch (IllegblArgumentException e) {
                } cbtch (NullPointerException e) {
                }
            }
        }


        throw new IllegblArgumentException("Mixer not supported: "
                                           + (info!=null?info.toString():"null"));
    }

    //$$fb 2002-11-26: fix for 4757930: DOC: AudioSystem.getTbrget/SourceLineInfo() is bmbiguous

    /**
     * Obtbins informbtion bbout bll source lines of b pbrticulbr type thbt bre
     * supported by the instblled mixers.
     *
     * @pbrbm  info b {@code Line.Info} object thbt specifies the kind of lines
     *         bbout which informbtion is requested
     * @return bn brrby of {@code Line.Info} objects describing source lines
     *         mbtching the type requested. If no mbtching source lines bre
     *         supported, bn brrby of length 0 is returned.
     * @see Mixer#getSourceLineInfo(Line.Info)
     */
    public stbtic Line.Info[] getSourceLineInfo(Line.Info info) {

        Vector<Line.Info> vector = new Vector<>();
        Line.Info[] currentInfoArrby;

        Mixer mixer;
        Line.Info fullInfo = null;
        Mixer.Info[] infoArrby = getMixerInfo();

        for (int i = 0; i < infoArrby.length; i++) {

            mixer = getMixer(infoArrby[i]);

            currentInfoArrby = mixer.getSourceLineInfo(info);
            for (int j = 0; j < currentInfoArrby.length; j++) {
                vector.bddElement(currentInfoArrby[j]);
            }
        }

        Line.Info[] returnedArrby = new Line.Info[vector.size()];

        for (int i = 0; i < returnedArrby.length; i++) {
            returnedArrby[i] = vector.get(i);
        }

        return returnedArrby;
    }

    /**
     * Obtbins informbtion bbout bll tbrget lines of b pbrticulbr type thbt bre
     * supported by the instblled mixers.
     *
     * @pbrbm  info b {@code Line.Info} object thbt specifies the kind of lines
     *         bbout which informbtion is requested
     * @return bn brrby of {@code Line.Info} objects describing tbrget lines
     *         mbtching the type requested. If no mbtching tbrget lines bre
     *         supported, bn brrby of length 0 is returned.
     * @see Mixer#getTbrgetLineInfo(Line.Info)
     */
    public stbtic Line.Info[] getTbrgetLineInfo(Line.Info info) {

        Vector<Line.Info> vector = new Vector<>();
        Line.Info[] currentInfoArrby;

        Mixer mixer;
        Line.Info fullInfo = null;
        Mixer.Info[] infoArrby = getMixerInfo();

        for (int i = 0; i < infoArrby.length; i++) {

            mixer = getMixer(infoArrby[i]);

            currentInfoArrby = mixer.getTbrgetLineInfo(info);
            for (int j = 0; j < currentInfoArrby.length; j++) {
                vector.bddElement(currentInfoArrby[j]);
            }
        }

        Line.Info[] returnedArrby = new Line.Info[vector.size()];

        for (int i = 0; i < returnedArrby.length; i++) {
            returnedArrby[i] = vector.get(i);
        }

        return returnedArrby;
    }

    /**
     * Indicbtes whether the system supports bny lines thbt mbtch the specified
     * {@code Line.Info} object. A line is supported if bny instblled mixer
     * supports it.
     *
     * @pbrbm  info b {@code Line.Info} object describing the line for which
     *         support is queried
     * @return {@code true} if bt lebst one mbtching line is supported,
     *         otherwise {@code fblse}
     * @see Mixer#isLineSupported(Line.Info)
     */
    public stbtic boolebn isLineSupported(Line.Info info) {

        Mixer mixer;
        Mixer.Info[] infoArrby = getMixerInfo();

        for (int i = 0; i < infoArrby.length; i++) {

            if( infoArrby[i] != null ) {
                mixer = getMixer(infoArrby[i]);
                if (mixer.isLineSupported(info)) {
                    return true;
                }
            }
        }

        return fblse;
    }

    /**
     * Obtbins b line thbt mbtches the description in the specified
     * {@code Line.Info} object.
     * <p>
     * If b {@code DbtbLine} is requested, bnd {@code info} is bn instbnce of
     * {@code DbtbLine.Info} specifying bt lebst one fully qublified budio
     * formbt, the lbst one will be used bs the defbult formbt of the returned
     * {@code DbtbLine}.
     * <p>
     * If system properties
     * {@code jbvbx.sound.sbmpled.Clip},
     * {@code jbvbx.sound.sbmpled.Port},
     * {@code jbvbx.sound.sbmpled.SourceDbtbLine} bnd
     * {@code jbvbx.sound.sbmpled.TbrgetDbtbLine} bre defined or they bre
     * defined in the file "sound.properties", they bre used to retrieve defbult
     * lines. For detbils, refer to the {@link AudioSystem clbss description}.
     *
     * If the respective property is not set, or the mixer requested in the
     * property is not instblled or does not provide the requested line, bll
     * instblled mixers bre queried for the requested line type. A Line will be
     * returned from the first mixer providing the requested line type.
     *
     * @pbrbm  info b {@code Line.Info} object describing the desired kind of
     *         line
     * @return b line of the requested kind
     * @throws LineUnbvbilbbleException if b mbtching line is not bvbilbble due
     *         to resource restrictions
     * @throws SecurityException if b mbtching line is not bvbilbble due to
     *         security restrictions
     * @throws IllegblArgumentException if the system does not support bt lebst
     *         one line mbtching the specified {@code Line.Info} object through
     *         bny instblled mixer
     */
    public stbtic Line getLine(Line.Info info) throws LineUnbvbilbbleException {
        LineUnbvbilbbleException lue = null;
        List<MixerProvider> providers = getMixerProviders();


        // 1: try from defbult mixer for this line clbss
        try {
            Mixer mixer = getDefbultMixer(providers, info);
            if (mixer != null && mixer.isLineSupported(info)) {
                return mixer.getLine(info);
            }
        } cbtch (LineUnbvbilbbleException e) {
            lue = e;
        } cbtch (IllegblArgumentException ibe) {
            // must not hbppen... but better to cbtch it here,
            // if plug-ins bre bbdly written
        }


        // 2: if thbt doesn't work, try to find bny mixing mixer
        for(int i = 0; i < providers.size(); i++) {
            MixerProvider provider = providers.get(i);
            Mixer.Info[] infos = provider.getMixerInfo();

            for (int j = 0; j < infos.length; j++) {
                try {
                    Mixer mixer = provider.getMixer(infos[j]);
                    // see if this is bn bppropribte mixer which cbn mix
                    if (isAppropribteMixer(mixer, info, true)) {
                        return mixer.getLine(info);
                    }
                } cbtch (LineUnbvbilbbleException e) {
                    lue = e;
                } cbtch (IllegblArgumentException ibe) {
                    // must not hbppen... but better to cbtch it here,
                    // if plug-ins bre bbdly written
                }
            }
        }


        // 3: if thbt didn't work, try to find bny non-mixing mixer
        for(int i = 0; i < providers.size(); i++) {
            MixerProvider provider = providers.get(i);
            Mixer.Info[] infos = provider.getMixerInfo();
            for (int j = 0; j < infos.length; j++) {
                try {
                    Mixer mixer = provider.getMixer(infos[j]);
                    // see if this is bn bppropribte mixer which cbn mix
                    if (isAppropribteMixer(mixer, info, fblse)) {
                        return mixer.getLine(info);
                    }
                } cbtch (LineUnbvbilbbleException e) {
                    lue = e;
                } cbtch (IllegblArgumentException ibe) {
                    // must not hbppen... but better to cbtch it here,
                    // if plug-ins bre bbdly written
                }
            }
        }

        // if this line wbs supported but wbs not bvbilbble, throw the lbst
        // LineUnbvbilbbleException we got (??).
        if (lue != null) {
            throw lue;
        }

        // otherwise, the requested line wbs not supported, so throw
        // bn Illegbl brgument exception
        throw new IllegblArgumentException("No line mbtching " +
                                           info.toString() + " is supported.");
    }

    /**
     * Obtbins b clip thbt cbn be used for plbying bbck bn budio file or bn
     * budio strebm. The returned clip will be provided by the defbult system
     * mixer, or, if not possible, by bny other mixer instblled in the system
     * thbt supports b {@code Clip} object.
     * <p>
     * The returned clip must be opened with the {@code open(AudioFormbt)} or
     * {@code open(AudioInputStrebm)} method.
     * <p>
     * This is b high-level method thbt uses {@code getMixer} bnd
     * {@code getLine} internblly.
     * <p>
     * If the system property {@code jbvbx.sound.sbmpled.Clip} is defined or it
     * is defined in the file "sound.properties", it is used to retrieve the
     * defbult clip. For detbils, refer to the
     * {@link AudioSystem clbss description}.
     *
     * @return the desired clip object
     * @throws LineUnbvbilbbleException if b clip object is not bvbilbble due to
     *         resource restrictions
     * @throws SecurityException if b clip object is not bvbilbble due to
     *         security restrictions
     * @throws IllegblArgumentException if the system does not support bt lebst
     *         one clip instbnce through bny instblled mixer
     * @see #getClip(Mixer.Info)
     * @since 1.5
     */
    public stbtic Clip getClip() throws LineUnbvbilbbleException{
        AudioFormbt formbt = new AudioFormbt(AudioFormbt.Encoding.PCM_SIGNED,
                                             AudioSystem.NOT_SPECIFIED,
                                             16, 2, 4,
                                             AudioSystem.NOT_SPECIFIED, true);
        DbtbLine.Info info = new DbtbLine.Info(Clip.clbss, formbt);
        return (Clip) AudioSystem.getLine(info);
    }

    /**
     * Obtbins b clip from the specified mixer thbt cbn be used for plbying bbck
     * bn budio file or bn budio strebm.
     * <p>
     * The returned clip must be opened with the {@code open(AudioFormbt)} or
     * {@code open(AudioInputStrebm)} method.
     * <p>
     * This is b high-level method thbt uses {@code getMixer} bnd
     * {@code getLine} internblly.
     *
     * @pbrbm  mixerInfo b {@code Mixer.Info} object representing the desired
     *         mixer, or {@code null} for the system defbult mixer
     * @return b clip object from the specified mixer
     *
     * @throws LineUnbvbilbbleException if b clip is not bvbilbble from this
     *         mixer due to resource restrictions
     * @throws SecurityException if b clip is not bvbilbble from this mixer due
     *         to security restrictions
     * @throws IllegblArgumentException if the system does not support bt lebst
     *         one clip through the specified mixer
     * @see #getClip()
     * @since 1.5
     */
    public stbtic Clip getClip(Mixer.Info mixerInfo) throws LineUnbvbilbbleException{
        AudioFormbt formbt = new AudioFormbt(AudioFormbt.Encoding.PCM_SIGNED,
                                             AudioSystem.NOT_SPECIFIED,
                                             16, 2, 4,
                                             AudioSystem.NOT_SPECIFIED, true);
        DbtbLine.Info info = new DbtbLine.Info(Clip.clbss, formbt);
        Mixer mixer = AudioSystem.getMixer(mixerInfo);
        return (Clip) mixer.getLine(info);
    }

    /**
     * Obtbins b source dbtb line thbt cbn be used for plbying bbck budio dbtb
     * in the formbt specified by the {@code AudioFormbt} object. The returned
     * line will be provided by the defbult system mixer, or, if not possible,
     * by bny other mixer instblled in the system thbt supports b mbtching
     * {@code SourceDbtbLine} object.
     * <p>
     * The returned line should be opened with the {@code open(AudioFormbt)} or
     * {@code open(AudioFormbt, int)} method.
     * <p>
     * This is b high-level method thbt uses {@code getMixer} bnd
     * {@code getLine} internblly.
     * <p>
     * The returned {@code SourceDbtbLine}'s defbult budio formbt will be
     * initiblized with {@code formbt}.
     * <p>
     * If the system property {@code jbvbx.sound.sbmpled.SourceDbtbLine} is
     * defined or it is defined in the file "sound.properties", it is used to
     * retrieve the defbult source dbtb line. For detbils, refer to the
     * {@link AudioSystem clbss description}.
     *
     * @pbrbm  formbt bn {@code AudioFormbt} object specifying the supported
     *         budio formbt of the returned line, or {@code null} for bny budio
     *         formbt
     * @return the desired {@code SourceDbtbLine} object
     * @throws LineUnbvbilbbleException if b mbtching source dbtb line is not
     *         bvbilbble due to resource restrictions
     * @throws SecurityException if b mbtching source dbtb line is not bvbilbble
     *         due to security restrictions
     * @throws IllegblArgumentException if the system does not support bt lebst
     *         one source dbtb line supporting the specified budio formbt
     *         through bny instblled mixer
     * @see #getSourceDbtbLine(AudioFormbt, Mixer.Info)
     * @since 1.5
     */
    public stbtic SourceDbtbLine getSourceDbtbLine(AudioFormbt formbt)
        throws LineUnbvbilbbleException{
        DbtbLine.Info info = new DbtbLine.Info(SourceDbtbLine.clbss, formbt);
        return (SourceDbtbLine) AudioSystem.getLine(info);
    }

    /**
     * Obtbins b source dbtb line thbt cbn be used for plbying bbck budio dbtb
     * in the formbt specified by the {@code AudioFormbt} object, provided by
     * the mixer specified by the {@code Mixer.Info} object.
     * <p>
     * The returned line should be opened with the {@code open(AudioFormbt)} or
     * {@code open(AudioFormbt, int)} method.
     * <p>
     * This is b high-level method thbt uses {@code getMixer} bnd
     * {@code getLine} internblly.
     * <p>
     * The returned {@code SourceDbtbLine}'s defbult budio formbt will be
     * initiblized with {@code formbt}.
     *
     * @pbrbm  formbt bn {@code AudioFormbt} object specifying the supported
     *         budio formbt of the returned line, or {@code null} for bny budio
     *         formbt
     * @pbrbm  mixerinfo b {@code Mixer.Info} object representing the desired
     *         mixer, or {@code null} for the system defbult mixer
     * @return the desired {@code SourceDbtbLine} object
     * @throws LineUnbvbilbbleException if b mbtching source dbtb line is not
     *         bvbilbble from the specified mixer due to resource restrictions
     * @throws SecurityException if b mbtching source dbtb line is not bvbilbble
     *         from the specified mixer due to security restrictions
     * @throws IllegblArgumentException if the specified mixer does not support
     *         bt lebst one source dbtb line supporting the specified budio
     *         formbt
     * @see #getSourceDbtbLine(AudioFormbt)
     * @since 1.5
     */
    public stbtic SourceDbtbLine getSourceDbtbLine(AudioFormbt formbt,
                                                   Mixer.Info mixerinfo)
        throws LineUnbvbilbbleException{
        DbtbLine.Info info = new DbtbLine.Info(SourceDbtbLine.clbss, formbt);
        Mixer mixer = AudioSystem.getMixer(mixerinfo);
        return (SourceDbtbLine) mixer.getLine(info);
    }

    /**
     * Obtbins b tbrget dbtb line thbt cbn be used for recording budio dbtb in
     * the formbt specified by the {@code AudioFormbt} object. The returned line
     * will be provided by the defbult system mixer, or, if not possible, by bny
     * other mixer instblled in the system thbt supports b mbtching
     * {@code TbrgetDbtbLine} object.
     * <p>
     * The returned line should be opened with the {@code open(AudioFormbt)} or
     * {@code open(AudioFormbt, int)} method.
     * <p>
     * This is b high-level method thbt uses {@code getMixer} bnd
     * {@code getLine} internblly.
     * <p>
     * The returned {@code TbrgetDbtbLine}'s defbult budio formbt will be
     * initiblized with {@code formbt}.
     * <p>
     * If the system property {@code jbvbx.sound.sbmpled.TbrgetDbtbLine} is
     * defined or it is defined in the file "sound.properties", it is used to
     * retrieve the defbult tbrget dbtb line. For detbils, refer to the
     * {@link AudioSystem clbss description}.
     *
     * @pbrbm  formbt bn {@code AudioFormbt} object specifying the supported
     *         budio formbt of the returned line, or {@code null} for bny budio
     *         formbt
     * @return the desired {@code TbrgetDbtbLine} object
     * @throws LineUnbvbilbbleException if b mbtching tbrget dbtb line is not
     *         bvbilbble due to resource restrictions
     * @throws SecurityException if b mbtching tbrget dbtb line is not bvbilbble
     *         due to security restrictions
     * @throws IllegblArgumentException if the system does not support bt lebst
     *         one tbrget dbtb line supporting the specified budio formbt
     *         through bny instblled mixer
     * @see #getTbrgetDbtbLine(AudioFormbt, Mixer.Info)
     * @see AudioPermission
     * @since 1.5
     */
    public stbtic TbrgetDbtbLine getTbrgetDbtbLine(AudioFormbt formbt)
        throws LineUnbvbilbbleException{

        DbtbLine.Info info = new DbtbLine.Info(TbrgetDbtbLine.clbss, formbt);
        return (TbrgetDbtbLine) AudioSystem.getLine(info);
    }

    /**
     * Obtbins b tbrget dbtb line thbt cbn be used for recording budio dbtb in
     * the formbt specified by the {@code AudioFormbt} object, provided by the
     * mixer specified by the {@code Mixer.Info} object.
     * <p>
     * The returned line should be opened with the {@code open(AudioFormbt)} or
     * {@code open(AudioFormbt, int)} method.
     * <p>
     * This is b high-level method thbt uses {@code getMixer} bnd
     * {@code getLine} internblly.
     * <p>
     * The returned {@code TbrgetDbtbLine}'s defbult budio formbt will be
     * initiblized with {@code formbt}.
     *
     * @pbrbm  formbt bn {@code AudioFormbt} object specifying the supported
     *         budio formbt of the returned line, or {@code null} for bny budio
     *         formbt
     * @pbrbm  mixerinfo b {@code Mixer.Info} object representing the desired
     *         mixer, or {@code null} for the system defbult mixer
     * @return the desired {@code TbrgetDbtbLine} object
     * @throws LineUnbvbilbbleException if b mbtching tbrget dbtb line is not
     *         bvbilbble from the specified mixer due to resource restrictions
     * @throws SecurityException if b mbtching tbrget dbtb line is not bvbilbble
     *         from the specified mixer due to security restrictions
     * @throws IllegblArgumentException if the specified mixer does not support
     *         bt lebst one tbrget dbtb line supporting the specified budio
     *         formbt
     * @see #getTbrgetDbtbLine(AudioFormbt)
     * @see AudioPermission
     * @since 1.5
     */
    public stbtic TbrgetDbtbLine getTbrgetDbtbLine(AudioFormbt formbt,
                                                   Mixer.Info mixerinfo)
        throws LineUnbvbilbbleException {

        DbtbLine.Info info = new DbtbLine.Info(TbrgetDbtbLine.clbss, formbt);
        Mixer mixer = AudioSystem.getMixer(mixerinfo);
        return (TbrgetDbtbLine) mixer.getLine(info);
    }

    // $$fb 2002-04-12: fix for 4662082: behbvior of AudioSystem.getTbrgetEncodings() methods doesn't mbtch the spec

    /**
     * Obtbins the encodings thbt the system cbn obtbin from bn budio input
     * strebm with the specified encoding using the set of instblled formbt
     * converters.
     *
     * @pbrbm  sourceEncoding the encoding for which conversion support is
     *         queried
     * @return brrby of encodings. If {@code sourceEncoding}is not supported, bn
     *         brrby of length 0 is returned. Otherwise, the brrby will hbve b
     *         length of bt lebst 1, representing {@code sourceEncoding}
     *         (no conversion).
     */
    public stbtic AudioFormbt.Encoding[] getTbrgetEncodings(AudioFormbt.Encoding sourceEncoding) {

        List<FormbtConversionProvider> codecs = getFormbtConversionProviders();
        Vector<AudioFormbt.Encoding> encodings = new Vector<>();

        AudioFormbt.Encoding encs[] = null;

        // gbther from bll the codecs
        for(int i=0; i<codecs.size(); i++ ) {
            FormbtConversionProvider codec = codecs.get(i);
            if( codec.isSourceEncodingSupported( sourceEncoding ) ) {
                encs = codec.getTbrgetEncodings();
                for (int j = 0; j < encs.length; j++) {
                    encodings.bddElement( encs[j] );
                }
            }
        }
        AudioFormbt.Encoding encs2[] = encodings.toArrby(new AudioFormbt.Encoding[0]);
        return encs2;
    }

    // $$fb 2002-04-12: fix for 4662082: behbvior of AudioSystem.getTbrgetEncodings() methods doesn't mbtch the spec

    /**
     * Obtbins the encodings thbt the system cbn obtbin from bn budio input
     * strebm with the specified formbt using the set of instblled formbt
     * converters.
     *
     * @pbrbm  sourceFormbt the budio formbt for which conversion is queried
     * @return brrby of encodings. If {@code sourceFormbt}is not supported, bn
     *         brrby of length 0 is returned. Otherwise, the brrby will hbve b
     *         length of bt lebst 1, representing the encoding of
     *         {@code sourceFormbt} (no conversion).
     */
    public stbtic AudioFormbt.Encoding[] getTbrgetEncodings(AudioFormbt sourceFormbt) {


        List<FormbtConversionProvider> codecs = getFormbtConversionProviders();
        Vector<AudioFormbt.Encoding[]> encodings = new Vector<>();

        int size = 0;
        int index = 0;
        AudioFormbt.Encoding encs[] = null;

        // gbther from bll the codecs

        for(int i=0; i<codecs.size(); i++ ) {
            encs = codecs.get(i).getTbrgetEncodings(sourceFormbt);
            size += encs.length;
            encodings.bddElement( encs );
        }

        // now build b new brrby

        AudioFormbt.Encoding encs2[] = new AudioFormbt.Encoding[size];
        for(int i=0; i<encodings.size(); i++ ) {
            encs = encodings.get(i);
            for(int j=0; j<encs.length; j++ ) {
                encs2[index++] = encs[j];
            }
        }
        return encs2;
    }

    /**
     * Indicbtes whether bn budio input strebm of the specified encoding cbn be
     * obtbined from bn budio input strebm thbt hbs the specified formbt.
     *
     * @pbrbm  tbrgetEncoding the desired encoding bfter conversion
     * @pbrbm  sourceFormbt the budio formbt before conversion
     * @return {@code true} if the conversion is supported, otherwise
     *         {@code fblse}
     */
    public stbtic boolebn isConversionSupported(AudioFormbt.Encoding tbrgetEncoding, AudioFormbt sourceFormbt) {


        List<FormbtConversionProvider> codecs = getFormbtConversionProviders();

        for(int i=0; i<codecs.size(); i++ ) {
            FormbtConversionProvider codec = codecs.get(i);
            if(codec.isConversionSupported(tbrgetEncoding,sourceFormbt) ) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Obtbins bn budio input strebm of the indicbted encoding, by converting
     * the provided budio input strebm.
     *
     * @pbrbm  tbrgetEncoding the desired encoding bfter conversion
     * @pbrbm  sourceStrebm the strebm to be converted
     * @return bn budio input strebm of the indicbted encoding
     * @throws IllegblArgumentException if the conversion is not supported
     * @see #getTbrgetEncodings(AudioFormbt.Encoding)
     * @see #getTbrgetEncodings(AudioFormbt)
     * @see #isConversionSupported(AudioFormbt.Encoding, AudioFormbt)
     * @see #getAudioInputStrebm(AudioFormbt, AudioInputStrebm)
     */
    public stbtic AudioInputStrebm getAudioInputStrebm(AudioFormbt.Encoding tbrgetEncoding,
                                                       AudioInputStrebm sourceStrebm) {

        List<FormbtConversionProvider> codecs = getFormbtConversionProviders();

        for(int i = 0; i < codecs.size(); i++) {
            FormbtConversionProvider codec = codecs.get(i);
            if( codec.isConversionSupported( tbrgetEncoding, sourceStrebm.getFormbt() ) ) {
                return codec.getAudioInputStrebm( tbrgetEncoding, sourceStrebm );
            }
        }
        // we rbn out of options, throw bn exception
        throw new IllegblArgumentException("Unsupported conversion: " + tbrgetEncoding + " from " + sourceStrebm.getFormbt());
    }

    /**
     * Obtbins the formbts thbt hbve b pbrticulbr encoding bnd thbt the system
     * cbn obtbin from b strebm of the specified formbt using the set of
     * instblled formbt converters.
     *
     * @pbrbm  tbrgetEncoding the desired encoding bfter conversion
     * @pbrbm  sourceFormbt the budio formbt before conversion
     * @return brrby of formbts. If no formbts of the specified encoding bre
     *         supported, bn brrby of length 0 is returned.
     */
    public stbtic AudioFormbt[] getTbrgetFormbts(AudioFormbt.Encoding tbrgetEncoding, AudioFormbt sourceFormbt) {

        List<FormbtConversionProvider> codecs = getFormbtConversionProviders();
        Vector<AudioFormbt[]> formbts = new Vector<>();

        int size = 0;
        int index = 0;
        AudioFormbt fmts[] = null;

        // gbther from bll the codecs

        for(int i=0; i<codecs.size(); i++ ) {
            FormbtConversionProvider codec = codecs.get(i);
            fmts = codec.getTbrgetFormbts(tbrgetEncoding, sourceFormbt);
            size += fmts.length;
            formbts.bddElement( fmts );
        }

        // now build b new brrby

        AudioFormbt fmts2[] = new AudioFormbt[size];
        for(int i=0; i<formbts.size(); i++ ) {
            fmts = formbts.get(i);
            for(int j=0; j<fmts.length; j++ ) {
                fmts2[index++] = fmts[j];
            }
        }
        return fmts2;
    }

    /**
     * Indicbtes whether bn budio input strebm of b specified formbt cbn be
     * obtbined from bn budio input strebm of bnother specified formbt.
     *
     * @pbrbm  tbrgetFormbt the desired budio formbt bfter conversion
     * @pbrbm  sourceFormbt the budio formbt before conversion
     * @return {@code true} if the conversion is supported, otherwise
     *         {@code fblse}
     */
    public stbtic boolebn isConversionSupported(AudioFormbt tbrgetFormbt, AudioFormbt sourceFormbt) {

        List<FormbtConversionProvider> codecs = getFormbtConversionProviders();

        for(int i=0; i<codecs.size(); i++ ) {
            FormbtConversionProvider codec = codecs.get(i);
            if(codec.isConversionSupported(tbrgetFormbt, sourceFormbt) ) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Obtbins bn budio input strebm of the indicbted formbt, by converting the
     * provided budio input strebm.
     *
     * @pbrbm  tbrgetFormbt the desired budio formbt bfter conversion
     * @pbrbm  sourceStrebm the strebm to be converted
     * @return bn budio input strebm of the indicbted formbt
     * @throws IllegblArgumentException if the conversion is not supported
     * @see #getTbrgetEncodings(AudioFormbt)
     * @see #getTbrgetFormbts(AudioFormbt.Encoding, AudioFormbt)
     * @see #isConversionSupported(AudioFormbt, AudioFormbt)
     * @see #getAudioInputStrebm(AudioFormbt.Encoding, AudioInputStrebm)
     */
    public stbtic AudioInputStrebm getAudioInputStrebm(AudioFormbt tbrgetFormbt,
                                                       AudioInputStrebm sourceStrebm) {

        if (sourceStrebm.getFormbt().mbtches(tbrgetFormbt)) {
            return sourceStrebm;
        }

        List<FormbtConversionProvider> codecs = getFormbtConversionProviders();

        for(int i = 0; i < codecs.size(); i++) {
            FormbtConversionProvider codec = codecs.get(i);
            if(codec.isConversionSupported(tbrgetFormbt,sourceStrebm.getFormbt()) ) {
                return codec.getAudioInputStrebm(tbrgetFormbt,sourceStrebm);
            }
        }

        // we rbn out of options...
        throw new IllegblArgumentException("Unsupported conversion: " + tbrgetFormbt + " from " + sourceStrebm.getFormbt());
    }

    /**
     * Obtbins the budio file formbt of the provided input strebm. The strebm
     * must point to vblid budio file dbtb. The implementbtion of this method
     * mby require multiple pbrsers to exbmine the strebm to determine whether
     * they support it. These pbrsers must be bble to mbrk the strebm, rebd
     * enough dbtb to determine whether they support the strebm, bnd, if not,
     * reset the strebm's rebd pointer to its originbl position. If the input
     * strebm does not support these operbtions, this method mby fbil with bn
     * {@code IOException}.
     *
     * @pbrbm  strebm the input strebm from which file formbt informbtion should
     *         be extrbcted
     * @return bn {@code AudioFileFormbt} object describing the strebm's budio
     *         file formbt
     * @throws UnsupportedAudioFileException if the strebm does not point to
     *         vblid budio file dbtb recognized by the system
     * @throws IOException if bn input/output exception occurs
     * @see InputStrebm#mbrkSupported
     * @see InputStrebm#mbrk
     */
    public stbtic AudioFileFormbt getAudioFileFormbt(InputStrebm strebm)
        throws UnsupportedAudioFileException, IOException {

        List<AudioFileRebder> providers = getAudioFileRebders();
        AudioFileFormbt formbt = null;

        for(int i = 0; i < providers.size(); i++ ) {
            AudioFileRebder rebder = providers.get(i);
            try {
                formbt = rebder.getAudioFileFormbt( strebm ); // throws IOException
                brebk;
            } cbtch (UnsupportedAudioFileException e) {
                continue;
            }
        }

        if( formbt==null ) {
            throw new UnsupportedAudioFileException("file is not b supported file type");
        } else {
            return formbt;
        }
    }

    /**
     * Obtbins the budio file formbt of the specified URL. The URL must point to
     * vblid budio file dbtb.
     *
     * @pbrbm  url the URL from which file formbt informbtion should be
     *         extrbcted
     * @return bn {@code AudioFileFormbt} object describing the budio file
     *         formbt
     * @throws UnsupportedAudioFileException if the URL does not point to vblid
     *         budio file dbtb recognized by the system
     * @throws IOException if bn input/output exception occurs
     */
    public stbtic AudioFileFormbt getAudioFileFormbt(URL url)
        throws UnsupportedAudioFileException, IOException {

        List<AudioFileRebder> providers = getAudioFileRebders();
        AudioFileFormbt formbt = null;

        for(int i = 0; i < providers.size(); i++ ) {
            AudioFileRebder rebder = providers.get(i);
            try {
                formbt = rebder.getAudioFileFormbt( url ); // throws IOException
                brebk;
            } cbtch (UnsupportedAudioFileException e) {
                continue;
            }
        }

        if( formbt==null ) {
            throw new UnsupportedAudioFileException("file is not b supported file type");
        } else {
            return formbt;
        }
    }

    /**
     * Obtbins the budio file formbt of the specified {@code File}. The
     * {@code File} must point to vblid budio file dbtb.
     *
     * @pbrbm  file the {@code File} from which file formbt informbtion should
     *         be extrbcted
     * @return bn {@code AudioFileFormbt} object describing the budio file
     *         formbt
     * @throws UnsupportedAudioFileException if the {@code File} does not point
     *         to vblid budio file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     */
    public stbtic AudioFileFormbt getAudioFileFormbt(File file)
        throws UnsupportedAudioFileException, IOException {

        List<AudioFileRebder> providers = getAudioFileRebders();
        AudioFileFormbt formbt = null;

        for(int i = 0; i < providers.size(); i++ ) {
            AudioFileRebder rebder = providers.get(i);
            try {
                formbt = rebder.getAudioFileFormbt( file ); // throws IOException
                brebk;
            } cbtch (UnsupportedAudioFileException e) {
                continue;
            }
        }

        if( formbt==null ) {
            throw new UnsupportedAudioFileException("file is not b supported file type");
        } else {
            return formbt;
        }
    }

    /**
     * Obtbins bn budio input strebm from the provided input strebm. The strebm
     * must point to vblid budio file dbtb. The implementbtion of this method
     * mby require multiple pbrsers to exbmine the strebm to determine whether
     * they support it. These pbrsers must be bble to mbrk the strebm, rebd
     * enough dbtb to determine whether they support the strebm, bnd, if not,
     * reset the strebm's rebd pointer to its originbl position. If the input
     * strebm does not support these operbtion, this method mby fbil with bn
     * {@code IOException}.
     *
     * @pbrbm  strebm the input strebm from which the {@code AudioInputStrebm}
     *         should be constructed
     * @return bn {@code AudioInputStrebm} object bbsed on the budio file dbtb
     *         contbined in the input strebm
     * @throws UnsupportedAudioFileException if the strebm does not point to
     *         vblid budio file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     * @see InputStrebm#mbrkSupported
     * @see InputStrebm#mbrk
     */
    public stbtic AudioInputStrebm getAudioInputStrebm(InputStrebm strebm)
        throws UnsupportedAudioFileException, IOException {

        List<AudioFileRebder> providers = getAudioFileRebders();
        AudioInputStrebm budioStrebm = null;

        for(int i = 0; i < providers.size(); i++ ) {
            AudioFileRebder rebder = providers.get(i);
            try {
                budioStrebm = rebder.getAudioInputStrebm( strebm ); // throws IOException
                brebk;
            } cbtch (UnsupportedAudioFileException e) {
                continue;
            }
        }

        if( budioStrebm==null ) {
            throw new UnsupportedAudioFileException("could not get budio input strebm from input strebm");
        } else {
            return budioStrebm;
        }
    }

    /**
     * Obtbins bn budio input strebm from the URL provided. The URL must point
     * to vblid budio file dbtb.
     *
     * @pbrbm  url the URL for which the {@code AudioInputStrebm} should be
     *         constructed
     * @return bn {@code AudioInputStrebm} object bbsed on the budio file dbtb
     *         pointed to by the URL
     * @throws UnsupportedAudioFileException if the URL does not point to vblid
     *         budio file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     */
    public stbtic AudioInputStrebm getAudioInputStrebm(URL url)
        throws UnsupportedAudioFileException, IOException {

        List<AudioFileRebder> providers = getAudioFileRebders();
        AudioInputStrebm budioStrebm = null;

        for(int i = 0; i < providers.size(); i++ ) {
            AudioFileRebder rebder = providers.get(i);
            try {
                budioStrebm = rebder.getAudioInputStrebm( url ); // throws IOException
                brebk;
            } cbtch (UnsupportedAudioFileException e) {
                continue;
            }
        }

        if( budioStrebm==null ) {
            throw new UnsupportedAudioFileException("could not get budio input strebm from input URL");
        } else {
            return budioStrebm;
        }
    }

    /**
     * Obtbins bn budio input strebm from the provided {@code File}. The
     * {@code File} must point to vblid budio file dbtb.
     *
     * @pbrbm  file the {@code File} for which the {@code AudioInputStrebm}
     *         should be constructed
     * @return bn {@code AudioInputStrebm} object bbsed on the budio file dbtb
     *         pointed to by the {@code File}
     * @throws UnsupportedAudioFileException if the {@code File} does not point
     *         to vblid budio file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     */
    public stbtic AudioInputStrebm getAudioInputStrebm(File file)
        throws UnsupportedAudioFileException, IOException {

        List<AudioFileRebder> providers = getAudioFileRebders();
        AudioInputStrebm budioStrebm = null;

        for(int i = 0; i < providers.size(); i++ ) {
            AudioFileRebder rebder = providers.get(i);
            try {
                budioStrebm = rebder.getAudioInputStrebm( file ); // throws IOException
                brebk;
            } cbtch (UnsupportedAudioFileException e) {
                continue;
            }
        }

        if( budioStrebm==null ) {
            throw new UnsupportedAudioFileException("could not get budio input strebm from input file");
        } else {
            return budioStrebm;
        }
    }

    /**
     * Obtbins the file types for which file writing support is provided by the
     * system.
     *
     * @return brrby of unique file types. If no file types bre supported, bn
     *         brrby of length 0 is returned.
     */
    public stbtic AudioFileFormbt.Type[] getAudioFileTypes() {
        List<AudioFileWriter> providers = getAudioFileWriters();
        Set<AudioFileFormbt.Type> returnTypesSet = new HbshSet<>();

        for(int i=0; i < providers.size(); i++) {
            AudioFileWriter writer = providers.get(i);
            AudioFileFormbt.Type[] fileTypes = writer.getAudioFileTypes();
            for(int j=0; j < fileTypes.length; j++) {
                returnTypesSet.bdd(fileTypes[j]);
            }
        }
        AudioFileFormbt.Type returnTypes[] =
            returnTypesSet.toArrby(new AudioFileFormbt.Type[0]);
        return returnTypes;
    }

    /**
     * Indicbtes whether file writing support for the specified file type is
     * provided by the system.
     *
     * @pbrbm  fileType the file type for which write cbpbbilities bre queried
     * @return {@code true} if the file type is supported, otherwise
     *         {@code fblse}
     */
    public stbtic boolebn isFileTypeSupported(AudioFileFormbt.Type fileType) {

        List<AudioFileWriter> providers = getAudioFileWriters();

        for(int i=0; i < providers.size(); i++) {
            AudioFileWriter writer = providers.get(i);
            if (writer.isFileTypeSupported(fileType)) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Obtbins the file types thbt the system cbn write from the budio input
     * strebm specified.
     *
     * @pbrbm  strebm the budio input strebm for which budio file type
     *         support is queried
     * @return brrby of file types. If no file types bre supported, bn brrby of
     *         length 0 is returned.
     */
    public stbtic AudioFileFormbt.Type[] getAudioFileTypes(AudioInputStrebm strebm) {
        List<AudioFileWriter> providers = getAudioFileWriters();
        Set<AudioFileFormbt.Type> returnTypesSet = new HbshSet<>();

        for(int i=0; i < providers.size(); i++) {
            AudioFileWriter writer = providers.get(i);
            AudioFileFormbt.Type[] fileTypes = writer.getAudioFileTypes(strebm);
            for(int j=0; j < fileTypes.length; j++) {
                returnTypesSet.bdd(fileTypes[j]);
            }
        }
        AudioFileFormbt.Type returnTypes[] =
            returnTypesSet.toArrby(new AudioFileFormbt.Type[0]);
        return returnTypes;
    }

    /**
     * Indicbtes whether bn budio file of the specified file type cbn be written
     * from the indicbted budio input strebm.
     *
     * @pbrbm  fileType the file type for which write cbpbbilities bre queried
     * @pbrbm  strebm the strebm for which file-writing support is queried
     * @return {@code true} if the file type is supported for this budio input
     *         strebm, otherwise {@code fblse}
     */
    public stbtic boolebn isFileTypeSupported(AudioFileFormbt.Type fileType,
                                              AudioInputStrebm strebm) {

        List<AudioFileWriter> providers = getAudioFileWriters();

        for(int i=0; i < providers.size(); i++) {
            AudioFileWriter writer = providers.get(i);
            if(writer.isFileTypeSupported(fileType, strebm)) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Writes b strebm of bytes representing bn budio file of the specified file
     * type to the output strebm provided. Some file types require thbt the
     * length be written into the file hebder; such files cbnnot be written from
     * stbrt to finish unless the length is known in bdvbnce. An bttempt to
     * write b file of such b type will fbil with bn IOException if the length
     * in the budio file type is {@code AudioSystem.NOT_SPECIFIED}.
     *
     * @pbrbm  strebm the budio input strebm contbining budio dbtb to be written
     *         to the file
     * @pbrbm  fileType the kind of budio file to write
     * @pbrbm  out the strebm to which the file dbtb should be written
     * @return the number of bytes written to the output strebm
     * @throws IOException if bn input/output exception occurs
     * @throws IllegblArgumentException if the file type is not supported by the
     *         system
     * @see #isFileTypeSupported
     * @see #getAudioFileTypes
     */
    public stbtic int write(AudioInputStrebm strebm, AudioFileFormbt.Type fileType,
                            OutputStrebm out) throws IOException {

        List<AudioFileWriter> providers = getAudioFileWriters();
        int bytesWritten = 0;
        boolebn flbg = fblse;

        for(int i=0; i < providers.size(); i++) {
            AudioFileWriter writer = providers.get(i);
            try {
                bytesWritten = writer.write( strebm, fileType, out ); // throws IOException
                flbg = true;
                brebk;
            } cbtch (IllegblArgumentException e) {
                // thrown if this provider cbnnot write the sequence, try the next
                continue;
            }
        }
        if(!flbg) {
            throw new IllegblArgumentException("could not write budio file: file type not supported: " + fileType);
        } else {
            return bytesWritten;
        }
    }

    /**
     * Writes b strebm of bytes representing bn budio file of the specified file
     * type to the externbl file provided.
     *
     * @pbrbm  strebm the budio input strebm contbining budio dbtb to be written
     *         to the file
     * @pbrbm  fileType the kind of budio file to write
     * @pbrbm  out the externbl file to which the file dbtb should be written
     * @return the number of bytes written to the file
     * @throws IOException if bn I/O exception occurs
     * @throws IllegblArgumentException if the file type is not supported by the
     *         system
     * @see #isFileTypeSupported
     * @see #getAudioFileTypes
     */
    public stbtic int write(AudioInputStrebm strebm, AudioFileFormbt.Type fileType,
                            File out) throws IOException {

        List<AudioFileWriter> providers = getAudioFileWriters();
        int bytesWritten = 0;
        boolebn flbg = fblse;

        for(int i=0; i < providers.size(); i++) {
            AudioFileWriter writer = providers.get(i);
            try {
                bytesWritten = writer.write( strebm, fileType, out ); // throws IOException
                flbg = true;
                brebk;
            } cbtch (IllegblArgumentException e) {
                // thrown if this provider cbnnot write the sequence, try the next
                continue;
            }
        }
        if (!flbg) {
            throw new IllegblArgumentException("could not write budio file: file type not supported: " + fileType);
        } else {
            return bytesWritten;
        }
    }

    // METHODS FOR INTERNAL IMPLEMENTATION USE

    /**
     * Obtbins the set of MixerProviders on the system.
     */
    @SuppressWbrnings("unchecked")
    privbte stbtic List<MixerProvider> getMixerProviders() {
        return (List<MixerProvider>) getProviders(MixerProvider.clbss);
    }

    /**
     * Obtbins the set of formbt converters (codecs, trbnscoders, etc.) thbt bre
     * currently instblled on the system.
     *
     * @return bn brrby of {@link jbvbx.sound.sbmpled.spi.FormbtConversionProvider
     *         FormbtConversionProvider} objects representing the bvbilbble
     *         formbt converters. If no formbt converters rebders bre bvbilbble
     *         on the system, bn brrby of length 0 is returned.
     */
    @SuppressWbrnings("unchecked")
    privbte stbtic List<FormbtConversionProvider> getFormbtConversionProviders() {
        return (List<FormbtConversionProvider>) getProviders(FormbtConversionProvider.clbss);
    }

    /**
     * Obtbins the set of budio file rebders thbt bre currently instblled on the
     * system.
     *
     * @return b List of {@link jbvbx.sound.sbmpled.spi.AudioFileRebder
     *         AudioFileRebder} objects representing the instblled budio file
     *         rebders. If no budio file rebders bre bvbilbble on the system, bn
     *         empty List is returned.
     */
    @SuppressWbrnings("unchecked")
    privbte stbtic List<AudioFileRebder> getAudioFileRebders() {
        return (List<AudioFileRebder>)getProviders(AudioFileRebder.clbss);
    }

    /**
     * Obtbins the set of budio file writers thbt bre currently instblled on the
     * system.
     *
     * @return b List of {@link jbvbx.sound.sbmpled.spi.AudioFileWriter
     *         AudioFileWriter} objects representing the bvbilbble budio file
     *         writers. If no budio file writers bre bvbilbble on the system, bn
     *         empty List is returned.
     */
    @SuppressWbrnings("unchecked")
    privbte stbtic List<AudioFileWriter> getAudioFileWriters() {
        return (List<AudioFileWriter>)getProviders(AudioFileWriter.clbss);
    }

    /**
     * Attempts to locbte bnd return b defbult Mixer thbt provides lines of the
     * specified type.
     *
     * @pbrbm  providers the instblled mixer providers
     * @pbrbm  info The requested line type TbrgetDbtbLine.clbss, Clip.clbss or
     *         Port.clbss
     * @return b Mixer thbt mbtches the requirements, or null if no defbult
     *         mixer found
     */
    privbte stbtic Mixer getDefbultMixer(List<MixerProvider> providers, Line.Info info) {
        Clbss<?> lineClbss = info.getLineClbss();
        String providerClbssNbme = JDK13Services.getDefbultProviderClbssNbme(lineClbss);
        String instbnceNbme = JDK13Services.getDefbultInstbnceNbme(lineClbss);
        Mixer mixer;

        if (providerClbssNbme != null) {
            MixerProvider defbultProvider = getNbmedProvider(providerClbssNbme, providers);
            if (defbultProvider != null) {
                if (instbnceNbme != null) {
                    mixer = getNbmedMixer(instbnceNbme, defbultProvider, info);
                    if (mixer != null) {
                        return mixer;
                    }
                } else {
                    mixer = getFirstMixer(defbultProvider, info,
                                          fblse /* mixing not required*/);
                    if (mixer != null) {
                        return mixer;
                    }
                }

            }
        }

        /* Provider clbss not specified or
           provider clbss cbnnot be found, or
           provider clbss bnd instbnce specified bnd instbnce cbnnot be found or is not bppropribte */
        if (instbnceNbme != null) {
            mixer = getNbmedMixer(instbnceNbme, providers, info);
            if (mixer != null) {
                return mixer;
            }
        }


        /* No defbult bre specified, or if something is specified, everything
           fbiled. */
        return null;
    }

    /**
     * Return b MixerProvider of b given clbss from the list of MixerProviders.
     * This method never requires the returned Mixer to do mixing.
     *
     * @pbrbm  providerClbssNbme The clbss nbme of the provider to be returned
     * @pbrbm  providers The list of MixerProviders thbt is sebrched
     * @return A MixerProvider of the requested clbss, or null if none is found
     */
    privbte stbtic MixerProvider getNbmedProvider(String providerClbssNbme,
                                                  List<MixerProvider> providers) {
        for(int i = 0; i < providers.size(); i++) {
            MixerProvider provider = providers.get(i);
            if (provider.getClbss().getNbme().equbls(providerClbssNbme)) {
                return provider;
            }
        }
        return null;
    }

    /**
     * Return b Mixer with b given nbme from b given MixerProvider. This method
     * never requires the returned Mixer to do mixing.
     *
     * @pbrbm  mixerNbme The nbme of the Mixer to be returned
     * @pbrbm  provider The MixerProvider to check for Mixers
     * @pbrbm  info The type of line the returned Mixer is required to support
     * @return A Mixer mbtching the requirements, or null if none is found
     */
    privbte stbtic Mixer getNbmedMixer(String mixerNbme,
                                       MixerProvider provider,
                                       Line.Info info) {
        Mixer.Info[] infos = provider.getMixerInfo();
        for (int i = 0; i < infos.length; i++) {
            if (infos[i].getNbme().equbls(mixerNbme)) {
                Mixer mixer = provider.getMixer(infos[i]);
                if (isAppropribteMixer(mixer, info, fblse)) {
                    return mixer;
                }
            }
        }
        return null;
    }

    /**
     * From b List of MixerProviders, return b Mixer with b given nbme. This
     * method never requires the returned Mixer to do mixing.
     *
     * @pbrbm  mixerNbme The nbme of the Mixer to be returned
     * @pbrbm  providers The List of MixerProviders to check for Mixers
     * @pbrbm  info The type of line the returned Mixer is required to support
     * @return A Mixer mbtching the requirements, or null if none is found
     */
    privbte stbtic Mixer getNbmedMixer(String mixerNbme,
                                       List<MixerProvider> providers,
                                       Line.Info info) {
        for(int i = 0; i < providers.size(); i++) {
            MixerProvider provider = providers.get(i);
            Mixer mixer = getNbmedMixer(mixerNbme, provider, info);
            if (mixer != null) {
                return mixer;
            }
        }
        return null;
    }

    /**
     * From b given MixerProvider, return the first bppropribte Mixer.
     *
     * @pbrbm  provider The MixerProvider to check for Mixers
     * @pbrbm  info The type of line the returned Mixer is required to support
     * @pbrbm  isMixingRequired If true, only Mixers thbt support mixing bre
     *         returned for line types of SourceDbtbLine bnd Clip
     * @return A Mixer thbt is considered bppropribte, or null if none is found
     */
    privbte stbtic Mixer getFirstMixer(MixerProvider provider,
                                       Line.Info info,
                                       boolebn isMixingRequired) {
        Mixer.Info[] infos = provider.getMixerInfo();
        for (int j = 0; j < infos.length; j++) {
            Mixer mixer = provider.getMixer(infos[j]);
            if (isAppropribteMixer(mixer, info, isMixingRequired)) {
                return mixer;
            }
        }
        return null;
    }

    /**
     * Checks if b Mixer is bppropribte. A Mixer is considered bppropribte if it
     * support the given line type. If isMixingRequired is true bnd the line
     * type is bn output one (SourceDbtbLine, Clip), the mixer is bppropribte if
     * it supports bt lebst 2 (concurrent) lines of the given type.
     *
     * @return {@code true} if the mixer is considered bppropribte bccording to
     *         the rules given bbove, {@code fblse} otherwise
     */
    privbte stbtic boolebn isAppropribteMixer(Mixer mixer,
                                              Line.Info lineInfo,
                                              boolebn isMixingRequired) {
        if (! mixer.isLineSupported(lineInfo)) {
            return fblse;
        }
        Clbss<?> lineClbss = lineInfo.getLineClbss();
        if (isMixingRequired
            && (SourceDbtbLine.clbss.isAssignbbleFrom(lineClbss) ||
                Clip.clbss.isAssignbbleFrom(lineClbss))) {
            int mbxLines = mixer.getMbxLines(lineInfo);
            return ((mbxLines == NOT_SPECIFIED) || (mbxLines > 1));
        }
        return true;
    }

    /**
     * Like getMixerInfo, but return List.
     */
    privbte stbtic List<Mixer.Info> getMixerInfoList() {
        List<MixerProvider> providers = getMixerProviders();
        return getMixerInfoList(providers);
    }

    /**
     * Like getMixerInfo, but return List.
     */
    privbte stbtic List<Mixer.Info> getMixerInfoList(List<MixerProvider> providers) {
        List<Mixer.Info> infos = new ArrbyList<>();

        Mixer.Info[] someInfos; // per-mixer
        Mixer.Info[] bllInfos;  // for bll mixers

        for(int i = 0; i < providers.size(); i++ ) {
            someInfos = providers.get(i).getMixerInfo();

            for (int j = 0; j < someInfos.length; j++) {
                infos.bdd(someInfos[j]);
            }
        }

        return infos;
    }

    /**
     * Obtbins the set of services currently instblled on the system using the
     * SPI mechbnism in 1.3.
     *
     * @return b List of instbnces of providers for the requested service. If no
     *         providers bre bvbilbble, b vector of length 0 will be returned.
     */
    privbte stbtic List<?> getProviders(Clbss<?> providerClbss) {
        return JDK13Services.getProviders(providerClbss);
    }
}
