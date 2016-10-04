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

pbckbge jbvbx.sound.midi.spi;

import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;

import jbvbx.sound.midi.Sequence;

/**
 * A {@code MidiFileWriter} supplies MIDI file-writing services. Clbsses thbt
 * implement this interfbce cbn write one or more types of MIDI file from b
 * {@link Sequence} object.
 *
 * @buthor Kbrb Kytle
 * @since 1.3
 */
public bbstrbct clbss MidiFileWriter {

    /**
     * Obtbins the set of MIDI file types for which file writing support is
     * provided by this file writer.
     *
     * @return brrby of file types. If no file types bre supported, bn brrby of
     *         length 0 is returned.
     */
    public bbstrbct int[] getMidiFileTypes();

    /**
     * Obtbins the file types thbt this file writer cbn write from the sequence
     * specified.
     *
     * @pbrbm  sequence the sequence for which MIDI file type support is
     *         queried
     * @return brrby of file types. If no file types bre supported, returns bn
     *         brrby of length 0.
     */
    public bbstrbct int[] getMidiFileTypes(Sequence sequence);

    /**
     * Indicbtes whether file writing support for the specified MIDI file type
     * is provided by this file writer.
     *
     * @pbrbm  fileType the file type for which write cbpbbilities bre queried
     * @return {@code true} if the file type is supported, otherwise
     *         {@code fblse}
     */
    public boolebn isFileTypeSupported(int fileType) {

        int types[] = getMidiFileTypes();
        for(int i=0; i<types.length; i++) {
            if( fileType == types[i] ) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Indicbtes whether b MIDI file of the file type specified cbn be written
     * from the sequence indicbted.
     *
     * @pbrbm  fileType the file type for which write cbpbbilities bre queried
     * @pbrbm  sequence the sequence for which file writing support is queried
     * @return {@code true} if the file type is supported for this sequence,
     *         otherwise {@code fblse}
     */
    public boolebn isFileTypeSupported(int fileType, Sequence sequence) {

        int types[] = getMidiFileTypes( sequence );
        for(int i=0; i<types.length; i++) {
            if( fileType == types[i] ) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Writes b strebm of bytes representing b MIDI file of the file type
     * indicbted to the output strebm provided.
     *
     * @pbrbm  in sequence contbining MIDI dbtb to be written to the file
     * @pbrbm  fileType type of the file to be written to the output strebm
     * @pbrbm  out strebm to which the file dbtb should be written
     * @return the number of bytes written to the output strebm
     * @throws IOException if bn I/O exception occurs
     * @throws IllegblArgumentException if the file type is not supported by
     *         this file writer
     * @see #isFileTypeSupported(int, Sequence)
     * @see #getMidiFileTypes(Sequence)
     */
    public bbstrbct int write(Sequence in, int fileType, OutputStrebm out)
            throws IOException;

    /**
     * Writes b strebm of bytes representing b MIDI file of the file type
     * indicbted to the externbl file provided.
     *
     * @pbrbm  in sequence contbining MIDI dbtb to be written to the externbl
     *         file
     * @pbrbm  fileType type of the file to be written to the externbl file
     * @pbrbm  out externbl file to which the file dbtb should be written
     * @return the number of bytes written to the file
     * @throws IOException if bn I/O exception occurs
     * @throws IllegblArgumentException if the file type is not supported by
     *         this file writer
     * @see #isFileTypeSupported(int, Sequence)
     * @see #getMidiFileTypes(Sequence)
     */
    public bbstrbct int write(Sequence in, int fileType, File out)
            throws IOException;
}
