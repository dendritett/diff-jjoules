package fr.davidson.diff.jjoules.mark;

import fr.davidson.diff.jjoules.AbstractDiffJJoulesStepTest;
import fr.davidson.diff.jjoules.Configuration;
import fr.davidson.diff.jjoules.delta.data.Data;
import fr.davidson.diff.jjoules.delta.data.Delta;
import fr.davidson.diff.jjoules.delta.data.Deltas;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Benjamin DANGLOT
 * benjamin.danglot@davidson.fr
 * on 06/10/2021
 */
public class MarkStepTest extends AbstractDiffJJoulesStepTest {

    @Test
    void test() {
        /*
            At the end of the Mark step, we should have the following files:
                - coverage_first.json
                - coverage_second.json
                - exec_deletions.json
                - exec_additions.json
                - theta.json
                - omega.json
                - Omega.json
                - deltaOmega.json
            And the following fields of the given Configuration should be initialized:
                - execLinesAdditions
                - execLinesDeletions
                - deltaOmega
         */
        final MarkStep markStep = new MarkStep();
        final Configuration configuration = this.getConfiguration();
        configuration.setConsideredTestsNames(configuration.getTestsList());
        final Deltas deltas = new Deltas();
        final Delta delta = new Delta(
                new Data(10, 10, 10, 10, 10, 10, 10, 10),
                new Data(100, 100, 100, 100, 100, 100, 100, 100)
        );
        deltas.put("fr.davidson.diff_jjoules_demo.InternalListTest#testCount", delta);
        configuration.setDeltas(deltas);
        assertFalse(new File(DIFF_JJOULES_FOLDER_PATH + "/coverage_first.json").exists());
        assertFalse(new File(DIFF_JJOULES_FOLDER_PATH + "/coverage_second.json").exists());
        assertFalse(new File(DIFF_JJOULES_FOLDER_PATH + "/exec_deletions.json").exists());
        assertFalse(new File(DIFF_JJOULES_FOLDER_PATH + "/exec_additions.json").exists());
        assertFalse(new File(DIFF_JJOULES_FOLDER_PATH + "/theta.json").exists());
        assertFalse(new File(DIFF_JJOULES_FOLDER_PATH + "/omega.json").exists());
        assertFalse(new File(DIFF_JJOULES_FOLDER_PATH + "/Omega.json").exists());
        assertFalse(new File(DIFF_JJOULES_FOLDER_PATH + "/deltaOmega.json").exists());

        markStep._run(configuration);

        assertTrue(new File(DIFF_JJOULES_FOLDER_PATH + "/coverage_first.json").exists());
        assertTrue(new File(DIFF_JJOULES_FOLDER_PATH + "/coverage_second.json").exists());
        assertTrue(new File(DIFF_JJOULES_FOLDER_PATH + "/exec_deletions.json").exists());
        assertTrue(new File(DIFF_JJOULES_FOLDER_PATH + "/exec_additions.json").exists());
        assertTrue(new File(DIFF_JJOULES_FOLDER_PATH + "/theta.json").exists());
        assertTrue(new File(DIFF_JJOULES_FOLDER_PATH + "/omega.json").exists());
        assertTrue(new File(DIFF_JJOULES_FOLDER_PATH + "/Omega.json").exists());
        assertTrue(new File(DIFF_JJOULES_FOLDER_PATH + "/deltaOmega.json").exists());
        assertTrue(new File(DIFF_JJOULES_FOLDER_PATH + "/coverage_first.json").exists());
        assertFalse(configuration.getExecLinesAdditions().isEmpty());
        assertFalse(configuration.getExecLinesDeletions().isEmpty());
        assertNotNull(configuration.getDeltas());
    }
}
