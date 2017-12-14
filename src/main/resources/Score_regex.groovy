import gov.va.vinci.leo.regex.types.RegularExpressionType

/* An arbitrary name for this annotator. Used in the pipeline for the name of this annotation. */
name = "MyRegexAnnotator"

configuration {
  /* All configuration for this annotator. */
  defaults {
    /* Global for all configrations below if a property specified here is not overridden in a section below. */
    //keep the same (for patrick)
    outputType = RegularExpressionType.class.canonicalName
    case_sensitive = false
    matchedPatternFeatureName = "pattern"
    concept_feature_name = "concept"
    groupFeatureName = "group"
  }

  /* An arbitrary name for this set of patterns/config. */

  "Score_concept" {
    expressions = [
        'stroke'
    ]
    outputType = "gov.va.vinci.stroke.types.Score"
  }

  "Concept_word" {
    expressions = ['class', 'grade', 'level', 'score', 'stage', '\\bis\\b']

    concept_feature_value = "Score_word"
    outputType = "gov.va.vinci.stroke.types.Score_word"
  }

  "Score-1" {
    expressions = ['\\b(1|one|I)\\b']
    concept_feature_value = "Score_1"
    outputType = "gov.va.vinci.stroke.types.Score_score"
  }

  "Score-1.5" {
    expressions = [
        '\\b(1|one|I) *(-|to|or|/) *(2|II|two)\\b'
    ]
    concept_feature_value = "Score_1.5"
    outputType = "gov.va.vinci.stroke.types.Score_score"
  }

  "Score-2" {
    expressions = ['\\b(2|two|II)\\b']

    concept_feature_value = "Score_2"
    outputType = "gov.va.vinci.stroke.types.Score_score"
  }
  "Score-2.5" {
    expressions = [
        '\\b(2|II|two) *(-|to|or|/) *(3|III|three)\\b'
    ]

    concept_feature_value = "Score_2.5"
    outputType = "gov.va.vinci.stroke.types.Score_score"
  }

  "Score-3" {
    expressions = ['\\b(3|three|III)\\b']

    concept_feature_value = "Score_3"
    outputType = "gov.va.vinci.stroke.types.Score_score"
  }

  "Score-3.5" {
    expressions = [
        '\\b(3|III|three) *(-|to|or|/) *(4|IV|four)\\b'
    ]
    concept_feature_value = "Score_3.5"
    outputType = "gov.va.vinci.stroke.types.Score_score"
  }

  "Score-4" {
    expressions = ['\\b(4|four|IV)\\b']
    concept_feature_value = "Score_4"
    outputType = "gov.va.vinci.stroke.types.Score_score"
  }

  "Exclude" {
    expressions = [
        'NYHA',
        '\\d{2,}',
        '\\d+.\\d{2,}',
        'aha',
        'hf',
        'heart failure',
        ': *\\[\\] *yes,?',
        '\\s*episodes?',
        '\\s*times',
        '\\s*nights?',
        '\\s*/?days?',
        '\\s*/?weeks?',
        '\\s*/?wks?',
        '\\s*months?',
        '\\s*minutes?',
        '\\s*years?',
        'x *(/ *)?(weeks?|wks?)', // stroke 2x/week
        '.\\dx?/?\\s*(time|week|wk|day|month|minute|y)', // stroke 2-3x/week
        '2/2',
        '%',
        '-[5-9]',
        '\\d\\d/\\d\\d+',
        '\\d+\\.\\d+',
        '\\d+.\\d+.\\d+',
        '\\( ?\\d+ ?\\) ?((\\d+|\\-|\\*) ?)+',
        '\\[ *\\] *Unstable\\s*stroke\\s*ScoreC\\s*\\(I,\\s*II,\\s*III,\\s*IV\\)'

    ]
    concept_feature_value = "Score_4"
    outputType = "gov.va.vinci.stroke.types.Exclude"
  }
}

