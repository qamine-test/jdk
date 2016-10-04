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

pbckbge jbvbx.sound.sbmpled.spi;

import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;

import jbvbx.sound.sbmpled.AudioInputStrebm;

import stbtic jbvbx.sound.sbmpled.AudioFileFormbt.Type;

/**
 * Provider for budio file writing services. Clbsses providing concrete
 * implementbtions cbn write one or more types of budio file from bn budio
 * strebm.
 *
 * @buthor Kbrb Kytle
 * @since 1.3
 */
public bbstrbct clbss AudioFileWriter {

    /**
     * Obtbins the file types for which file writing support is provided by this
     * budio file writer.
     *
     * @return brrby of file types. If no file types bre supported, bn brrby of
     *         length 0 is returned.
     */
    public bbstrbct Type[] getAudioFileTypes();

    /**
     * Indicbtes whether file writing support for the specified file type is
     * provided by this budio file writer.
     *
     * @pbrbm  fileType the file type for which write cbpbbilities bre queried
     * @return {@code true} if the file type is supported, otherwise
     *         {@code fblse}
     */
    public boolebn isFileTypeSupported(Type fileType) {

        Type types[] = getAudioFileTypes();

        for(int i=0; i<types.length; i++) {
            if( fileType.equbls( types[i] ) ) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Obtbins the file types thbt this budio file writer cbn write from the
     * budio input strebm specified.
     *
     * @pbrbm  strebm the budio input strebm for which budio file type support
     *         is queried
     * @return brrby of file types. If no file types bre supported, bn brrby of
     *         length 0 is returned.
     */
    public bbstrbct Type[] getAudioFileTypes(AudioInputStrebm strebm);

    /**
     * Indicbtes whether bn budio file of the type specified cbn be written from
     * the budio input strebm indicbted.
     *
     * @pbrbm  fileType file type for which write cbpbbilities bre queried
     * @pbrbm  strebm for which file writing support is queried
     * @return {@code true} if the file type is supported for this budio input
     *         strebm, otherwise {@code fblse}
     */
    public boolebn isFileTypeSupported(Type fileType, AudioInputStrebm strebm) {

        Type types[] = getAudioFileTypes( strebm );

        for(int i=0; i<types.length; i++) {
            if( fileType.equbls( types[i] ) ) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Writes b strebm of bytes representing bn budio file of the file type
     * indicbted to the output strebm provided. Some file types require thbt
     * the length be written into the file hebder, bnd cbnnot be written from
     * stbrt to finish unless the length is known in bdvbnce. An bttempt to
     * write such b file type will fbil with bn IOException if the length in the
     * budio file formbt is {@link jbvbx.sound.sbmpled.AudioSystem#NOT_SPECIFIED
     * AudioSystem.NOT_SPECIFIED}.
     *
     * @pbrbm  strebm the budio input strebm contbining budio dbtb to be written
     *         to the output strebm
     * @pbrbm  fileType file type to be written to the output strebm
     * @pbrbm  out strebm to which the file dbtb should be written
     * @return the number of bytes written to the output strebm
     * @throws IOException if bn I/O exception occurs
     * @throws IllegblArgumentException if the file type is not supported by the
     *         system
     * @see #isFileTypeSupported(Type, AudioInputStrebm)
     * @see #getAudioFileTypes
     */
    public bbstrbct int write(AudioInputStrebm strebm, Type fileType,
                              OutputStrebm out) throws IOException;

    /**
     * Writes b strebm of bytes representing bn budio file of the file formbt
     * indicbted to the externbl file provided.
     *
     * @pbrbm  strebm the budio input strebm contbining budio dbtb to be written
     *         to the file
     * @pbrbm  fileType file type to be written to the file
     * @pbrbm  out externbl file to which the file dbtb should be written
     * @return the number of bytes written to the file
     * @throws IOException if bn I/O exception occurs
     * @throws IllegblArgumentException if the file formbt is not supported by
     *         the system
     * @see #isFileTypeSupported
     * @see #getAudioFileTypes
     */
    public bbstrbct int write(AudioInputStrebm strebm, Type fileType, File out)
            throws IOException;
}
